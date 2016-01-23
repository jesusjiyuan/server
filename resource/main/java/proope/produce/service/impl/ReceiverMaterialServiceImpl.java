package proope.produce.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import common.hibernate.BaseService;
import common.to.StorePageResultTo;
import proope.produce.ProduceDao;
import proope.produce.service.ReceiverMaterialService;
import proope.produce.to.entity.ReceiveMaterialDetailHistoryEntity;
import proope.produce.to.entity.ReceiveMaterialHistoryEntity;
import proope.produce.to.result.ReceiveMaterialHistoryStorePageResult;

public class ReceiverMaterialServiceImpl extends BaseService implements ReceiverMaterialService {

	private ProduceDao produceDao;

	public void setProduceDao(ProduceDao produceDao) {
		this.produceDao = produceDao;
	}

	@Override
	public ReceiveMaterialHistoryStorePageResult findReceiveMaterialHistoryStorePageResult(String bmmc, Integer start, Integer limit) {
		List<SimpleExpression> simpleExpressionList = new ArrayList<SimpleExpression>();
		if(bmmc != null && !"".equals(bmmc)){
			simpleExpressionList.add(Restrictions.like("bmmc", "%"+bmmc+"%"));
		}
		StorePageResultTo storePageResultTo = produceDao.searchStorePageCriteria(ReceiveMaterialHistoryEntity.class, start, limit, simpleExpressionList);
		List<ReceiveMaterialHistoryEntity> receiveMaterialHistoryEntityList = new ArrayList<ReceiveMaterialHistoryEntity>();
		for(Object obj : storePageResultTo.getItemList()){
			receiveMaterialHistoryEntityList.add((ReceiveMaterialHistoryEntity)obj);
		}
		return new ReceiveMaterialHistoryStorePageResult(storePageResultTo.getItemTotal(), receiveMaterialHistoryEntityList);
	}

	@Override
	public Boolean createReceiveMaterialHistoryEntity(ReceiveMaterialHistoryEntity receiveMaterialHistoryEntity,
			List<ReceiveMaterialDetailHistoryEntity> receiveMaterialDetailHistoryEntityList, String cjr) {
		String rmhId = tool.UUID.currentTimeMillisAsRandomNumber();
		Date nowDate = new Date();
		receiveMaterialHistoryEntity.setDm(rmhId);
		receiveMaterialHistoryEntity.setCjr(cjr);
		receiveMaterialHistoryEntity.setCjrq(nowDate);
		Serializable rmhSerializable = produceDao.save(receiveMaterialHistoryEntity);
		for (ReceiveMaterialDetailHistoryEntity receiveMaterialDetailHistoryEntity : receiveMaterialDetailHistoryEntityList) {
			receiveMaterialDetailHistoryEntity.setDm(tool.UUID.currentTimeMillisAsRandomNumber());
			receiveMaterialDetailHistoryEntity.setLllsdm(rmhId);
			receiveMaterialDetailHistoryEntity.setCjr(cjr);
			receiveMaterialDetailHistoryEntity.setCjrq(nowDate);
			produceDao.executeSql("update tbl_material_inventory set sl = sl - "+
			receiveMaterialDetailHistoryEntity.getSl()+", xgrq = now(), xgr = '"+cjr+"' where wldm = '"+receiveMaterialDetailHistoryEntity.getWldm()+"'");
			produceDao.save(receiveMaterialDetailHistoryEntity);
		}
		return rmhSerializable != null && !"".equals(rmhSerializable);
	}

	@Override
	public List<ReceiveMaterialDetailHistoryEntity> findReceiveMaterialDetailHistoryListByRmhId(String receiveMaterialHistoryId) {
		List<SimpleExpression> simpleExpressionList = new ArrayList<SimpleExpression>();
		simpleExpressionList.add(Restrictions.eq("lllsdm", receiveMaterialHistoryId));
		
		List<Object> objsList = produceDao.searchAllCriteria(ReceiveMaterialDetailHistoryEntity.class, simpleExpressionList);
		
		List<ReceiveMaterialDetailHistoryEntity> receiveMaterialDetailHistoryEntityList = new ArrayList<ReceiveMaterialDetailHistoryEntity>();
		for(Object obj : objsList){
			receiveMaterialDetailHistoryEntityList.add((ReceiveMaterialDetailHistoryEntity)obj);
		}
		return receiveMaterialDetailHistoryEntityList;
	}
	
}
