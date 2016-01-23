package proope.warehouse.service;

import proope.warehouse.to.result.MaterialInventoryStorePageResult;
import proope.warehouse.to.result.ProductInventoryStorePageResult;

public interface WarehouseService {

	/**
	 * 查询物料库存
	 * @param wlmc
	 * @param start
	 * @param limit
	 * @return
	 */
	public MaterialInventoryStorePageResult findMaterialInventoryStorePageResult(String wlmc, Integer start, Integer limit);
	
	/**
	 * 查询产品库存
	 * @param cpmc
	 * @param start
	 * @param limit
	 * @return
	 */
	public ProductInventoryStorePageResult findProductInventoryStorePageResult(String cpmc, Integer start, Integer limit);
}
