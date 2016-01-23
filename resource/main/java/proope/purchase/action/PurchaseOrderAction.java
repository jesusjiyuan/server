package proope.purchase.action;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import proope.purchase.service.PurchaseOrderService;
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

/**
 * 采购订单action
 * @author 
 *
 */
public class PurchaseOrderAction extends common.struts.BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PurchaseOrderService purchaseOrderService;

	/* 采购订单分页查询 */
	private PurchaseOrderStorePageResult purchaseOrderStorePageResult;
	
	private String purchaseOrderId;  //  采购申请ID
	
	private String gysmc;  //  供应商名称  查询订单/订单历史时的查询条件
	
	private PurchaseOrderResult purchaseOrderResult;  //  采购订单查询结果实体

	/* 采购订单明细List */
	private List<PurchaseOrderDetailResult> purchaseOrderDetailList;
	
	private List<PurchaseOrderDetailResult> purchaseOrderDetailResultList;  //  采购订单明细结果list
	
	/* 采购订单历史 */
	private PurchaseOrderHistoryStorePageResult purchaseOrderHistoryStorePageResult;
	
	private String purchaseOrderHistoryId;  //  采购订单历史ID
	
	private List<PurchaseOrderDetailHistoryEntity> purchaseOrderDetailHistoryList;  //  采购订单明细历史List
	
	/* 入库单 */
	private PutWarehouseHistoryStorePageResult putWarehouseHistoryStorePageResult;
	
	private String rkbmmc;  //  入库部门名称
	
	private String putWarehouseHistoryId;  //  物料入库历史ID
	
	private List<PutWarehouseDetailHistoryEntity> putWarehouseDetailHistoryList;  //   物料入库历史明细List
	
	/* 采购订单明细 */
	private PurchaseOrderDetailStorePageResult purchaseOrderDetailStorePageResult;
	
	/* 到货历史分页查询 */
	private PurchaseArrivalsGoodsHistoryStorePageResult purchaseArrivalsGoodsHistoryStorePageResult;
	
	/* 到货明细Result */
	private List<PurchaseArrivalsGoodsDetailResult> purchaseArrivalsGoodsDetailResultList;
	
	private String arrivalsGoodsHistoryId;  //  到货历史ID
	
	/* 到货历史明细  */
	private List<PurchaseArrivalsGoodsDetailHistoryEntity> purchaseArrivalsGoodsDetailHistoryEntityList;
	
	/**
	 * 创建采购订单
	 * @return
	 */
	public String createPurchaseOrder(){
		String loginUserId = this.findSessionUserId();
		return returnResponseWrite(purchaseOrderService.createPurchaseOrder(purchaseOrderResult, purchaseOrderDetailResultList, loginUserId, loginUserId), "创建采购订单失败。");
	}
	
	/**
	 * 查询采购订单
	 * @return
	 */
	public String findPurchaseOrderStorePageResult(){
		purchaseOrderStorePageResult = purchaseOrderService.findPurchaseOrderStorePageResult(gysmc, getStart(), getLimit());
		return "purchaseOrderStorePageResult";
	}
	
	/**
	 * 根据采购订单ID，查询出采购订单
	 * @return
	 */
	public String findPurchaseOrderResultByPoId(){
		purchaseOrderResult = purchaseOrderService.findPurchaseOrderResultByPoId(purchaseOrderId);
		return "purchaseOrderResult";
	}
	
	/**
	 * 根据采购订单ID，查询出该订单下的明细
	 * @return
	 */
	public String findPurchaseOrderDetailListByPoId(){
		purchaseOrderDetailList = purchaseOrderService.findPurchaseOrderDetailListByPoId(purchaseOrderId);
		return "purchaseOrderDetailList";
	}
	
	/**
	 * 查询采购订单全部明细  创建到货记录
	 * @return
	 */
	public String findPurchaseOrderDetailStorePageResult(){
		purchaseOrderDetailStorePageResult = purchaseOrderService.findPurchaseOrderDetailStorePageResult(gysmc, getStart(), getLimit());
		return "purchaseOrderDetailStorePageResult";
	}
	
	/**
	 * 创建到货记录
	 * @return
	 */
	public String createArrivalsGoods(){
		String loginUserId = this.findSessionUserId();
		return returnResponseWrite(purchaseOrderService.createPurchaseOrderArrivalsGoods(
				purchaseArrivalsGoodsDetailResultList, loginUserId, loginUserId), "创建到货记录失败！");
	}
	
	/**
	 * 分页查询出到货历史
	 * @return
	 */
	public String findPurchaseArrivalsGoodsHistoryStorePageResult(){
		purchaseArrivalsGoodsHistoryStorePageResult = purchaseOrderService.findPurchaseArrivalsGoodsHistoryStorePageResult(getStart(), getLimit());
		return "purchaseArrivalsGoodsHistoryStorePageResult";
	}
	
	/**
	 * 根据到货历史记录ID查询出到货明细
	 * @return
	 */
	public String findPurchaseArrivalsGoodsDetailHistoryListByPaghId(){
		purchaseArrivalsGoodsDetailHistoryEntityList = purchaseOrderService.findPurchaseArrivalsGoodsDetailHistoryListByAgdhId(arrivalsGoodsHistoryId);
		return "purchaseArrivalsGoodsDetailHistoryEntityList";
	}
	
	/**
	 * 查询采购订单历史
	 * @return
	 */
	public String findPurchaseOrderHistoryStorePageResult(){
		purchaseOrderHistoryStorePageResult = purchaseOrderService.findPurchaseOrderHistoryStorePageResult(gysmc, getStart(), getLimit());
		return "purchaseOrderHistoryStorePageResult";
	}
	
	/**
	 * 根据采购订单历史ID，查询出该采购订单历史明细
	 * @return
	 */
	public String findPurchaseOrderDetailHistoryListByPohId(){
		purchaseOrderDetailHistoryList = purchaseOrderService.findPurchaseOrderDetailHistoryListByPohId(purchaseOrderHistoryId);
		return "purchaseOrderDetailHistoryList";
	}
	/**
	 * 
	 * 根据采购订单历史ID，导出采购申请历史
	 * @return
	 */
	public String exportPDFPutWarehouseHistory(){
		OutputStream oStream=null;
		try {
			purchaseOrderId = purchaseOrderId;
			oStream=this.getHttpServletResponse().getOutputStream();
			PurchaseOrderReport.purchaseHistoryReport(oStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//purchaseOrderDetailHistoryList = purchaseOrderService.findPurchaseOrderDetailHistoryListByPohId(purchaseOrderHistoryId);
		return "purchaseOrderDetailHistoryList";
	}
	
	/**
	 * 入库历史查询
	 * @return
	 */
	public String findPutWarehouseHistoryStorePageResult(){
		putWarehouseHistoryStorePageResult = purchaseOrderService.findPutWarehouseHistoryStorePageResult(rkbmmc, getStart(), getLimit());
		return "putWarehouseHistoryStorePageResult";
	}
	
	/**
	 * 根据物料入库历史ID，查询出物料入库历史明细
	 * @return
	 */
	public String findPutWarehouseDetailHistoryListByPwhId(){
		putWarehouseDetailHistoryList = purchaseOrderService.findPutWarehouseDetailHistoryList(putWarehouseHistoryId);
		return "putWarehouseDetailHistoryList";
	}
	
	//  ----------------------  get set --------------------------------
	public void setPurchaseOrderService(PurchaseOrderService purchaseOrderService) {
		this.purchaseOrderService = purchaseOrderService;
	}
	
	public PurchaseOrderStorePageResult getPurchaseOrderStorePageResult() {
		return purchaseOrderStorePageResult;
	}
	public void setPurchaseOrderStorePageResult(PurchaseOrderStorePageResult purchaseOrderStorePageResult) {
		this.purchaseOrderStorePageResult = purchaseOrderStorePageResult;
	}
	public PurchaseOrderHistoryStorePageResult getPurchaseOrderHistoryStorePageResult() {
		return purchaseOrderHistoryStorePageResult;
	}
	public void setPurchaseOrderHistoryStorePageResult(PurchaseOrderHistoryStorePageResult purchaseOrderHistoryStorePageResult) {
		this.purchaseOrderHistoryStorePageResult = purchaseOrderHistoryStorePageResult;
	}
	public PutWarehouseHistoryStorePageResult getPutWarehouseHistoryStorePageResult() {
		return putWarehouseHistoryStorePageResult;
	}
	public void setPutWarehouseHistoryStorePageResult(PutWarehouseHistoryStorePageResult putWarehouseHistoryStorePageResult) {
		this.putWarehouseHistoryStorePageResult = putWarehouseHistoryStorePageResult;
	}
	public String getGysmc() {
		return gysmc;
	}
	public void setGysmc(String gysmc) {
		this.gysmc = gysmc;
	}
	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	public PurchaseOrderResult getPurchaseOrderResult() {
		return purchaseOrderResult;
	}
	public void setPurchaseOrderResult(PurchaseOrderResult purchaseOrderResult) {
		this.purchaseOrderResult = purchaseOrderResult;
	}
	public List<PurchaseOrderDetailResult> getPurchaseOrderDetailResultList() {
		return purchaseOrderDetailResultList;
	}
	public void setPurchaseOrderDetailResultList(
			List<PurchaseOrderDetailResult> purchaseOrderDetailResultList) {
		this.purchaseOrderDetailResultList = purchaseOrderDetailResultList;
	}
	public List<PurchaseOrderDetailResult> getPurchaseOrderDetailList() {
		return purchaseOrderDetailList;
	}
	public void setPurchaseOrderDetailList(List<PurchaseOrderDetailResult> purchaseOrderDetailList) {
		this.purchaseOrderDetailList = purchaseOrderDetailList;
	}
	public String getPurchaseOrderHistoryId() {
		return purchaseOrderHistoryId;
	}
	public void setPurchaseOrderHistoryId(String purchaseOrderHistoryId) {
		this.purchaseOrderHistoryId = purchaseOrderHistoryId;
	}
	public List<PurchaseOrderDetailHistoryEntity> getPurchaseOrderDetailHistoryList() {
		return purchaseOrderDetailHistoryList;
	}
	public void setPurchaseOrderDetailHistoryList(List<PurchaseOrderDetailHistoryEntity> purchaseOrderDetailHistoryList) {
		this.purchaseOrderDetailHistoryList = purchaseOrderDetailHistoryList;
	}
	public String getRkbmmc() {
		return rkbmmc;
	}
	public void setRkbmmc(String rkbmmc) {
		this.rkbmmc = rkbmmc;
	}
	public String getPutWarehouseHistoryId() {
		return putWarehouseHistoryId;
	}
	public void setPutWarehouseHistoryId(String putWarehouseHistoryId) {
		this.putWarehouseHistoryId = putWarehouseHistoryId;
	}
	public List<PutWarehouseDetailHistoryEntity> getPutWarehouseDetailHistoryList() {
		return putWarehouseDetailHistoryList;
	}
	public void setPutWarehouseDetailHistoryList(List<PutWarehouseDetailHistoryEntity> putWarehouseDetailHistoryList) {
		this.putWarehouseDetailHistoryList = putWarehouseDetailHistoryList;
	}
	public PurchaseOrderDetailStorePageResult getPurchaseOrderDetailStorePageResult() {
		return purchaseOrderDetailStorePageResult;
	}
	public void setPurchaseOrderDetailStorePageResult(PurchaseOrderDetailStorePageResult purchaseOrderDetailStorePageResult) {
		this.purchaseOrderDetailStorePageResult = purchaseOrderDetailStorePageResult;
	}
	public PurchaseArrivalsGoodsHistoryStorePageResult getPurchaseArrivalsGoodsHistoryStorePageResult() {
		return purchaseArrivalsGoodsHistoryStorePageResult;
	}
	public void setPurchaseArrivalsGoodsHistoryStorePageResult(PurchaseArrivalsGoodsHistoryStorePageResult purchaseArrivalsGoodsHistoryStorePageResult) {
		this.purchaseArrivalsGoodsHistoryStorePageResult = purchaseArrivalsGoodsHistoryStorePageResult;
	}
	public List<PurchaseArrivalsGoodsDetailResult> getPurchaseArrivalsGoodsDetailResultList() {
		return purchaseArrivalsGoodsDetailResultList;
	}
	public void setPurchaseArrivalsGoodsDetailResultList(List<PurchaseArrivalsGoodsDetailResult> purchaseArrivalsGoodsDetailResultList) {
		this.purchaseArrivalsGoodsDetailResultList = purchaseArrivalsGoodsDetailResultList;
	}
	public String getArrivalsGoodsHistoryId() {
		return arrivalsGoodsHistoryId;
	}
	public void setArrivalsGoodsHistoryId(String arrivalsGoodsHistoryId) {
		this.arrivalsGoodsHistoryId = arrivalsGoodsHistoryId;
	}
	public List<PurchaseArrivalsGoodsDetailHistoryEntity> getPurchaseArrivalsGoodsDetailHistoryEntityList() {
		return purchaseArrivalsGoodsDetailHistoryEntityList;
	}
	public void setPurchaseArrivalsGoodsDetailHistoryEntityList(List<PurchaseArrivalsGoodsDetailHistoryEntity> purchaseArrivalsGoodsDetailHistoryEntityList) {
		this.purchaseArrivalsGoodsDetailHistoryEntityList = purchaseArrivalsGoodsDetailHistoryEntityList;
	}

}
