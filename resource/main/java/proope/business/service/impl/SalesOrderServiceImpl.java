package proope.business.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import proope.business.BusinessDao;
import proope.business.service.SalesOrderService;
import proope.business.to.entity.SalesOrderDetailEntity;
import proope.business.to.entity.SalesOrderDetailHistoryEntity;
import proope.business.to.entity.SalesOrderEntity;
import proope.business.to.entity.SalesOrderHistoryEntity;
import proope.business.to.entity.SalesOrderOutGoodsDetailHistoryEntity;
import proope.business.to.result.SalesOrderDetailResult;
import proope.business.to.result.SalesOrderOutGoodsDetailHistoryResult;
import proope.business.to.result.SalesOrderResult;
import proope.business.to.result.SalesOrderStorePageResult;
import common.hibernate.BaseService;
import common.to.StorePageResultTo;

public class SalesOrderServiceImpl extends BaseService implements SalesOrderService {

	private BusinessDao businessDao;
	public void setBusinessDao(BusinessDao businessDao) {
		this.businessDao = businessDao;
	}
	
	@Override
	public SalesOrderStorePageResult findNotSubmitSalesOrderStorePageResult(String khmc, Integer start, Integer limit) {
		StorePageResultTo storePageResultTo = businessDao.searchStorePageSql("select so.dm, so.khdm, c.khmc, so.cjrq ", 
				" from tbl_sales_order so left join tbl_client c on so.khdm = c.dm " +
		" where so.zt = 1 " + spellingSql("and c.khmc like '%", khmc, "%' "), start, limit);
		List<SalesOrderResult> salesOrderList = new ArrayList<SalesOrderResult>();
		SalesOrderResult salesOrderResult = null;
		Object[] objs = null;
		for(Object obj : storePageResultTo.getItemList()){
			objs = (Object[]) obj;
			salesOrderResult = new SalesOrderResult();
			salesOrderResult.setDm(String.valueOf(objs[0]));
			salesOrderResult.setKhdm(String.valueOf(objs[1]));
			salesOrderResult.setKhmc(String.valueOf(objs[2]));
			salesOrderResult.setCjrq(stringToDate(String.valueOf(objs[3]), DBDATEFORMAT));
			salesOrderList.add(salesOrderResult);
		}
		return new SalesOrderStorePageResult(storePageResultTo.getItemTotal(), salesOrderList);
	}
	
	@Override
	public Boolean createSaleOrder(SalesOrderEntity salesOrderEntity, 
			List<SalesOrderDetailEntity> salesOrderDetailList, String cjr, String xgr) {
		Date nowDate = new Date();
		String saleOrderId = tool.UUID.currentTimeMillisAsRandomNumber();//  生成销售订单ID
		salesOrderEntity.setDm(saleOrderId);
		salesOrderEntity.setZt(1);
		salesOrderEntity.setCjrq(nowDate);
		salesOrderEntity.setCjr(cjr);
		salesOrderEntity.setXgrq(nowDate);
		salesOrderEntity.setXgr(xgr);
		Serializable soId = businessDao.save(salesOrderEntity);
		
		for(SalesOrderDetailEntity salesOrderDetailEntity : salesOrderDetailList){
			salesOrderDetailEntity.setDm(tool.UUID.currentTimeMillisAsRandomNumber());
			salesOrderDetailEntity.setXsdddm(saleOrderId);
			salesOrderDetailEntity.setYjfsl(new BigDecimal(0));
			salesOrderDetailEntity.setCjrq(nowDate);
			salesOrderDetailEntity.setCjr(cjr);
			salesOrderDetailEntity.setXgrq(nowDate);
			salesOrderDetailEntity.setXgr(xgr);
			businessDao.save(salesOrderDetailEntity);
		}
		return soId != null && !soId.equals("");
	}
	
	@Override
	public List<SalesOrderDetailResult> findSalesOrderDetailResultListBySoId(String salesOrderId) {
			List<Object[]> objsList = businessDao.searchAllSql("select sod.dm, sod.cpdm, p.cpmc, sod.sl, sod.dj, sod.yjfsl " +
						"from tbl_sales_order_detail sod left join tbl_product p on sod.cpdm = p.dm where sod.xsdddm = '"+salesOrderId+"'");
			List<SalesOrderDetailResult> salesOrderDetailList = new ArrayList<SalesOrderDetailResult>();
			SalesOrderDetailResult salesOrderDetailResult = null;
			for (Object[] objects : objsList) {
				salesOrderDetailResult = new SalesOrderDetailResult();
				salesOrderDetailResult.setDm(String.valueOf(objects[0]));
				salesOrderDetailResult.setCpdm(String.valueOf(objects[1]));
				salesOrderDetailResult.setCpmc(String.valueOf(objects[2]));
				salesOrderDetailResult.setSl(new BigDecimal(String.valueOf(objects[3])));
				salesOrderDetailResult.setDj(new BigDecimal(String.valueOf(objects[4])));
				salesOrderDetailResult.setYjfsl(new BigDecimal(String.valueOf(objects[5])));
				salesOrderDetailList.add(salesOrderDetailResult);
			}
			return salesOrderDetailList;
	}

