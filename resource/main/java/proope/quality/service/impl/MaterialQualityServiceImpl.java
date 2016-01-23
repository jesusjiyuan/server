package proope.quality.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import proope.purchase.to.entity.PutWarehouseDetailHistoryEntity;
import proope.purchase.to.entity.PutWarehouseHistoryEntity;
import proope.purchase.to.result.PurchaseArrivalsGoodsDetailResult;
import proope.purchase.to.result.PurchaseArrivalsGoodsDetailStorePageResult;
import proope.purchase.to.result.PutWarehouseDetailHistoryResult;
import proope.quality.QualityDao;
import proope.quality.service.MaterialQualityService;
import proope.warehouse.to.entity.MaterialInventoryEntity;
import common.hibernate.BaseService;
import common.to.StorePageResultTo;

public class MaterialQualityServiceImpl extends BaseService implements MaterialQualityService {

	private QualityDao qualityDao;
	public void setQualityDao(QualityDao qualityDao) {
		this.qualityDao = qualityDao;
	}

	@Override
	public PurchaseArrivalsGoodsDetailStorePageResult findPurchaseArrivalsGoodsDetailStorePageResult(String gysmc, Integer start, Integer limit) {
		String listSql = "select ad.dm, ad.dhdm, ad.cgsqmxdm, ad.cgddmxdm, ad.wldm, m.mc, ad.sqsl, ad.dgsl, ad.dhsl, ad.yrksl, ad.cjrq,ad.cjr,ad.gysmc " +
				"from (select pagd.dm, pagd.dhdm,pagd.cgsqmxdm,pagd.cgddmxdm,pagd.wldm, " +
				"pagd.sqsl,pagd.dgsl,pagd.dhsl,pagd.yrksl,pagd.cjrq,pagd.cjr, t.gysmc " +
				"from tbl_purchase_arrivals_goods_detail pagd left join	(select podh.dm, poh.gysmc " +
				"from tbl_purchase_order_detail_history podh left join tbl_purchase_order_history poh on podh.cgddlsdm = poh.dm " +
				") t on pagd.cgddmxdm = t.dm where 1=1 " + spellingSql("and t.gysmc like '%", gysmc, "%' ") +
				"limit "+start+", " + limit + ") ad left join tbl_material m on ad.wldm = m.dm";
		
		String countSql = "select count(*)  as " + SQLASNAMEITEMCOUNT + 
				" from tbl_purchase_arrivals_goods_detail pagd left join	(select podh.dm, poh.gysmc " +
				"from tbl_purchase_order_detail_history podh left join tbl_purchase_order_history poh on podh.cgddlsdm = poh.dm " +
				") t on pagd.cgddmxdm = t.dm where 1=1 "+ spellingSql("and t.gysmc like '%", gysmc, "%' ");
		
		StorePageResultTo storePageResultTo = qualityDao.searchStorePageSql(listSql, countSql);
		List<PurchaseArrivalsGoodsDetailResult> purchaseArrivalsGoodsDetailList = new ArrayList<PurchaseArrivalsGoodsDetailResult>();
		Object[] objs = null;
		PurchaseArrivalsGoodsDetailResult purchaseArrivalsGoodsDetailResult = null;
		for(Object obj : storePageResultTo.getItemList()){
			objs = (Object[]) obj;
			purchaseArrivalsGoodsDetailResult = new PurchaseArrivalsGoodsDetailResult();
			purchaseArrivalsGoodsDetailResult.setDm(String.valueOf(objs[0]));
			purchaseArrivalsGoodsDetailResult.setDhdm(String.valueOf(objs[1]));
			purchaseArrivalsGoodsDetailResult.setCgsqmxdm(String.valueOf(objs[2]));
			purchaseArrivalsGoodsDetailResult.setCgddmxdm(String.valueOf(objs[3]));
			purchaseArrivalsGoodsDetailResult.setWldm(String.valueOf(objs[4]));
			purchaseArrivalsGoodsDetailResult.setWlmc(String.valueOf(objs[5]));
			purchaseArrivalsGoodsDetailResult.setSqsl(new BigDecimal(String.valueOf(objs[6])));
			purchaseArrivalsGoodsDetailResult.setDgsl(new BigDecimal(String.valueOf(objs[7])));
			purchaseArrivalsGoodsDetailResult.setDhsl(new BigDecimal(String.valueOf(objs[8])));
			purchaseArrivalsGoodsDetailResult.setYrksl(new BigDecimal(String.valueOf(objs[9])));
			purchaseArrivalsGoodsDetailResult.setCjrq(stringToDate(String.valueOf(objs[10]), DBDATEFORMAT));
			purchaseArrivalsGoodsDetailResult.setCjr(String.valueOf(objs[11]));
			purchaseArrivalsGoodsDetailResult.setGysmc(String.valueOf(objs[12]));
			purchaseArrivalsGoodsDetailList.add(purchaseArrivalsGoodsDetailResult);
		}
		return new PurchaseArrivalsGoodsDetailStorePageResult(storePageResultTo.getItemTotal(), purchaseArrivalsGoodsDetailList);
	}
	
