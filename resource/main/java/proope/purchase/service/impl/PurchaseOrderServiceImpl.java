package proope.purchase.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import proope.purchase.PurchaseDao;
import proope.purchase.service.PurchaseOrderService;
import proope.purchase.to.entity.PurchaseArrivalsGoodsDetailEntity;
import proope.purchase.to.entity.PurchaseArrivalsGoodsDetailHistoryEntity;
import proope.purchase.to.entity.PurchaseArrivalsGoodsEntity;
import proope.purchase.to.entity.PurchaseArrivalsGoodsHistoryEntity;
import proope.purchase.to.entity.PurchaseOrderDetailEntity;
import proope.purchase.to.entity.PurchaseOrderDetailHistoryEntity;
import proope.purchase.to.entity.PurchaseOrderEntity;
import proope.purchase.to.entity.PurchaseOrderHistoryEntity;
import proope.purchase.to.entity.PutWarehouseDetailHistoryEntity;
import proope.purchase.to.entity.PutWarehouseHistoryEntity;
import proope.purchase.to.result.PurchaseArrivalsGoodsDetailResult;
import proope.purchase.to.result.PurchaseArrivalsGoodsHistoryStorePageResult;
import proope.purchase.to.result.PurchaseOrderDetailResult;
import proope.purchase.to.result.PurchaseOrderDetailStorePageResult;
import proope.purchase.to.result.PurchaseOrderHistoryStorePageResult;
import proope.purchase.to.result.PurchaseOrderResult;
import proope.purchase.to.result.PurchaseOrderStorePageResult;
import proope.purchase.to.result.PutWarehouseHistoryStorePageResult;
import common.hibernate.BaseService;
import common.to.StorePageResultTo;

public class PurchaseOrderServiceImpl extends BaseService implements PurchaseOrderService {

