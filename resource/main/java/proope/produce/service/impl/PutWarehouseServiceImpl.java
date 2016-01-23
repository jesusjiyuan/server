package proope.produce.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.BeanUtils;
import proope.produce.ProduceDao;
import proope.produce.service.PutWarehouseService;
import proope.produce.to.entity.ProductPutWarehouseApplyDetailEntity;
import proope.produce.to.entity.ProductPutWarehouseApplyDetailHistoryEntity;
import proope.produce.to.entity.ProductPutWarehouseApplyEntity;
import proope.produce.to.entity.ProductPutWarehouseApplyHistoryEntity;
import proope.produce.to.entity.ProductPutWarehouseDetailHistoryEntity;
import proope.produce.to.entity.ProductPutWarehouseHistoryEntity;
import proope.produce.to.result.ProductPutWarehouseApplyDetailResult;
import proope.produce.to.result.ProductPutWarehouseApplyResult;
import proope.produce.to.result.ProductPutWarehouseApplyStorePageResult;
import proope.produce.to.result.ProductPutWarehouseHistoryStorePageResult;
import common.hibernate.BaseService;
import common.to.StorePageResultTo;

public class PutWarehouseServiceImpl extends BaseService implements PutWarehouseService {

	private ProduceDao produceDao;

	public void setProduceDao(ProduceDao produceDao) {
		this.produceDao = produceDao;
	}

	@Override
	public ProductPutWarehouseApplyStorePageResult findProductPutWarehouseApplyStorePageResult(String bmmc, Integer start, Integer limit) {
		StorePageResultTo storePageResultTo = produceDao.searchStorePageSql("select ppwa.dm, d.bmmc, ppwa.cjrq ", 
				" from tbl_product_put_warehouse_apply ppwa left join tbl_department d on ppwa.bmdm = d.dm where 1=1 " +
				spellingSql("and d.bmmc like '%", bmmc, "%'"), start, limit);
		List<ProductPutWarehouseApplyResult> productPutWarehouseApplyList = new ArrayList<ProductPutWarehouseApplyResult>();
		ProductPutWarehouseApplyResult productPutWarehouseApplyResult = null;
		Object[] objs = null;
		for(Object obj : storePageResultTo.getItemList()){
			objs = (Object[]) obj;
			productPutWarehouseApplyResult = new ProductPutWarehouseApplyResult();
			productPutWarehouseApplyResult.setDm(String.valueOf(objs[0]));
			productPutWarehouseApplyResult.setBmmc(String.valueOf(objs[1]));
			productPutWarehouseApplyResult.setCjrq(stringToDate(String.valueOf(objs[2]), DBDATEFORMAT));
			productPutWarehouseApplyList.add(productPutWarehouseApplyResult);
		}
		return new ProductPutWarehouseApplyStorePageResult(storePageResultTo.getItemTotal(), productPutWarehouseApplyList);
	}
	
	public List<ProductPutWarehouseApplyDetailResult> findProductPutWarehouseApplyDetailResultListByPpwaId(String productPutWarehouseApplyId){
		List<Object[]> objsList = produceDao.searchAllSql("select pwad.dm,pwad.cprksqdm,pwad.cpdm,p.cpmc, pwad.sqsl,pwad.yrksl,pwad.cjrq " +
		"from tbl_product_put_warehouse_apply_detail pwad left join tbl_product p on pwad.cpdm = p.dm	where pwad.cprksqdm = '"+productPutWarehouseApplyId+"'");
		List<ProductPutWarehouseApplyDetailResult> productPutWarehouseApplyDetailResultList = new ArrayList<ProductPutWarehouseApplyDetailResult>();
		ProductPutWarehouseApplyDetailResult productPutWarehouseApplyDetailResult = null;
		for(Object[] objs : objsList){
			productPutWarehouseApplyDetailResult = new ProductPutWarehouseApplyDetailResult();
			productPutWarehouseApplyDetailResult.setDm(String.valueOf(objs[0]));
			productPutWarehouseApplyDetailResult.setCprksqdm(String.valueOf(objs[1]));
			productPutWarehouseApplyDetailResult.setCpdm(String.valueOf(objs[2]));
			productPutWarehouseApplyDetailResult.setCpmc(String.valueOf(objs[3]));
			productPutWarehouseApplyDetailResult.setSqsl(new BigDecimal(String.valueOf(objs[4])));
			productPutWarehouseApplyDetailResult.setYrksl(new BigDecimal(String.valueOf(objs[5])));
			productPutWarehouseApplyDetailResult.setCjrq(stringToDate(String.valueOf(objs[6]), DBDATEFORMAT));
			productPutWarehouseApplyDetailResultList.add(productPutWarehouseApplyDetailResult);
		}
		return productPutWarehouseApplyDetailResultList;
	}
	
