package proope.quality.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import proope.produce.to.entity.ProductPutWarehouseDetailHistoryEntity;
import proope.produce.to.entity.ProductPutWarehouseHistoryEntity;
import proope.produce.to.result.ProductPutWarehouseApplyDetailResult;
import proope.produce.to.result.ProductPutWarehouseApplyDetailStorePageResult;
import proope.produce.to.result.ProductPutWarehouseDetailHistoryResult;
import proope.quality.QualityDao;
import proope.quality.service.ProduceQualityService;
import proope.warehouse.to.entity.ProductInventoryEntity;
import common.hibernate.BaseService;
import common.to.StorePageResultTo;

public class ProduceQualityServiceImpl extends BaseService implements ProduceQualityService {
	private QualityDao qualityDao;
	public void setQualityDao(QualityDao qualityDao) {
		this.qualityDao = qualityDao;
	}
	
	@Override
	public ProductPutWarehouseApplyDetailStorePageResult findWaitCheckProductPutWarehouseApplyDetailStorePageResult(String cpmc, Integer start, Integer limit) {
		StorePageResultTo storePageResultTo = qualityDao.searchStorePageSql("select ppwd.dm, ppwd.cprksqdm, ppwd.cpdm, p.cpmc, ppwd.sqsl, ppwd.yrksl ", 
				" from tbl_product_put_warehouse_apply_detail ppwd left join tbl_product p on ppwd.cpdm = p.dm " +
		" where 1=1 " + spellingSql("and p.cpmc like '%", cpmc, "%' "), start, limit);
		List<ProductPutWarehouseApplyDetailResult> productPutWarehouseDetailList = new ArrayList<ProductPutWarehouseApplyDetailResult>();
		ProductPutWarehouseApplyDetailResult productPutWarehouseDetailResult = null;
		Object[] objs = null;
		for(Object obj : storePageResultTo.getItemList()){
			objs = (Object[]) obj;
			productPutWarehouseDetailResult = new ProductPutWarehouseApplyDetailResult();
			productPutWarehouseDetailResult.setDm(String.valueOf(objs[0]));
			productPutWarehouseDetailResult.setCprksqdm(String.valueOf(objs[1]));
			productPutWarehouseDetailResult.setCpdm(String.valueOf(objs[2]));
			productPutWarehouseDetailResult.setCpmc(String.valueOf(objs[3]));
			productPutWarehouseDetailResult.setSqsl(new BigDecimal(String.valueOf(objs[4])));
			productPutWarehouseDetailResult.setYrksl(new BigDecimal(String.valueOf(objs[5])));
			productPutWarehouseDetailList.add(productPutWarehouseDetailResult);
		}
		return new ProductPutWarehouseApplyDetailStorePageResult(storePageResultTo.getItemTotal(), productPutWarehouseDetailList);
	}