	@Override
	public SalesOrderResult findSalesOrderResultBySoId(String salesOrderId) {
		List<Object[]> objsList = businessDao.searchAllSql("select so.dm, so.khdm, c.khmc from tbl_sales_order so left join tbl_client c on so.khdm = c.dm where so.dm = '"+salesOrderId+"'");
		SalesOrderResult salesOrderResult = new SalesOrderResult();
		if(objsList.size() > 0){
			Object[] objects = objsList.get(0);
			salesOrderResult.setDm(String.valueOf(objects[0]));
			salesOrderResult.setKhdm(String.valueOf(objects[1]));
			salesOrderResult.setKhmc(String.valueOf(objects[2]));
		}
		return salesOrderResult;
	}
	
	@Override
	public Boolean updateSaleOrder(SalesOrderEntity salesOrderEntity, List<SalesOrderDetailEntity> salesOrderDetailList, String xgr) {
		String salesOrderId = salesOrderEntity.getDm();
		Date nowDate = new Date();
		SalesOrderEntity updateObj = (SalesOrderEntity) businessDao.get(SalesOrderEntity.class, salesOrderId);
		updateObj.setXgr(xgr);
		updateObj.setXgrq(nowDate);
		businessDao.executeSql("delete from tbl_sales_order_detail where xsdddm = '"+salesOrderId+"'");
		if (salesOrderDetailList != null && salesOrderDetailList.size() > 0) {
			for(SalesOrderDetailEntity salesOrderDetailEntity : salesOrderDetailList){
				salesOrderDetailEntity.setDm(tool.UUID.currentTimeMillisAsRandomNumber());
				salesOrderDetailEntity.setXsdddm(salesOrderId);
				salesOrderDetailEntity.setYjfsl(new BigDecimal(0));
				salesOrderDetailEntity.setCjrq(nowDate);
				salesOrderDetailEntity.setCjr(xgr);
				salesOrderDetailEntity.setXgrq(nowDate);
				salesOrderDetailEntity.setXgr(xgr);
				businessDao.save(salesOrderDetailEntity);
			}
		}
		return businessDao.update(updateObj);
	}
	
	@Override
	public Boolean deleteSaleOrder(List<String> salesOrderIdList) {
		return businessDao.executeSql(new String[] {"delete from tbl_sales_order where dm in "+sqlStringInCondition(salesOrderIdList),
				"delete from tbl_sales_order_detail where xsdddm in "+sqlStringInCondition(salesOrderIdList)}) > 0;
	}
	
	@Override
	public Boolean updateSubmitSaleOrder(List<String> salesOrderIdList, String xgr) {
		SalesOrderHistoryEntity salesOrderHistoryEntity = null;  //  销售订单历史
		SalesOrderDetailHistoryEntity salesOrderDetailHistoryEntity = null;  //  销售订单明细历史
		SalesOrderResult salesOrderResult = null;
		List<SalesOrderDetailResult> salesOrderDetailResultList = null;
		Date nowDate = new Date();
		for(String salesOrderId : salesOrderIdList){
			salesOrderResult = findSalesOrderResultBySoId(salesOrderId);
			salesOrderHistoryEntity = new SalesOrderHistoryEntity();
			salesOrderHistoryEntity.setDm(salesOrderId);//  销售订单历史代码与销售订单代码相同
			salesOrderHistoryEntity.setKhdm(salesOrderResult.getKhdm());
			salesOrderHistoryEntity.setKhmc(salesOrderResult.getKhmc());
			salesOrderHistoryEntity.setKhlxdh(salesOrderResult.getKhlxdh());
			salesOrderHistoryEntity.setBz(salesOrderResult.getBz());
			salesOrderHistoryEntity.setCjr(xgr);
			salesOrderHistoryEntity.setCjrq(nowDate);
			businessDao.save(salesOrderHistoryEntity);
			
			salesOrderDetailResultList = findSalesOrderDetailResultListBySoId(salesOrderId);
			for(SalesOrderDetailResult salesOrderDetailResult : salesOrderDetailResultList){
				salesOrderDetailHistoryEntity = new SalesOrderDetailHistoryEntity();
				salesOrderDetailHistoryEntity.setDm(salesOrderDetailResult.getDm());//  销售订单明细历史ID与销售订单明细ID相同
				salesOrderDetailHistoryEntity.setXsddlsdm(salesOrderId);
				salesOrderDetailHistoryEntity.setCpdm(salesOrderDetailResult.getCpdm());
				salesOrderDetailHistoryEntity.setCpmc(salesOrderDetailResult.getCpmc());
				salesOrderDetailHistoryEntity.setSl(salesOrderDetailResult.getSl());
				salesOrderDetailHistoryEntity.setDj(salesOrderDetailResult.getDj());
				salesOrderDetailHistoryEntity.setSjsl(new BigDecimal(0));
				salesOrderDetailHistoryEntity.setCjr(xgr);
				salesOrderDetailHistoryEntity.setCjrq(nowDate);
				businessDao.save(salesOrderDetailHistoryEntity);
			}
		}
		return businessDao.executeSql("update tbl_sales_order set zt = 2, xgrq = now(), xgr = '"+
				xgr+"' where dm in "+sqlStringInCondition(salesOrderIdList)) > 0;
	}
	