	@Override
	public Boolean createMaterialPutWarehouseHistory(PutWarehouseHistoryEntity putWarehouseHistoryEntity, 
			List<PutWarehouseDetailHistoryResult> putWarehouseDetailHistoryResultList, String cjr) {
		String pwhId = tool.UUID.currentTimeMillisAsRandomNumber();  //  生成入库历史ID
		Date nowDate = new Date();
		
		putWarehouseHistoryEntity.setDm(pwhId);
		putWarehouseHistoryEntity.setCjr(cjr);
		putWarehouseHistoryEntity.setCjrq(nowDate);
		Serializable pwhSerializable = qualityDao.save(putWarehouseHistoryEntity);
		
		List<String> wldmList = new ArrayList<String>();
		for(PutWarehouseDetailHistoryResult putWarehouseDetailHistoryResult : putWarehouseDetailHistoryResultList){
			wldmList.add(putWarehouseDetailHistoryResult.getWldm());
		}
		
		List<MaterialInventoryEntity> materialInventoryList = findMaterialInventoryEntityListByWldmList(wldmList);
		MaterialInventoryEntity materialInventoryEntity = null;
		
		List<String> deleArrivalGoodDetailIdList = new ArrayList<String>();
		PutWarehouseDetailHistoryEntity putWarehouseDetailHistoryEntity = null;
		BigDecimal yrkBigDecimal = null;
		String dhmxdm = null;  //  到货明细代码
		for(PutWarehouseDetailHistoryResult putWarehouseDetailHistoryResult : putWarehouseDetailHistoryResultList){
			dhmxdm = putWarehouseDetailHistoryResult.getDhmxlsdm();
			putWarehouseDetailHistoryEntity = new PutWarehouseDetailHistoryEntity();
			BeanUtils.copyProperties(putWarehouseDetailHistoryResult, putWarehouseDetailHistoryEntity);
			putWarehouseDetailHistoryEntity.setDm(tool.UUID.currentTimeMillisAsRandomNumber());
			putWarehouseDetailHistoryEntity.setRklsdm(pwhId);
			putWarehouseDetailHistoryEntity.setCjr(cjr);
			putWarehouseDetailHistoryEntity.setCjrq(nowDate);
			qualityDao.save(putWarehouseDetailHistoryEntity);
			
			materialInventoryEntity = findMaterialInventoryEntityByWldm(materialInventoryList, putWarehouseDetailHistoryResult.getWldm());
			if(materialInventoryEntity == null){
				materialInventoryEntity = new MaterialInventoryEntity();
				materialInventoryEntity.setWldm(putWarehouseDetailHistoryResult.getWldm());
				materialInventoryEntity.setSl(putWarehouseDetailHistoryResult.getRksl());
				materialInventoryEntity.setCjr(cjr);
				materialInventoryEntity.setCjrq(nowDate);
				materialInventoryEntity.setXgr(cjr);
				materialInventoryEntity.setXgrq(nowDate);
				qualityDao.save(materialInventoryEntity);
				materialInventoryList.add(materialInventoryEntity);
			}else{
				materialInventoryEntity.setSl(materialInventoryEntity.getSl().add(putWarehouseDetailHistoryResult.getRksl()));
				materialInventoryEntity.setXgr(cjr);
				materialInventoryEntity.setXgrq(nowDate);
				qualityDao.update(materialInventoryEntity);
			}
			
			yrkBigDecimal = putWarehouseDetailHistoryResult.getRksl().add(putWarehouseDetailHistoryResult.getYrksl());
			if (yrkBigDecimal.compareTo(putWarehouseDetailHistoryResult.getDhsl()) >= 0) {
				//  已全部到货，删除到货表中的记录
				deleArrivalGoodDetailIdList.add(dhmxdm);
			}else{
				//  入库数量与到货数不一致，修改到货中的已入库数量
				qualityDao.executeSql("update tbl_purchase_arrivals_goods_detail set yrksl = "+yrkBigDecimal+" where dm = '"+dhmxdm+"'");
			}
			qualityDao.executeSql("update tbl_purchase_arrivals_goods_detail_history set rksl = "+yrkBigDecimal+" where dm = '"+dhmxdm+"'");
		}
		//  删除已完成的到货明细
		qualityDao.executeSql("delete from tbl_purchase_arrivals_goods_detail where dm in "+sqlStringInCondition(deleArrivalGoodDetailIdList));
		//  删除已完成的到货记录
		qualityDao.executeSql("delete from tbl_purchase_arrivals_goods where dm not in (select dhdm from tbl_purchase_arrivals_goods_detail group by dhdm)");
		return pwhSerializable != null && !pwhSerializable.equals("");
	}
	
	/**
	 * 私有方法，根据物料代码list，查询出库存的物料
	 * @param wldmList
	 * @return
	 */
	private List<MaterialInventoryEntity> findMaterialInventoryEntityListByWldmList(List<String> wldmList) {
		List<Object[]> objsList = qualityDao.searchAllSql("select wldm,sl,cjrq,cjr,xgrq,xgr from tbl_material_inventory where wldm in "+sqlStringInCondition(wldmList));
		List<MaterialInventoryEntity> materialInventoryList = new ArrayList<MaterialInventoryEntity>();
		MaterialInventoryEntity materialInventoryEntity = null;
		for (Object[] objects : objsList) {
			materialInventoryEntity = new MaterialInventoryEntity();
			materialInventoryEntity.setWldm(String.valueOf(objects[0]));
			materialInventoryEntity.setSl(new BigDecimal(String.valueOf(objects[1])));
			materialInventoryEntity.setCjrq(stringToDate(String.valueOf(objects[2]), DBDATEFORMAT));
			materialInventoryEntity.setCjr(String.valueOf(objects[3]));
			materialInventoryEntity.setXgrq(stringToDate(String.valueOf(objects[4]), DBDATEFORMAT));
			materialInventoryEntity.setXgr(String.valueOf(objects[5]));
			materialInventoryList.add(materialInventoryEntity);
		}
		return materialInventoryList;
	}
	
	private MaterialInventoryEntity findMaterialInventoryEntityByWldm(List<MaterialInventoryEntity> mieList, String wldm){
		for(MaterialInventoryEntity materialInventoryEntity : mieList){
			if(wldm.equals(materialInventoryEntity.getWldm())){
				return materialInventoryEntity;
			}
		}
		return null;
	}

}