	@Override
	public Boolean createProductPutWarehouseHistory(ProductPutWarehouseHistoryEntity productPutWarehouseHistoryEntity,
			List<ProductPutWarehouseDetailHistoryResult> productPutWarehouseDetailHistoryResultList, String cjr) {
		List<String> cpdmList = new ArrayList<String>();
		for (ProductPutWarehouseDetailHistoryResult productPutWarehouseDetailHistoryResult : productPutWarehouseDetailHistoryResultList) {
			cpdmList.add(productPutWarehouseDetailHistoryResult.getCpdm());
		}
		List<ProductInventoryEntity> productInventoryList = findProductInventoryListByCpdmList(cpdmList);
		
		String ppwhId = tool.UUID.currentTimeMillisAsRandomNumber();
		Date nowDate = new Date();
		productPutWarehouseHistoryEntity.setDm(ppwhId);
		productPutWarehouseHistoryEntity.setCjr(cjr);
		productPutWarehouseHistoryEntity.setCjrq(nowDate);
		Serializable ppwhSerializable = qualityDao.save(productPutWarehouseHistoryEntity);
		List<String> deleIdList = new ArrayList<String>();
		String cpdm = null;  //  产品代码
		String rksqmxdm = null;  //  入库申请明细代码
		BigDecimal rkzl = null;  //  入库总量
		ProductInventoryEntity productInventoryEntity = null;  //  产品库存量
		ProductPutWarehouseDetailHistoryEntity productPutWarehouseDetailHistoryEntity = null;
		for (ProductPutWarehouseDetailHistoryResult productPutWarehouseDetailHistoryResult : productPutWarehouseDetailHistoryResultList) {
			rksqmxdm = productPutWarehouseDetailHistoryResult.getRksqmxlldm();
			cpdm = productPutWarehouseDetailHistoryResult.getCpdm();
			rkzl = productPutWarehouseDetailHistoryResult.getYrksl().add(productPutWarehouseDetailHistoryResult.getSl());
			
			productPutWarehouseDetailHistoryEntity = new ProductPutWarehouseDetailHistoryEntity();
			BeanUtils.copyProperties(productPutWarehouseDetailHistoryResult, productPutWarehouseDetailHistoryEntity);
			productPutWarehouseDetailHistoryEntity.setDm(tool.UUID.currentTimeMillisAsRandomNumber());
			productPutWarehouseDetailHistoryEntity.setCprklsdm(ppwhId);
			productPutWarehouseDetailHistoryEntity.setCjr(cjr);
			productPutWarehouseDetailHistoryEntity.setCjrq(nowDate);
			qualityDao.save(productPutWarehouseDetailHistoryEntity);
			
			productInventoryEntity = findProductInventoryEntityFromListByCpdm(productInventoryList, cpdm);
			if(productInventoryEntity == null){
				//  产品库存中没有产品记录，添加
				productInventoryEntity= new ProductInventoryEntity();
				productInventoryEntity.setCpdm(cpdm);
				productInventoryEntity.setSl(productPutWarehouseDetailHistoryResult.getSl());
				productInventoryEntity.setCjr(cjr);
				productInventoryEntity.setCjrq(nowDate);
				productInventoryEntity.setXgr(cjr);
				productInventoryEntity.setXgrq(nowDate);
				qualityDao.save(productInventoryEntity);
				productInventoryList.add(productInventoryEntity);
			}else{
				//  产品库存中已有产品记录，修改数量
				productInventoryEntity.setSl(productInventoryEntity.getSl().add(productPutWarehouseDetailHistoryResult.getSl()));
				productInventoryEntity.setXgr(cjr);
				productInventoryEntity.setXgrq(nowDate);
				qualityDao.update(productInventoryEntity);
			}
			
			if (rkzl.compareTo(productPutWarehouseDetailHistoryResult.getSqsl()) >= 0) {
				deleIdList.add(rksqmxdm);
			}else{
				qualityDao.executeSql("update tbl_product_put_warehouse_apply_detail set yrksl = "+rkzl+" where dm = '"+rksqmxdm+"'");
			}
			qualityDao.executeSql("update tbl_product_put_warehouse_apply_detail_history set rksl = "+rkzl+" where dm = '"+rksqmxdm+"'");
		}
		qualityDao.executeSql("delete from tbl_product_put_warehouse_apply_detail where dm in " + sqlStringInCondition(deleIdList));
		qualityDao.executeSql("delete from tbl_product_put_warehouse_apply where dm not in (select cprksqdm from tbl_product_put_warehouse_apply_detail group by cprksqdm)");
		return ppwhSerializable != null && !"".equals(ppwhSerializable);
	}
	
	private List<ProductInventoryEntity> findProductInventoryListByCpdmList(List<String> cpdmList){
		List<Object[]> objsList = qualityDao.searchAllSql("select cpdm, sl, cjrq, cjr, xgrq, xgr from tbl_product_inventory where cpdm in "+sqlStringInCondition(cpdmList));
		List<ProductInventoryEntity> productInventoryList = new ArrayList<ProductInventoryEntity>();
		ProductInventoryEntity productInventoryEntity = null;
		for (Object[] objects : objsList) {
			productInventoryEntity = new ProductInventoryEntity();
			productInventoryEntity.setCpdm(String.valueOf(objects[0]));
			productInventoryEntity.setSl(new BigDecimal(String.valueOf(objects[1])));
			productInventoryEntity.setCjrq(stringToDate(String.valueOf(objects[2]), DBDATEFORMAT));
			productInventoryEntity.setCjr(String.valueOf(objects[3]));
			productInventoryEntity.setXgrq(stringToDate(String.valueOf(objects[4]), DBDATEFORMAT));
			productInventoryEntity.setXgr(String.valueOf(objects[5]));
			productInventoryList.add(productInventoryEntity);
		}
		return productInventoryList;
	}
	
	private ProductInventoryEntity findProductInventoryEntityFromListByCpdm(List<ProductInventoryEntity> productInventoryList, String cpdm){
		for(ProductInventoryEntity productInventoryEntity : productInventoryList){
			if (cpdm.equals(productInventoryEntity.getCpdm())) {
				return productInventoryEntity;
			}
		}
		return null;
	}

}