	@Override
	public SalesOrderStorePageResult findNotProcessSalesOrderStorePageResult(
			String khmc, Integer start, Integer limit) {
		StorePageResultTo storePageResultTo = businessDao.searchStorePageSql("select so.dm, so.khdm, c.khmc, so.cjrq ", 
				" from tbl_sales_order so left join tbl_client c on so.khdm = c.dm " +
		" where so.zt = 2 " + spellingSql("and c.khmc like '%", khmc, "%' "), start, limit);
		List<SalesOrderResult> salesOrderList = new ArrayList<SalesOrderResult>();
		SalesOrderResult salesOrderResult = null;
		Object[] objs = null;
		for(Object obj : storePageResultTo.getItemList()){
			objs = (Object[]) obj;
			salesOrderResult = new SalesOrderResult();
			salesOrderResult.setDm(String.valueOf(objs[0]));
			salesOrderResult.setKhdm(String.valueOf(objs[1]));
			salesOrderResult.setKhmc(String.valueOf(objs[2]));
			salesOrderResult.setCjrq(stringToDate(String.valueOf(objs[3]), DBDATEFORMAT));
			salesOrderList.add(salesOrderResult);
		}
		return new SalesOrderStorePageResult(storePageResultTo.getItemTotal(), salesOrderList);
	}

	@Override
	public Boolean createSaleOrderOutGoods(List<SalesOrderOutGoodsDetailHistoryResult> salesOrderOutGoodsDetailHistoryResultList, String cjr) {
		Date nowDate = new Date();
		SalesOrderOutGoodsDetailHistoryEntity salesOrderOutGoodsDetailHistoryEntity = null;
		String xsddmxlsdm = null;  //  销售订单明细历史代码
		BigDecimal jfzs = null;  //  交付总数
		List<String> deleSalesOrderDetailIdList = new ArrayList<String>();
		for(SalesOrderOutGoodsDetailHistoryResult salesOrderOutGoodsDetailHistoryResult : salesOrderOutGoodsDetailHistoryResultList){
			salesOrderOutGoodsDetailHistoryEntity = new SalesOrderOutGoodsDetailHistoryEntity();
			BeanUtils.copyProperties(salesOrderOutGoodsDetailHistoryResult, salesOrderOutGoodsDetailHistoryEntity);
			salesOrderOutGoodsDetailHistoryEntity.setDm(tool.UUID.currentTimeMillisAsRandomNumber());
			salesOrderOutGoodsDetailHistoryEntity.setCjr(cjr);
			salesOrderOutGoodsDetailHistoryEntity.setCjrq(nowDate);
			businessDao.save(salesOrderOutGoodsDetailHistoryEntity);
			
			xsddmxlsdm = salesOrderOutGoodsDetailHistoryResult.getXsddmxlsdm();
			
			jfzs = salesOrderOutGoodsDetailHistoryResult.getYjfsl().add(salesOrderOutGoodsDetailHistoryResult.getChsl());
			
			if (jfzs.compareTo(salesOrderOutGoodsDetailHistoryResult.getSl()) >= 0) {
				//  交付完成
				deleSalesOrderDetailIdList.add(xsddmxlsdm);
			}else{
				//  交付未完成
				businessDao.executeSql("update tbl_sales_order_detail set yjfsl = "+jfzs+", xgr = '"+cjr+"', xgrq = now() where dm = '"+xsddmxlsdm+"'");
			}
			businessDao.executeSql("update tbl_sales_order_detail_history set sjsl = "+jfzs+" where dm = '"+xsddmxlsdm+"'");
			businessDao.executeSql("");//  产品存量实时减少
		}
		if (deleSalesOrderDetailIdList.size() > 0) {
			businessDao.executeSql("delete from tbl_sales_order_detail where dm in "+sqlStringInCondition(deleSalesOrderDetailIdList));
			businessDao.executeSql("delete from tbl_sales_order where dm not in (select xsdddm from tbl_sales_order_detail group by xsdddm)");
		}
		return true;
	}

	
	
}