	@Override
	public Boolean createProductPutWarehouseApply(ProductPutWarehouseApplyHistoryEntity productPutWarehouseApplyHistoryEntity,
			List<ProductPutWarehouseApplyDetailHistoryEntity> productPutWarehouseApplyDetailHistoryEntityList, String cjr) {
		String ppwaId = tool.UUID.currentTimeMillisAsRandomNumber();
		Date nowDate = new Date();
		ProductPutWarehouseApplyEntity productPutWarehouseApplyEntity = new ProductPutWarehouseApplyEntity();
		BeanUtils.copyProperties(productPutWarehouseApplyHistoryEntity, productPutWarehouseApplyEntity);
		productPutWarehouseApplyEntity.setDm(ppwaId);
		productPutWarehouseApplyEntity.setCjr(cjr);
		productPutWarehouseApplyEntity.setCjrq(nowDate);
		Serializable ppwaSerializable = produceDao.save(productPutWarehouseApplyEntity);
		
		productPutWarehouseApplyHistoryEntity.setDm(ppwaId);
		productPutWarehouseApplyHistoryEntity.setCjr(cjr);
		productPutWarehouseApplyHistoryEntity.setCjrq(nowDate);
		produceDao.save(productPutWarehouseApplyHistoryEntity);
		
		String ppwadId = null;
		ProductPutWarehouseApplyDetailEntity productPutWarehouseApplyDetailEntity = null;
		for(ProductPutWarehouseApplyDetailHistoryEntity productPutWarehouseApplyDetailHistoryEntity : productPutWarehouseApplyDetailHistoryEntityList){
			productPutWarehouseApplyDetailEntity = new ProductPutWarehouseApplyDetailEntity();
			BeanUtils.copyProperties(productPutWarehouseApplyDetailHistoryEntity, productPutWarehouseApplyDetailEntity);
			ppwadId = tool.UUID.currentTimeMillisAsRandomNumber();
			
			productPutWarehouseApplyDetailEntity.setDm(ppwadId);
			productPutWarehouseApplyDetailEntity.setCprksqdm(ppwaId);
			productPutWarehouseApplyDetailEntity.setYrksl(new BigDecimal(0));
			productPutWarehouseApplyDetailEntity.setCjr(cjr);
			productPutWarehouseApplyDetailEntity.setCjrq(nowDate);
			produceDao.save(productPutWarehouseApplyDetailEntity);
			
			productPutWarehouseApplyDetailHistoryEntity.setDm(ppwadId);
			productPutWarehouseApplyDetailHistoryEntity.setCprksqlldm(ppwaId);
			productPutWarehouseApplyDetailHistoryEntity.setRksl(new BigDecimal(0));
			productPutWarehouseApplyDetailHistoryEntity.setCjr(cjr);
			productPutWarehouseApplyDetailHistoryEntity.setCjrq(nowDate);
			produceDao.save(productPutWarehouseApplyDetailHistoryEntity);
		}
		return ppwaSerializable != null && !"".equals(ppwaSerializable);
	}

	@Override
	public ProductPutWarehouseHistoryStorePageResult findProductPutWarehouseHistoryStorePageResult(String bmmc, Integer start, Integer limit) {
		List<SimpleExpression> simpleExpressionList = new ArrayList<SimpleExpression>();
		if(bmmc != null && !"".equals(bmmc)){
			simpleExpressionList.add(Restrictions.like("bmmc", "%"+bmmc+"%"));
		}
		StorePageResultTo storePageResultTo = produceDao.searchStorePageCriteria(ProductPutWarehouseHistoryEntity.class, start, limit, simpleExpressionList);
		List<ProductPutWarehouseHistoryEntity> productPutWarehouseHistoryList = new ArrayList<ProductPutWarehouseHistoryEntity>();
		for(Object obj : storePageResultTo.getItemList()){
			productPutWarehouseHistoryList.add((ProductPutWarehouseHistoryEntity)obj);
		}
		return new ProductPutWarehouseHistoryStorePageResult(new Long(2), productPutWarehouseHistoryList);
	}

	@Override
	public List<ProductPutWarehouseDetailHistoryEntity> findProductPutWarehouseDetailHistoryListByPpwhId(String productPutWarehouseHistoryId) {
		List<Object[]> objList = produceDao.searchAllSql("select dm,cprklsdm,rksqlldm,rksqmxlldm,cpdm,cpmc,sl,bz,cjrq,cjr from tbl_product_put_warehouse_detail_history where cprklsdm = '"+productPutWarehouseHistoryId+"'");
		List<ProductPutWarehouseDetailHistoryEntity> productPutWarehouseDetailHistoryEntityList = new ArrayList<ProductPutWarehouseDetailHistoryEntity>();
		ProductPutWarehouseDetailHistoryEntity productPutWarehouseDetailHistoryEntity = null;
		for(Object []objs : objList){
			productPutWarehouseDetailHistoryEntity = new ProductPutWarehouseDetailHistoryEntity();
			productPutWarehouseDetailHistoryEntity.setDm(String.valueOf(objs[0]));
			productPutWarehouseDetailHistoryEntity.setCprklsdm(String.valueOf(objs[1]));
			productPutWarehouseDetailHistoryEntity.setRksqlldm(String.valueOf(objs[2]));
			productPutWarehouseDetailHistoryEntity.setRksqmxlldm(String.valueOf(objs[3]));
			productPutWarehouseDetailHistoryEntity.setCpdm(String.valueOf(objs[4]));
			productPutWarehouseDetailHistoryEntity.setCpmc(String.valueOf(objs[5]));
			productPutWarehouseDetailHistoryEntity.setSl(new BigDecimal(String.valueOf(objs[6])));
			productPutWarehouseDetailHistoryEntity.setBz(String.valueOf(objs[7]));
			productPutWarehouseDetailHistoryEntity.setCjrq(stringToDate(String.valueOf(objs[8]), DBDATEFORMAT));
			productPutWarehouseDetailHistoryEntity.setCjr(String.valueOf(objs[9]));
			productPutWarehouseDetailHistoryEntityList.add(productPutWarehouseDetailHistoryEntity);
		}
		return productPutWarehouseDetailHistoryEntityList;
	}

}
