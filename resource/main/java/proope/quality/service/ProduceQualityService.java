package proope.quality.service;

import java.util.List;
import proope.produce.to.entity.ProductPutWarehouseHistoryEntity;
import proope.produce.to.result.ProductPutWarehouseApplyDetailStorePageResult;
import proope.produce.to.result.ProductPutWarehouseDetailHistoryResult;

public interface ProduceQualityService {

	/**
	 * 查询入库待检的产品明细
	 * @param cpmc
	 * @param start
	 * @param limit
	 * @return
	 */
	public ProductPutWarehouseApplyDetailStorePageResult findWaitCheckProductPutWarehouseApplyDetailStorePageResult(String cpmc, Integer start, Integer limit);
	
	/**
	 * 创建产品入库单
	 * @param productPutWarehouseHistoryEntity
	 * @param productPutWarehouseDetailHistoryEntityList
	 * @param cjr
	 * @return
	 */
	public Boolean createProductPutWarehouseHistory(ProductPutWarehouseHistoryEntity productPutWarehouseHistoryEntity, 
			List<ProductPutWarehouseDetailHistoryResult> productPutWarehouseDetailHistoryResultList, String cjr);
	
}