	private PurchaseDao purchaseDao;
	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}
	
	@Override
	public Boolean createPurchaseOrder(PurchaseOrderResult purchaseOrderResult, 
			List<PurchaseOrderDetailResult> purchaseOrderDetailResultList, String cjr, String xgr){
		PurchaseOrderEntity purchaseOrderEntity = new PurchaseOrderEntity();
		BeanUtils.copyProperties(purchaseOrderResult, purchaseOrderEntity);
		String poId = tool.UUID.currentTimeMillisAsRandomNumber();  //  采购订单ID
		Date nowDate = new Date();
		purchaseOrderEntity.setDm(poId);
		purchaseOrderEntity.setZt(0);
		purchaseOrderEntity.setCjr(cjr);
		purchaseOrderEntity.setCjrq(nowDate);
		purchaseOrderEntity.setXgr(xgr);
		purchaseOrderEntity.setXgrq(nowDate);
		Serializable po = purchaseDao.save(purchaseOrderEntity);
		
		//  创建采购订单历史，ID与采购订单相同
		PurchaseOrderHistoryEntity purchaseOrderHistoryEntity = new PurchaseOrderHistoryEntity();
		purchaseOrderHistoryEntity.setDm(poId);
		purchaseOrderHistoryEntity.setGysdm(purchaseOrderResult.getGysdm());
		purchaseOrderHistoryEntity.setGysmc(purchaseOrderResult.getGysmc());
		purchaseOrderHistoryEntity.setGysdh(purchaseOrderResult.getGysdh());
		purchaseOrderHistoryEntity.setGyscz(purchaseOrderResult.getGyscz());
		purchaseOrderHistoryEntity.setShf(purchaseOrderResult.getShf());
		purchaseOrderHistoryEntity.setShflxr(purchaseOrderResult.getShflxr());
		purchaseOrderHistoryEntity.setShfdh(purchaseOrderResult.getShfdh());
		purchaseOrderHistoryEntity.setShfcz(purchaseOrderResult.getShfcz());
		purchaseOrderHistoryEntity.setCjr(cjr);
		purchaseOrderHistoryEntity.setCjrq(nowDate);
		purchaseDao.save(purchaseOrderHistoryEntity);
		
		PurchaseOrderDetailEntity purchaseOrderDetailEntity = null;
		//  定义订单历史明细
		PurchaseOrderDetailHistoryEntity purchaseOrderDetailHistoryEntity = null;
		
		List<String> purchaseApplyDetailIdList = new ArrayList<String>();
		String cgsqmxdm = null;  //  采购申请明细ID
		String cgddmxdm = null;  //  采购订单明细代码
		BigDecimal ydgsl = null;
		for (PurchaseOrderDetailResult purchaseOrderDetailResult : purchaseOrderDetailResultList) {
			cgsqmxdm = purchaseOrderDetailResult.getCgsqmxdm();
			cgddmxdm = tool.UUID.currentTimeMillisAsRandomNumber();
			purchaseOrderDetailEntity = new PurchaseOrderDetailEntity();
			BeanUtils.copyProperties(purchaseOrderDetailResult, purchaseOrderDetailEntity);

			purchaseOrderDetailEntity.setDm(cgddmxdm);
			purchaseOrderDetailEntity.setCgdddm(poId);
			purchaseOrderDetailEntity.setYdhsl(new BigDecimal(0));
			purchaseOrderDetailEntity.setCjr(cjr);
			purchaseOrderDetailEntity.setCjrq(nowDate);
			purchaseDao.save(purchaseOrderDetailEntity);
			
			ydgsl = purchaseOrderDetailResult.getYdgsl().add(purchaseOrderDetailResult.getDgsl());
			if (purchaseOrderDetailResult.getSqsl().compareTo(ydgsl) <= 0) {
				//  申请数量等于已订购数量  删除采购申请表中的记录
				purchaseApplyDetailIdList.add(cgsqmxdm);
			}else {
				//  如采购数量小于申请数量，修改申请明细中的已采购数量
				purchaseDao.executeSql("update tbl_purchase_apply_detail set ycgsl = "+ydgsl+" where dm = '"+cgsqmxdm+"'");
			}
			//  修改申请历史实际采购数量
			purchaseDao.executeSql("update tbl_purchase_apply_detail_history set sjcgsl = "+ydgsl+" where dm = '"+cgsqmxdm+"'");
			
			//  创建订单历史明细
			purchaseOrderDetailHistoryEntity = new PurchaseOrderDetailHistoryEntity();
			purchaseOrderDetailHistoryEntity.setDm(cgddmxdm);
			purchaseOrderDetailHistoryEntity.setCgddlsdm(poId);
			purchaseOrderDetailHistoryEntity.setCgsqmxlsdm(cgsqmxdm);
			purchaseOrderDetailHistoryEntity.setWldm(purchaseOrderDetailResult.getWldm());
			purchaseOrderDetailHistoryEntity.setWlmc(purchaseOrderDetailResult.getWlmc());
			purchaseOrderDetailHistoryEntity.setWlgg(purchaseOrderDetailResult.getWlgg());
			purchaseOrderDetailHistoryEntity.setWldw(purchaseOrderDetailResult.getWldw());
			purchaseOrderDetailHistoryEntity.setDj(purchaseOrderDetailResult.getDj());
			purchaseOrderDetailHistoryEntity.setSqsl(purchaseOrderDetailResult.getSqsl());
			purchaseOrderDetailHistoryEntity.setDgsl(purchaseOrderDetailResult.getDgsl());
			purchaseOrderDetailHistoryEntity.setDhsl(new BigDecimal(0));
			purchaseOrderDetailHistoryEntity.setCjr(cjr);
			purchaseOrderDetailHistoryEntity.setCjrq(nowDate);
			purchaseDao.save(purchaseOrderDetailHistoryEntity);
		}
		
		//  删除采购申请明细中的记录，表示该明细已订购
		purchaseDao.executeSql("delete from tbl_purchase_apply_detail where dm in "+sqlStringInCondition(purchaseApplyDetailIdList));
		//  删除已完成的采购申请，即在采购申请明细中已没有记录的采购申请
		purchaseDao.executeSql("delete from tbl_purchase_apply where dm not in (select cgsqdm from tbl_purchase_apply_detail group by cgsqdm)");
		return po != null && !po.equals("");
	}
	
	@Override
	public PurchaseOrderStorePageResult findPurchaseOrderStorePageResult(String gysmc, Integer start, Integer limit) {
		StorePageResultTo storePageResultTo = purchaseDao.searchStorePageSql("select po.dm, s.gysmc, po.shf ", 
				" from tbl_purchase_order po left join tbl_supplier s on po.gysdm = s.dm where 1=1 " +
				spellingSql("and s.gysmc like '%", gysmc, "%' "), start, limit);
		List<PurchaseOrderResult> purchaseOrderList = new ArrayList<PurchaseOrderResult>();
		PurchaseOrderResult purchaseOrderResult = null;
		Object[] objs = null;
		for(Object obj : storePageResultTo.getItemList()){
			objs = (Object[]) obj;
			purchaseOrderResult = new PurchaseOrderResult();
			purchaseOrderResult.setDm(String.valueOf(objs[0]));
			purchaseOrderResult.setGysmc(String.valueOf(objs[1]));
			purchaseOrderResult.setShf(String.valueOf(objs[2]));
			purchaseOrderList.add(purchaseOrderResult);
		}
		return new PurchaseOrderStorePageResult(storePageResultTo.getItemTotal(), purchaseOrderList);
	}

	@Override
	public PurchaseOrderResult findPurchaseOrderResultByPoId(String purchaseOrderId) {
		List<Object[]> objsList = purchaseDao.searchAllSql("select po.dm, s.gysmc, po.shf, po.shflxr, po.shfdh " +
				"from tbl_purchase_order po left join tbl_supplier s on po.gysdm = s.dm where po.dm = '"+purchaseOrderId+"'");
		PurchaseOrderResult purchaseOrderResult = new PurchaseOrderResult();
		if(objsList.size() > 0){
			Object[] objects = objsList.get(0);
			purchaseOrderResult.setDm(String.valueOf(objects[0]));
			purchaseOrderResult.setGysmc(String.valueOf(objects[1]));
			purchaseOrderResult.setShf(String.valueOf(objects[2]));
			purchaseOrderResult.setShflxr(String.valueOf(objects[3]));
			purchaseOrderResult.setShfdh(String.valueOf(objects[4]));
		}
		return purchaseOrderResult;
	}

	@Override
	public List<PurchaseOrderDetailResult> findPurchaseOrderDetailListByPoId(String purchaseOrderId) {
		List<Object[]> objsList = purchaseDao.searchAllSql("select pod.dm, pod.wldm, pod.dj, pod.dgsl, m.mc, pod.ydhsl " +
				"from tbl_purchase_order_detail pod left join tbl_material m on pod.wldm = m.dm where pod.cgdddm = '"+
				purchaseOrderId+"' ");
		List<PurchaseOrderDetailResult> purchaseOrderDetailList = new ArrayList<PurchaseOrderDetailResult>();
		PurchaseOrderDetailResult purchaseOrderDetailResult = null;
		for(Object[] objs : objsList){
			purchaseOrderDetailResult = new PurchaseOrderDetailResult();
			purchaseOrderDetailResult.setDm(String.valueOf(objs[0]));
			purchaseOrderDetailResult.setWldm(String.valueOf(objs[1]));
			purchaseOrderDetailResult.setDj(new BigDecimal(String.valueOf(objs[2])));
			purchaseOrderDetailResult.setDgsl(new BigDecimal(String.valueOf(objs[3])));
			purchaseOrderDetailResult.setWlmc(String.valueOf(objs[4]));
			purchaseOrderDetailResult.setYdhsl(new BigDecimal(String.valueOf(objs[5])));
			purchaseOrderDetailList.add(purchaseOrderDetailResult);
		}
		return purchaseOrderDetailList;
	}

	@Override
	public PurchaseOrderHistoryStorePageResult findPurchaseOrderHistoryStorePageResult(
			String gysmc, Integer start, Integer limit) {
		StorePageResultTo storePageResultTo = purchaseDao.searchStorePageSql(
				"select dm,gysdm,gysmc,gysdh,gyscz,shf,shflxr,shfdh,shfcz,cjrq ", 
				" from tbl_purchase_order_history where 1=1 " +	spellingSql("and gysmc like '%", gysmc, "%' "), start, limit);
		List<PurchaseOrderHistoryEntity> purchaseOrderHistoryList = new ArrayList<PurchaseOrderHistoryEntity>();
		PurchaseOrderHistoryEntity purchaseOrderHistoryEntity = null;
		Object[] objs = null;
		for(Object obj : storePageResultTo.getItemList()){
			objs = (Object[]) obj;
			purchaseOrderHistoryEntity = new PurchaseOrderHistoryEntity();
			purchaseOrderHistoryEntity.setDm(String.valueOf(objs[0]));
			purchaseOrderHistoryEntity.setGysdm(String.valueOf(objs[1]));
			purchaseOrderHistoryEntity.setGysmc(String.valueOf(objs[2]));
			purchaseOrderHistoryEntity.setGysdh(String.valueOf(objs[3]));
			purchaseOrderHistoryEntity.setGyscz(String.valueOf(objs[4]));
			purchaseOrderHistoryEntity.setShf(String.valueOf(objs[5]));
			purchaseOrderHistoryEntity.setShflxr(String.valueOf(objs[6]));
			purchaseOrderHistoryEntity.setShfdh(String.valueOf(objs[7]));
			purchaseOrderHistoryEntity.setShfcz(String.valueOf(objs[8]));
			purchaseOrderHistoryEntity.setCjrq(stringToDate(String.valueOf(objs[9]), DBDATEFORMAT));
			purchaseOrderHistoryList.add(purchaseOrderHistoryEntity);
		}
		return new PurchaseOrderHistoryStorePageResult(storePageResultTo.getItemTotal(), purchaseOrderHistoryList);
	}

	@Override
	public List<PurchaseOrderDetailHistoryEntity> findPurchaseOrderDetailHistoryListByPohId(String purchaseOrderHistoryId) {
		List<Object[]> objsList = purchaseDao.searchAllSql(
				"select dm,cgddlsdm,wldm,wlmc,wlgg,wldw,dj,sqsl,dgsl,dhsl,bz from tbl_purchase_order_detail_history where cgddlsdm = '"+purchaseOrderHistoryId+"'");
		List<PurchaseOrderDetailHistoryEntity>  purchaseOrderDetailHistoryList = new ArrayList<PurchaseOrderDetailHistoryEntity>();
		PurchaseOrderDetailHistoryEntity purchaseOrderDetailHistoryEntity = null;
		for(Object[] objs : objsList){
			purchaseOrderDetailHistoryEntity = new PurchaseOrderDetailHistoryEntity();
			purchaseOrderDetailHistoryEntity.setDm(String.valueOf(objs[0]));
			purchaseOrderDetailHistoryEntity.setCgddlsdm(String.valueOf(objs[1]));
			purchaseOrderDetailHistoryEntity.setWldm(String.valueOf(objs[2]));
			purchaseOrderDetailHistoryEntity.setWlmc(String.valueOf(objs[3]));
			purchaseOrderDetailHistoryEntity.setWlgg(String.valueOf(objs[4]));
			purchaseOrderDetailHistoryEntity.setWldw(String.valueOf(objs[5]));
			purchaseOrderDetailHistoryEntity.setDj(new BigDecimal(String.valueOf(objs[6])));
			purchaseOrderDetailHistoryEntity.setSqsl(new BigDecimal(String.valueOf(objs[7])));
			purchaseOrderDetailHistoryEntity.setDgsl(new BigDecimal(String.valueOf(objs[8])));
			purchaseOrderDetailHistoryEntity.setDhsl(new BigDecimal(String.valueOf(objs[9])));
			purchaseOrderDetailHistoryEntity.setBz(String.valueOf(objs[10]));
			purchaseOrderDetailHistoryList.add(purchaseOrderDetailHistoryEntity);
		}
		return purchaseOrderDetailHistoryList;
	}

	@Override
	public PutWarehouseHistoryStorePageResult findPutWarehouseHistoryStorePageResult(String rkbmmc, Integer start, Integer limit) {
		StorePageResultTo storePageResultTo = purchaseDao.searchStorePageSql(
				"select dm,rkbmdm,rkbmmc,rklb,sccj,bz,cjrq ", 
				" from tbl_put_warehouse_history where 1=1 " +	spellingSql("and rkbmmc like '%", rkbmmc, "%' "), start, limit);
		List<PutWarehouseHistoryEntity> putWarehouseHistoryList = new ArrayList<PutWarehouseHistoryEntity>();
		PutWarehouseHistoryEntity putWarehouseHistoryEntity = null;
		Object[] objs = null;
		for(Object obj : storePageResultTo.getItemList()){
			objs = (Object[]) obj;
			putWarehouseHistoryEntity = new PutWarehouseHistoryEntity();
			putWarehouseHistoryEntity.setDm(String.valueOf(objs[0]));
			putWarehouseHistoryEntity.setRkbmdm(String.valueOf(objs[1]));
			putWarehouseHistoryEntity.setRkbmmc(String.valueOf(objs[2]));
			putWarehouseHistoryEntity.setRklb(String.valueOf(objs[3]));
			putWarehouseHistoryEntity.setSccj(String.valueOf(objs[4]));
			putWarehouseHistoryEntity.setBz(String.valueOf(objs[5]));
			putWarehouseHistoryEntity.setCjrq(stringToDate(String.valueOf(objs[6]), DBDATEFORMAT));
			putWarehouseHistoryList.add(putWarehouseHistoryEntity);
		}
		return new PutWarehouseHistoryStorePageResult(storePageResultTo.getItemTotal(), putWarehouseHistoryList);
	}

	@Override
	public List<PutWarehouseDetailHistoryEntity> findPutWarehouseDetailHistoryList(String putWarehouseHistoryId) {
		List<Object[]> objsList = purchaseDao.searchAllSql("select dm,rklsdm,cgsqmxlsdm,wldm,wlmc,rksl,jyjg,bz from tbl_put_warehouse_detail_history where rklsdm = '"+putWarehouseHistoryId+"'");
		List<PutWarehouseDetailHistoryEntity> putWarehouseDetailHistoryList = new ArrayList<PutWarehouseDetailHistoryEntity>();
		PutWarehouseDetailHistoryEntity putWarehouseDetailHistoryEntity = null;
		for(Object[] objs : objsList){
			putWarehouseDetailHistoryEntity = new PutWarehouseDetailHistoryEntity();
			putWarehouseDetailHistoryEntity.setDm(String.valueOf(objs[0]));
			putWarehouseDetailHistoryEntity.setRklsdm(String.valueOf(objs[1]));
			putWarehouseDetailHistoryEntity.setCgsqmxlsdm(String.valueOf(objs[2]));
			putWarehouseDetailHistoryEntity.setWldm(String.valueOf(objs[3]));
			putWarehouseDetailHistoryEntity.setWlmc(String.valueOf(objs[4]));
			putWarehouseDetailHistoryEntity.setRksl(new BigDecimal(String.valueOf(objs[5])));
			putWarehouseDetailHistoryEntity.setJyjg(String.valueOf(objs[6]));
			putWarehouseDetailHistoryEntity.setBz(String.valueOf(objs[7]));
			putWarehouseDetailHistoryList.add(putWarehouseDetailHistoryEntity);
		}
		return putWarehouseDetailHistoryList;
	}
	
	@Override
	public PurchaseOrderDetailStorePageResult findPurchaseOrderDetailStorePageResult(String gysmc, Integer start, Integer limit) {
		String listSql = "select t.dm, t.cgsqmxdm, t.sqsl, t.dgsl, t.ydhsl, t.wldm, m.mc, t.gysmc from " +
			"(select pod.dm, pod.cgsqmxdm, pod.sqsl, pod.dgsl, pod.ydhsl, pod.wldm, s.gysmc " +
			"from tbl_purchase_order_detail pod left join tbl_purchase_order po on pod.cgdddm = po.dm " +
			"left join tbl_supplier s on po.gysdm = s.dm " +
			"where 1=1 " + spellingSql("and s.gysmc like '%", gysmc, "%' ") + " limit "+start+","+limit+") t " +
			"left join tbl_material m on t.wldm = m.dm";
		
		String countSql = "select count(*) as " + SQLASNAMEITEMCOUNT + 
				" from tbl_purchase_order_detail pod left join tbl_purchase_order po on pod.cgdddm = po.dm " +
				" left join tbl_supplier s on po.gysdm = s.dm where 1=1 " + spellingSql("and s.gysmc like '%", gysmc, "%' ");

		StorePageResultTo storePageResultTo = purchaseDao.searchStorePageSql(listSql, countSql);
		List<PurchaseOrderDetailResult> purchaseOrderDetailList = new ArrayList<PurchaseOrderDetailResult>();
		PurchaseOrderDetailResult purchaseOrderDetailResult = null;
		Object[] objs = null;
		for(Object obj : storePageResultTo.getItemList()){
			objs = (Object[]) obj;
			purchaseOrderDetailResult = new PurchaseOrderDetailResult();
			purchaseOrderDetailResult.setDm(String.valueOf(objs[0]));
			purchaseOrderDetailResult.setCgsqmxdm(String.valueOf(objs[1]));
			purchaseOrderDetailResult.setSqsl(new BigDecimal(String.valueOf(objs[2])));
			purchaseOrderDetailResult.setDgsl(new BigDecimal(String.valueOf(objs[3])));
			purchaseOrderDetailResult.setYdhsl(new BigDecimal(String.valueOf(objs[4])));
			purchaseOrderDetailResult.setWldm(String.valueOf(objs[5]));
			purchaseOrderDetailResult.setWlmc(String.valueOf(objs[6]));
			purchaseOrderDetailResult.setGysmc(String.valueOf(objs[7]));
			purchaseOrderDetailList.add(purchaseOrderDetailResult);
		}
		return new PurchaseOrderDetailStorePageResult(storePageResultTo.getItemTotal(), purchaseOrderDetailList);
	}
	
	@Override
	public Boolean createPurchaseOrderArrivalsGoods(List<PurchaseArrivalsGoodsDetailResult> purchaseArrivalsGoodsDetailResultList, String cjr, String xgr) {
		if (purchaseArrivalsGoodsDetailResultList == null || purchaseArrivalsGoodsDetailResultList.size() == 0) {
			return true;
		}
		Date nowDate = new Date();
		String pagId = tool.UUID.currentTimeMillisAsRandomNumber();  //  到货记录主键
		PurchaseArrivalsGoodsEntity purchaseArrivalsGoodsEntity = new PurchaseArrivalsGoodsEntity();
		purchaseArrivalsGoodsEntity.setDm(pagId);
		purchaseArrivalsGoodsEntity.setCjr(cjr);
		purchaseArrivalsGoodsEntity.setCjrq(nowDate);
		Serializable pagSerializable = purchaseDao.save(purchaseArrivalsGoodsEntity);
		
		PurchaseArrivalsGoodsHistoryEntity purchaseArrivalsGoodsHistoryEntity = new PurchaseArrivalsGoodsHistoryEntity();
		purchaseArrivalsGoodsHistoryEntity.setDm(pagId);
		purchaseArrivalsGoodsHistoryEntity.setCjr(cjr);
		purchaseArrivalsGoodsHistoryEntity.setCjrq(nowDate);
		purchaseDao.save(purchaseArrivalsGoodsHistoryEntity);
		
		PurchaseArrivalsGoodsDetailEntity purchaseArrivalsGoodsDetailEntity = null;
		PurchaseArrivalsGoodsDetailHistoryEntity purchaseArrivalsGoodsDetailHistoryEntity = null;
		String pagdId = null;
		BigDecimal ydhsl = null;
		String cgddmmdm = null;
		List<String> deleOrderDetailList = new ArrayList<String>();
		for(PurchaseArrivalsGoodsDetailResult purchaseArrivalsGoodsDetailResult : purchaseArrivalsGoodsDetailResultList){
			pagdId = tool.UUID.currentTimeMillisAsRandomNumber();
			cgddmmdm = purchaseArrivalsGoodsDetailResult.getCgddmxdm();
			purchaseArrivalsGoodsDetailEntity = new PurchaseArrivalsGoodsDetailEntity();
			BeanUtils.copyProperties(purchaseArrivalsGoodsDetailResult, purchaseArrivalsGoodsDetailEntity);
			purchaseArrivalsGoodsDetailEntity.setDm(pagdId);
			purchaseArrivalsGoodsDetailEntity.setDhdm(pagId);
			purchaseArrivalsGoodsDetailEntity.setYrksl(new BigDecimal(0));
			purchaseArrivalsGoodsDetailEntity.setCjr(cjr);
			purchaseArrivalsGoodsDetailEntity.setCjrq(nowDate);
			purchaseDao.save(purchaseArrivalsGoodsDetailEntity);
			
			ydhsl = purchaseArrivalsGoodsDetailResult.getYdhsl().add(purchaseArrivalsGoodsDetailResult.getDhsl());
			if (purchaseArrivalsGoodsDetailResult.getDgsl().compareTo(ydhsl) <= 0) {
				//  到货数量等于采购数量，删除采购订单明细中的记录
				deleOrderDetailList.add(cgddmmdm);
			}else {
				//  到货数量，小于采购数量，修改订单明细表已到货数量
				purchaseDao.executeSql("update tbl_purchase_order_detail set ydhsl = "+ydhsl+" where dm = '"+cgddmmdm+"'");
			}
			//  修改采购订单历史明细记录
			purchaseDao.executeSql("update tbl_purchase_order_detail_history set dhsl = "+ydhsl+" where dm = '"+cgddmmdm+"'");
			
			purchaseArrivalsGoodsDetailHistoryEntity = new PurchaseArrivalsGoodsDetailHistoryEntity();
			BeanUtils.copyProperties(purchaseArrivalsGoodsDetailResult, purchaseArrivalsGoodsDetailHistoryEntity);
			purchaseArrivalsGoodsDetailHistoryEntity.setCgsqmxlsdm(purchaseArrivalsGoodsDetailResult.getCgsqmxdm());
			purchaseArrivalsGoodsDetailHistoryEntity.setCgddmxlsdm(purchaseArrivalsGoodsDetailResult.getCgddmxdm());
			purchaseArrivalsGoodsDetailHistoryEntity.setDm(pagdId);
			purchaseArrivalsGoodsDetailHistoryEntity.setDhlsdm(pagId);
			purchaseArrivalsGoodsDetailHistoryEntity.setRksl(new BigDecimal(0));
			purchaseArrivalsGoodsDetailHistoryEntity.setCjr(cjr);
			purchaseArrivalsGoodsDetailHistoryEntity.setCjrq(nowDate);
			purchaseDao.save(purchaseArrivalsGoodsDetailHistoryEntity);
		}
		//  删除订单明细表中到货数量等于采购数量的明细记录
		purchaseDao.executeSql("delete from tbl_purchase_order_detail where dm in "+sqlStringInCondition(deleOrderDetailList));
		//  删除已无明细记录的订单
		purchaseDao.executeSql("delete from tbl_purchase_order where dm not in (select cgdddm from tbl_purchase_order_detail group by cgdddm)");
		return pagSerializable != null && !pagSerializable.equals("");
	}

	@Override
	public PurchaseArrivalsGoodsHistoryStorePageResult findPurchaseArrivalsGoodsHistoryStorePageResult(Integer start, Integer limit) {
		StorePageResultTo storePageResultTo = purchaseDao.searchStorePageSql("select dm, cjrq, cjr ", 
				" from tbl_purchase_arrivals_goods_history where 1=1 ", start, limit);
		List<PurchaseArrivalsGoodsHistoryEntity> purchaseArrivalsGoodsHistoryList = new ArrayList<PurchaseArrivalsGoodsHistoryEntity>();
		PurchaseArrivalsGoodsHistoryEntity purchaseArrivalsGoodsHistoryEntity = null;
		Object[] objs = null;
		for(Object obj : storePageResultTo.getItemList()){
			objs = (Object[]) obj;
			purchaseArrivalsGoodsHistoryEntity = new PurchaseArrivalsGoodsHistoryEntity();
			purchaseArrivalsGoodsHistoryEntity.setDm(String.valueOf(objs[0]));
			purchaseArrivalsGoodsHistoryEntity.setCjrq(stringToDate(String.valueOf(objs[1]), DBDATEFORMAT));
			purchaseArrivalsGoodsHistoryEntity.setCjr(String.valueOf(objs[2]));
			purchaseArrivalsGoodsHistoryList.add(purchaseArrivalsGoodsHistoryEntity);
		}
		return new PurchaseArrivalsGoodsHistoryStorePageResult(storePageResultTo.getItemTotal(), purchaseArrivalsGoodsHistoryList);
	}

	@Override
	public List<PurchaseArrivalsGoodsDetailHistoryEntity> findPurchaseArrivalsGoodsDetailHistoryListByAgdhId(String arrivalsGoodsHistoryId) {
		List<Object[]> objsList = purchaseDao.searchAllSql("select dm, cgsqmxlsdm, cgddmxlsdm, wldm, wlmc, sqsl, dgsl, dhsl, rksl, cjrq, cjr from tbl_purchase_arrivals_goods_detail_history where dhlsdm = '"+arrivalsGoodsHistoryId+"'");
		List<PurchaseArrivalsGoodsDetailHistoryEntity> purchaseArrivalsGoodsDetailHistoryList = new ArrayList<PurchaseArrivalsGoodsDetailHistoryEntity>();
		PurchaseArrivalsGoodsDetailHistoryEntity purchaseArrivalsGoodsDetailHistoryEntity = null;
		for(Object[] objs : objsList){
			purchaseArrivalsGoodsDetailHistoryEntity = new PurchaseArrivalsGoodsDetailHistoryEntity();
			purchaseArrivalsGoodsDetailHistoryEntity.setDm(String.valueOf(objs[0]));
			purchaseArrivalsGoodsDetailHistoryEntity.setDhlsdm(arrivalsGoodsHistoryId);
			purchaseArrivalsGoodsDetailHistoryEntity.setCgsqmxlsdm(String.valueOf(objs[1]));
			purchaseArrivalsGoodsDetailHistoryEntity.setCgddmxlsdm(String.valueOf(objs[2]));
			purchaseArrivalsGoodsDetailHistoryEntity.setWldm(String.valueOf(objs[3]));
			purchaseArrivalsGoodsDetailHistoryEntity.setWlmc(String.valueOf(objs[4]));
			purchaseArrivalsGoodsDetailHistoryEntity.setSqsl(new BigDecimal(String.valueOf(objs[5])));
			purchaseArrivalsGoodsDetailHistoryEntity.setDgsl(new BigDecimal(String.valueOf(objs[6])));
			purchaseArrivalsGoodsDetailHistoryEntity.setDhsl(new BigDecimal(String.valueOf(objs[7])));
			purchaseArrivalsGoodsDetailHistoryEntity.setRksl(new BigDecimal(String.valueOf(objs[8])));
			purchaseArrivalsGoodsDetailHistoryEntity.setCjrq(stringToDate(String.valueOf(objs[9]), DBDATEFORMAT));
			purchaseArrivalsGoodsDetailHistoryEntity.setCjr(String.valueOf(objs[10]));
			purchaseArrivalsGoodsDetailHistoryList.add(purchaseArrivalsGoodsDetailHistoryEntity);
		}
		
		return purchaseArrivalsGoodsDetailHistoryList;
	}

}
