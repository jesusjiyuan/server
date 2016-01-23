package proope.purchase.service.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import proope.purchase.PurchaseDao;
import proope.purchase.service.PurchaseApplyService;
import proope.purchase.to.entity.PurchaseApplyDetailEntity;
import proope.purchase.to.entity.PurchaseApplyDetailHistoryEntity;
import proope.purchase.to.entity.PurchaseApplyEntity;
import proope.purchase.to.entity.PurchaseApplyHistoryEntity;
import proope.purchase.to.result.PurchaseApplyDetailResult;
import proope.purchase.to.result.PurchaseApplyDetailStorePageResult;
import proope.purchase.to.result.PurchaseApplyHistoryStorePageResult;
import proope.purchase.to.result.PurchaseApplyResult;
import proope.purchase.to.result.PurchaseApplyStorePageResult;
import common.hibernate.BaseService;
import common.to.StorePageResultTo;

public class PurchaseApplyServiceImpl extends BaseService implements PurchaseApplyService {

	private PurchaseDao purchaseDao;
	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}
	
	@Override
	public PurchaseApplyStorePageResult findPurchaseApplyStorePageResult(String bmmc, Integer start, Integer limit) {
		StorePageResultTo storePageResultTo = purchaseDao.searchStorePageSql("select pa.dm, dep.bmmc, pa.cjrq ", 
				" from tbl_purchase_apply pa left join tbl_department dep on pa.bmdm = dep.dm where pa.zt=1 " +
				spellingSql("and dep.bmmc like '%", bmmc, "%'"), start, limit);
		List<PurchaseApplyResult> purchaseApplyList = new ArrayList<PurchaseApplyResult>();
		PurchaseApplyResult purchaseApplyResult = null;
		Object[] objs = null;
		for(Object obj : storePageResultTo.getItemList()){
			objs = (Object[]) obj;
			purchaseApplyResult = new PurchaseApplyResult();
			purchaseApplyResult.setDm(String.valueOf(objs[0]));
			purchaseApplyResult.setBmmc(String.valueOf(objs[1]));
			purchaseApplyResult.setCjrq(stringToDate(String.valueOf(objs[2]), DBDATEFORMAT));
			purchaseApplyList.add(purchaseApplyResult);
		}
		return new PurchaseApplyStorePageResult(storePageResultTo.getItemTotal(), purchaseApplyList);
	}

	@Override
	public Boolean createPurchaseApply(PurchaseApplyEntity purchaseApplyEntity,
			List<PurchaseApplyDetailEntity> purchaseApplyDetailList, String cjr, String xgr) {
		String paId = tool.UUID.currentTimeMillisAsRandomNumber();  //  采购申请ID
		Date date = new Date();
		purchaseApplyEntity.setCjr(cjr);
		purchaseApplyEntity.setCjrq(date);
		purchaseApplyEntity.setXgr(xgr);
		purchaseApplyEntity.setXgrq(date);
		purchaseApplyEntity.setDm(paId);
		purchaseApplyEntity.setZt(1);
		Serializable pa = purchaseDao.save(purchaseApplyEntity);
		
		if(purchaseApplyDetailList != null && purchaseApplyDetailList.size() > 0){
			for(PurchaseApplyDetailEntity purchaseApplyDetailEntity : purchaseApplyDetailList){
				purchaseApplyDetailEntity.setDm(tool.UUID.currentTimeMillisAsRandomNumber());
				purchaseApplyDetailEntity.setCgsqdm(paId);
				purchaseApplyDetailEntity.setYcgsl(new BigDecimal(0));
				purchaseApplyDetailEntity.setCjr(cjr);
				purchaseApplyDetailEntity.setCjrq(date);
				purchaseDao.save(purchaseApplyDetailEntity);
			}
		}
		
		return pa != null && !pa.equals("");
	}

	@Override
	public PurchaseApplyResult findPurchaseApplyResult(String purchaseApplyId) {
		List<Object[]> objsList = purchaseDao.searchAllSql(
				"select pa.dm, pa.bmdm, dep.bmmc from tbl_purchase_apply pa left join tbl_department dep on pa.bmdm = dep.dm where pa.dm = '"+purchaseApplyId+"'");
		PurchaseApplyResult purchaseApplyResult = new PurchaseApplyResult();
		if(objsList.size() > 0){
			Object[] objects = objsList.get(0);
			purchaseApplyResult.setDm(String.valueOf(objects[0]));
			purchaseApplyResult.setBmdm(String.valueOf(objects[1]));
			purchaseApplyResult.setBmmc(String.valueOf(objects[2]));
		}
		return purchaseApplyResult;
	}
	
	@Override
	public List<PurchaseApplyDetailResult> findPurchaseApplyDetailListByPaId(String purchaseApplyId) {
		List<Object[]> objsList = purchaseDao.searchAllSql("select pad.dm, pad.wldm, m.mc, m.gg, pad.sqsl from " +
					"(select dm, cgsqdm, wldm, sqsl from tbl_purchase_apply_detail where cgsqdm = '"+purchaseApplyId+
					"') pad left join tbl_material m on pad.wldm = m.dm");
		List<PurchaseApplyDetailResult> purchaseApplyDetailList = new ArrayList<PurchaseApplyDetailResult>();
		PurchaseApplyDetailResult purchaseApplyDetailResult = null;
		for (Object[] objects : objsList) {
			purchaseApplyDetailResult = new PurchaseApplyDetailResult();
			purchaseApplyDetailResult.setDm(String.valueOf(objects[0]));
			purchaseApplyDetailResult.setWldm(String.valueOf(objects[1]));
			purchaseApplyDetailResult.setWlmc(String.valueOf(objects[2]));
			purchaseApplyDetailResult.setGg(String.valueOf(objects[3]));
			purchaseApplyDetailResult.setSqsl(new BigDecimal(String.valueOf(objects[4])));
			purchaseApplyDetailList.add(purchaseApplyDetailResult);
		}
		return purchaseApplyDetailList;
	}

	@Override
	public Boolean updatePurchaseApply(PurchaseApplyEntity purchaseApplyEntity,
			List<PurchaseApplyDetailEntity> purchaseApplyDetailList, String xgr) {
		String purchaseApplyId = purchaseApplyEntity.getDm();
		Date date = new Date();
		PurchaseApplyEntity updateObj = (PurchaseApplyEntity)purchaseDao.get(PurchaseApplyEntity.class, purchaseApplyId);
		updateObj.setXgrq(date);
		updateObj.setXgr(xgr);
		//  先删除现有的采购申请明细，再创建新的采购申请明细
		purchaseDao.executeSql("delete from tbl_purchase_apply_detail where cgsqdm = '"+purchaseApplyId+"'");
		if(purchaseApplyDetailList != null && purchaseApplyDetailList.size() > 0){
			for(PurchaseApplyDetailEntity purchaseApplyDetailEntity : purchaseApplyDetailList){
				purchaseApplyDetailEntity.setDm(tool.UUID.currentTimeMillisAsRandomNumber());
				purchaseApplyDetailEntity.setCgsqdm(purchaseApplyId);
				purchaseApplyDetailEntity.setYcgsl(new BigDecimal(0));
				purchaseApplyDetailEntity.setCjr(xgr);
				purchaseApplyDetailEntity.setCjrq(date);
				purchaseDao.save(purchaseApplyDetailEntity);
			}
		}
		return purchaseDao.update(updateObj);
	}
	
	@Override
	public Boolean deletePurchaseApply(List<String> purchaseApplyIdList){
		return purchaseDao.executeSql(new String[] {"delete from tbl_purchase_apply where dm in "+sqlStringInCondition(purchaseApplyIdList),
				"delete from tbl_purchase_apply_detail where cgsqdm in "+sqlStringInCondition(purchaseApplyIdList)}) > 0;
	}

	@Override
	public Boolean updateSubmitPurchaseApply(List<String> purchaseApplyIdList, String xgr) {
		//  在申请历史表中创建记录，申请历史表主键使用申请表的主键
		PurchaseApplyHistoryEntity purchaseApplyHistoryEntity = null;
		PurchaseApplyDetailHistoryEntity purchaseApplyDetailHistoryEntity = null;
		PurchaseApplyResult purchaseApplyResult = null;
		List<PurchaseApplyDetailResult> purchaseApplyDetailResultList = null;
		Date nowDate = new Date();
		for(String paId : purchaseApplyIdList){
			purchaseApplyResult = findPurchaseApplyResult(paId);
			purchaseApplyHistoryEntity = new PurchaseApplyHistoryEntity();
			purchaseApplyHistoryEntity.setDm(paId);  //  申请表主键作为历史表主键
			purchaseApplyHistoryEntity.setBmdm(purchaseApplyResult.getBmdm());
			purchaseApplyHistoryEntity.setBmmc(purchaseApplyResult.getBmmc());
			purchaseApplyHistoryEntity.setSqtcsj(nowDate);
			purchaseApplyHistoryEntity.setCjrq(nowDate);
			purchaseApplyHistoryEntity.setCjr(xgr);
			purchaseDao.save(purchaseApplyHistoryEntity);
			purchaseApplyDetailResultList = findPurchaseApplyDetailListByPaId(paId);
			for(PurchaseApplyDetailResult purchaseApplyDetailResult : purchaseApplyDetailResultList){
				purchaseApplyDetailHistoryEntity = new PurchaseApplyDetailHistoryEntity();
				purchaseApplyDetailHistoryEntity.setDm(purchaseApplyDetailResult.getDm());  //  明细历史代码使用明细代码
				purchaseApplyDetailHistoryEntity.setCgsqlsdm(paId);
				purchaseApplyDetailHistoryEntity.setWldm(purchaseApplyDetailResult.getWldm());
				purchaseApplyDetailHistoryEntity.setWlmc(purchaseApplyDetailResult.getWlmc());
				purchaseApplyDetailHistoryEntity.setSqsl(purchaseApplyDetailResult.getSqsl());
				purchaseApplyDetailHistoryEntity.setSjcgsl(new BigDecimal(0));
				purchaseApplyDetailHistoryEntity.setCjr(xgr);
				purchaseApplyDetailHistoryEntity.setCjrq(nowDate);
				purchaseDao.save(purchaseApplyDetailHistoryEntity);
			}
		}
		return purchaseDao.executeSql("update tbl_purchase_apply set zt = 2, xgr = '"+xgr+"', xgrq = now() where dm in "+sqlStringInCondition(purchaseApplyIdList)) > 0;
	}

	@Override
	public PurchaseApplyDetailStorePageResult findHaveSubmitPurchaseApplyDetailStorePageResult(String bmmc, Integer start, Integer limit) {
		StorePageResultTo storePageResultTo = purchaseDao.searchStorePageSql(
				"select pad.dm, pad.wldm, m.mc, pad.sqsl, pad.ycgsl, dep.bmmc ", 
				"from tbl_purchase_apply_detail pad left join tbl_purchase_apply pa on pad.cgsqdm = pa.dm " +
				"left join tbl_material m on pad.wldm = m.dm left join tbl_department dep on pa.bmdm = dep.dm " +
				"where pa.zt = 2 " + spellingSql("and dep.bmmc like '%", bmmc, "%' "), start, limit);
		List<PurchaseApplyDetailResult> purchaseApplyDetailList = new ArrayList<PurchaseApplyDetailResult>();
		PurchaseApplyDetailResult purchaseApplyDetailResult = null;
		Object[] objs = null;
		for(Object obj : storePageResultTo.getItemList()){
			objs = (Object[]) obj;
			purchaseApplyDetailResult = new PurchaseApplyDetailResult();
			purchaseApplyDetailResult.setDm(String.valueOf(objs[0]));
			purchaseApplyDetailResult.setWldm(String.valueOf(objs[1]));
			purchaseApplyDetailResult.setWlmc(String.valueOf(objs[2]));
			purchaseApplyDetailResult.setSqsl(new BigDecimal(String.valueOf(objs[3])));
			purchaseApplyDetailResult.setYcgsl(new BigDecimal(String.valueOf(objs[4])));
			purchaseApplyDetailResult.setBmmc(String.valueOf(objs[5]));
			purchaseApplyDetailList.add(purchaseApplyDetailResult);
		}
		return new PurchaseApplyDetailStorePageResult(storePageResultTo.getItemTotal(), purchaseApplyDetailList);
	}

	@Override
	public PurchaseApplyHistoryStorePageResult findPurchaseApplyHistoryStorePageResult(String bmmc, Integer start, Integer limit) {
		List<SimpleExpression> simpleExpressionList = new ArrayList<SimpleExpression>();
		if(bmmc != null && !"".equals(bmmc)){
			simpleExpressionList.add(Restrictions.like("bmmc", "%"+bmmc+"%"));
		}
		StorePageResultTo storePageResultTo = purchaseDao.searchStorePageCriteria(PurchaseApplyHistoryEntity.class, start, limit, simpleExpressionList);
		List<PurchaseApplyHistoryEntity> purchaseApplyHistoryList = new ArrayList<PurchaseApplyHistoryEntity>();
		for(Object obj : storePageResultTo.getItemList()){
			purchaseApplyHistoryList.add((PurchaseApplyHistoryEntity)obj);
		}
		return new PurchaseApplyHistoryStorePageResult(storePageResultTo.getItemTotal(), purchaseApplyHistoryList);
	}

	@Override
	public PurchaseApplyHistoryEntity findPurchaseApplyHistoryEntityById(String purchaseApplyHistoryId) {
		return (PurchaseApplyHistoryEntity) purchaseDao.get(PurchaseApplyHistoryEntity.class, purchaseApplyHistoryId);
	}

	@Override
	public List<PurchaseApplyDetailHistoryEntity> findPurchaseApplyDetailHistoryListByPadhId(String purchaseApplyHistoryId) {
		List<Object[]> objsList = purchaseDao.searchAllSql("select wldm, wlmc, sqsl, sjcgsl from tbl_purchase_apply_detail_history where cgsqlsdm = '"+purchaseApplyHistoryId+"'");
		List<PurchaseApplyDetailHistoryEntity> purchaseApplyDetailHistoryList = new ArrayList<PurchaseApplyDetailHistoryEntity>();
		PurchaseApplyDetailHistoryEntity purchaseApplyDetailHistoryEntity = null;
		for (Object[] objects : objsList) {
			purchaseApplyDetailHistoryEntity = new PurchaseApplyDetailHistoryEntity();
			purchaseApplyDetailHistoryEntity.setWldm(String.valueOf(objects[0]));
			purchaseApplyDetailHistoryEntity.setWlmc(String.valueOf(objects[1]));
			purchaseApplyDetailHistoryEntity.setSqsl(new BigDecimal(String.valueOf(objects[2])));
			purchaseApplyDetailHistoryEntity.setSjcgsl(new BigDecimal(String.valueOf(objects[3])));
			purchaseApplyDetailHistoryList.add(purchaseApplyDetailHistoryEntity);
		}
		return purchaseApplyDetailHistoryList;
	}
	
}
