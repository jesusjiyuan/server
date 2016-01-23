package proope.business.service;

import java.util.List;

import proope.business.to.entity.SalesOrderDetailEntity;
import proope.business.to.entity.SalesOrderEntity;
import proope.business.to.result.SalesOrderDetailResult;
import proope.business.to.result.SalesOrderOutGoodsDetailHistoryResult;
import proope.business.to.result.SalesOrderResult;
import proope.business.to.result.SalesOrderStorePageResult;

/**
 * 销售订单Service
 * @author 
 *
 */
public interface SalesOrderService {

	/**
	 * 查询未提交的销售订单
	 * @param khmc
	 * @param start
	 * @param limit
	 * @return
	 */
	public SalesOrderStorePageResult findNotSubmitSalesOrderStorePageResult(String khmc, Integer start, Integer limit);
	
	/**
	 * 创建销售订单
	 * @param salesOrderEntity
	 * @param salesOrderDetailList
	 * @param cjr
	 * @param xgr
	 * @return
	 */
	public Boolean createSaleOrder(SalesOrderEntity salesOrderEntity, List<SalesOrderDetailEntity> salesOrderDetailList, String cjr, String xgr);
	
	/**
	 * 根据销售订单ID取得销售订单
	 * @param salesOrderId
	 * @return
	 */
	public SalesOrderResult findSalesOrderResultBySoId(String salesOrderId);
	
	/**
	 * 根据销售订单ID，取得销售订单明细
	 * @param salesOrderId
	 * @return
	 */
	public List<SalesOrderDetailResult> findSalesOrderDetailResultListBySoId(String salesOrderId);
	
	/**
	 * 修改销售订单
	 * @param salesOrderEntity
	 * @param salesOrderDetailList
	 * @param xgr
	 * @return
	 */
	public Boolean updateSaleOrder(SalesOrderEntity salesOrderEntity, List<SalesOrderDetailEntity> salesOrderDetailList, String xgr);
	
	/**
	 * 删除销售订单
	 * @param salesOrderIdList
	 * @return
	 */
	public Boolean deleteSaleOrder(List<String>  salesOrderIdList);
	
	/**
	 * 提交销售订单
	 * @param salesOrderIdList
	 * @param xgr
	 * @return
	 */
	public Boolean updateSubmitSaleOrder(List<String>  salesOrderIdList, String xgr);
	
	/**
	 * 查询未处理的销售订单
	 * @param khmc
	 * @param start
	 * @param limit
	 * @return
	 */
	public SalesOrderStorePageResult findNotProcessSalesOrderStorePageResult(String khmc, Integer start, Integer limit);
	
	/**
	 * 销售订单出货
	 * @param salesOrderOutGoodsDetailHistoryResultList
	 * @param cjr
	 * @return
	 */
	public Boolean createSaleOrderOutGoods(List<SalesOrderOutGoodsDetailHistoryResult> salesOrderOutGoodsDetailHistoryResultList, String cjr);
}
