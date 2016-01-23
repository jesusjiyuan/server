package proope.produce.service;

import java.util.List;

import proope.produce.to.entity.ProductPutWarehouseApplyDetailHistoryEntity;
import proope.produce.to.entity.ProductPutWarehouseApplyHistoryEntity;
import proope.produce.to.entity.ProductPutWarehouseDetailHistoryEntity;
import proope.produce.to.result.ProductPutWarehouseApplyDetailResult;
import proope.produce.to.result.ProductPutWarehouseApplyStorePageResult;
import proope.produce.to.result.ProductPutWarehouseHistoryStorePageResult;

public interface PutWarehouseService {

	/**
	 * 查询产品入库申请分页
	 * @param bmmc
	 * @return
	 */
	public ProductPutWarehouseApplyStorePageResult findProductPutWarehouseApplyStorePageResult(String bmmc, Integer start, Integer limit);
	
	/**
	 * 根据入库申请ID，查询出入库申请明细
	 * @param productPutWarehouseApplyId
	 * @return
	 */
	public List<ProductPutWarehouseApplyDetailResult> findProductPutWarehouseApplyDetailResultListByPpwaId(String productPutWarehouseApplyId);
	
	/**
	 * 创建产品入库申请
	 * @param productPutWarehouseApplyHistoryEntity
	 * @param productPutWarehouseApplyDetailHistoryEntityList
	 * @param cjr
	 * @return
	 */
	public Boolean createProductPutWarehouseApply(ProductPutWarehouseApplyHistoryEntity productPutWarehouseApplyHistoryEntity, 
			List<ProductPutWarehouseApplyDetailHistoryEntity> productPutWarehouseApplyDetailHistoryEntityList, String cjr);
	
	/**
	 * 查询产品入库历史记录
	 * @param bmmc
	 * @param start
	 * @param limit
	 * @return
	 */
	public ProductPutWarehouseHistoryStorePageResult findProductPutWarehouseHistoryStorePageResult(String bmmc, Integer start, Integer limit);
	
	/**
	 * 根据产品入库历史ID，查询出明细
	 * @param productPutWarehouseHistoryId
	 * @return
	 */
	public List<ProductPutWarehouseDetailHistoryEntity> findProductPutWarehouseDetailHistoryListByPpwhId(String productPutWarehouseHistoryId);
}
