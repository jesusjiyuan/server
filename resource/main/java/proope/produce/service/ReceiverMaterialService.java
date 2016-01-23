package proope.produce.service;

import java.util.List;

import proope.produce.to.entity.ReceiveMaterialDetailHistoryEntity;
import proope.produce.to.entity.ReceiveMaterialHistoryEntity;
import proope.produce.to.result.ReceiveMaterialHistoryStorePageResult;

public interface ReceiverMaterialService {

	/**
	 * 查询领料历史
	 * @param bmmc
	 * @param start
	 * @param limit
	 * @return
	 */
	public ReceiveMaterialHistoryStorePageResult findReceiveMaterialHistoryStorePageResult(String bmmc, Integer start, Integer limit);
	
	/**
	 * 创建领料历史记录
	 * @param receiveMaterialHistoryEntity
	 * @param receiveMaterialDetailHistoryEntityList
	 * @param cjr
	 * @return
	 */
	public Boolean createReceiveMaterialHistoryEntity(ReceiveMaterialHistoryEntity receiveMaterialHistoryEntity, 
			List<ReceiveMaterialDetailHistoryEntity> receiveMaterialDetailHistoryEntityList, String cjr);
	
	/**
	 * 根据领料历史ID，查询出领料历史明细
	 * @param receiveMaterialHistoryId
	 * @return
	 */
	public List<ReceiveMaterialDetailHistoryEntity> findReceiveMaterialDetailHistoryListByRmhId(String receiveMaterialHistoryId);
}
