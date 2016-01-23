package proope.purchase.service;

import java.util.List;

import proope.purchase.to.entity.PurchaseArrivalsGoodsDetailHistoryEntity;
import proope.purchase.to.entity.PurchaseOrderDetailHistoryEntity;
import proope.purchase.to.entity.PutWarehouseDetailHistoryEntity;
import proope.purchase.to.result.PurchaseArrivalsGoodsDetailResult;
import proope.purchase.to.result.PurchaseArrivalsGoodsHistoryStorePageResult;
import proope.purchase.to.result.PurchaseOrderDetailResult;
import proope.purchase.to.result.PurchaseOrderDetailStorePageResult;
import proope.purchase.to.result.PurchaseOrderHistoryStorePageResult;
import proope.purchase.to.result.PurchaseOrderResult;
import proope.purchase.to.result.PurchaseOrderStorePageResult;
import proope.purchase.to.result.PutWarehouseHistoryStorePageResult;

public interface PurchaseOrderService {

	/**
	 * 创建采购订单
	 * @param purchaseOrderResult
	 * @param PurchaseOrderDetailResult
	 * @param cjr
	 * @param xgr
	 * @return
	 */
	public Boolean createPurchaseOrder(PurchaseOrderResult purchaseOrderResult, 
			List<PurchaseOrderDetailResult> purchaseOrderDetailResultList, String cjr, String xgr);
	
	/**
	 * 查询出未处理完的采购订单
	 * @return
	 */
	public PurchaseOrderStorePageResult findPurchaseOrderStorePageResult(String gysmc, Integer start, Integer limit);
	
	/**
	 * 根据采购订单ID，查询出采购订单
	 * @param purchaseOrderId
	 * @return
	 */
	public PurchaseOrderResult findPurchaseOrderResultByPoId(String purchaseOrderId);
	
	/**
	 * 查询出某个采购订单下的采购明细
	 * @param purchaseOrderId
	 * @return
	 */
	public List<PurchaseOrderDetailResult> findPurchaseOrderDetailListByPoId(String purchaseOrderId);
	
	/**
	 * 分页查询采购订单历史记录
	 * @param gysmc
	 * @param start
	 * @param limit
	 * @return
	 */
	public PurchaseOrderHistoryStorePageResult findPurchaseOrderHistoryStorePageResult(String gysmc, Integer start, Integer limit);
	
	/**
	 * 根据采购订单历史ID，查询出该采购订单历史明细
	 * @param purchaseOrderHistoryId
	 * @return
	 */
	public List<PurchaseOrderDetailHistoryEntity> findPurchaseOrderDetailHistoryListByPohId(String purchaseOrderHistoryId);
	
	/**
	 * 查询入库单历史
	 * @param rkbmmc
	 * @param start
	 * @param limit
	 * @return
	 */
	public PutWarehouseHistoryStorePageResult findPutWarehouseHistoryStorePageResult(String rkbmmc, Integer start, Integer limit);
	
	/**
	 * 根据入库单历史ID，查询出明细
	 * @param putWarehouseHistoryId
	 * @return
	 */
	public List<PutWarehouseDetailHistoryEntity> findPutWarehouseDetailHistoryList(String putWarehouseHistoryId);
	
	/**
	 * 查询出采购订单明细，创建到货记录用
	 * @param gysmc
	 * @param start
	 * @param limit
	 * @return
	 */
	public PurchaseOrderDetailStorePageResult findPurchaseOrderDetailStorePageResult(String gysmc, Integer start, Integer limit);
	
	/**
	 * 创建到货记录
	 * @param purchaseArrivalsGoodsDetailResultList
	 * @param cjr
	 * @param xgr
	 * @return
	 */
	public Boolean createPurchaseOrderArrivalsGoods(List<PurchaseArrivalsGoodsDetailResult> purchaseArrivalsGoodsDetailResultList, String cjr, String xgr);
	
	/**
	 * 分页查询到货历史
	 * @param start
	 * @param limit
	 * @return
	 */
	public PurchaseArrivalsGoodsHistoryStorePageResult findPurchaseArrivalsGoodsHistoryStorePageResult(Integer start, Integer limit);
	
	/**
	 * 根据到货历史ID，查询出明细
	 * @param arrivalsGoodsHistoryId
	 * @return
	 */
	public List<PurchaseArrivalsGoodsDetailHistoryEntity> findPurchaseArrivalsGoodsDetailHistoryListByAgdhId(String arrivalsGoodsHistoryId);
}
