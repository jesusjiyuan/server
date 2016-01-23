package proope.quality.service;

import java.util.List;
import proope.purchase.to.entity.PutWarehouseHistoryEntity;
import proope.purchase.to.result.PurchaseArrivalsGoodsDetailStorePageResult;
import proope.purchase.to.result.PutWarehouseDetailHistoryResult;

/**
 * 物料品质管理Service
 * @author 
 *
 */
public interface MaterialQualityService {

	/**
	 * 查询到货记录，用于入库操作
	 * @param gysmc
	 * @param start
	 * @param limit
	 * @return
	 */
	public PurchaseArrivalsGoodsDetailStorePageResult findPurchaseArrivalsGoodsDetailStorePageResult(String gysmc, Integer start, Integer limit);
	
	/**
	 * 创建物料入库单
	 * @param putWarehouseHistoryEntity
	 * @param putWarehouseDetailHistoryResultList;
	 * @param cjr
	 * @return
	 */
	public Boolean createMaterialPutWarehouseHistory(PutWarehouseHistoryEntity putWarehouseHistoryEntity, 
			List<PutWarehouseDetailHistoryResult> putWarehouseDetailHistoryResultList, String cjr);
}
