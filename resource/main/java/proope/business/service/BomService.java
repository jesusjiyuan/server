package proope.business.service;

import java.util.List;

import proope.business.to.entity.BomEntity;
import proope.business.to.result.BomResult;
import proope.business.to.result.OrderMaterialStorePageResult;
import proope.business.to.result.ProductStorePageResult;

public interface BomService {
	
	/**
	 * 查询产品，用于管理BOM，查询出产品后，可查看该产品对应的BOM表
	 * @param cpmc
	 * @param start
	 * @param limit
	 * @return
	 */
	public ProductStorePageResult findProductStorePageResultForBom(String cpmc, Integer start, Integer limit);
	
	/**
	 * 根据产品ID，查询出BOM，即该产品需要使用多少物料
	 * @param productId
	 * @return
	 */
	public List<BomResult> findBomListByProductId(String productId);
	
	/**
	 * 根据产品ID修改BOM表
	 * @param productId
	 * @param bomEntityList
	 * @param xgr
	 * @return
	 */
	public Boolean editBomListByProductId(String productId, List<BomEntity> bomEntityList, String xgr);
	
	/**
	 * 根据某订单中订购的产品，关联到BOM表，查询共需要使用多少物料
	 * @param khmc
	 * @param start
	 * @param limit
	 * @return
	 */
	public OrderMaterialStorePageResult findOrderMaterialStorePageResult(String khmc, Integer start, Integer limit);
}
