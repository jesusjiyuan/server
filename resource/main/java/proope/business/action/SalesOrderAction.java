package proope.business.action;

import java.util.List;

import proope.business.service.SalesOrderService;
import proope.business.to.entity.SalesOrderDetailEntity;
import proope.business.to.entity.SalesOrderEntity;
import proope.business.to.result.SalesOrderDetailResult;
import proope.business.to.result.SalesOrderOutGoodsDetailHistoryResult;
import proope.business.to.result.SalesOrderResult;
import proope.business.to.result.SalesOrderStorePageResult;
import common.struts.BaseAction;

/**
 * 销售订单action
 * @author 
 *
 */
public class SalesOrderAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SalesOrderService salesOrderService;
	
	/* 销售订单 */
	private SalesOrderStorePageResult salesOrderStorePageResult;
	
	private String khmc;  //  客户名称
	
	private SalesOrderResult salesOrderResult;  //  销售订单Result
	
	private List<SalesOrderDetailResult> salesOrderDetailResultList;  //  销售订单明细Result List
	
	private SalesOrderEntity salesOrderEntity;  //  销售订单Entity
	
	private List<SalesOrderDetailEntity> salesOrderDetailEntityList;  //  销售订单明细Entity List
	
	private String salesOrderId;  //  销售订单ID
	
	private List<String> salesOrderIdList;
	
	/* 销售订单出货历史Result List */
	private List<SalesOrderOutGoodsDetailHistoryResult> salesOrderOutGoodsDetailHistoryResultList;
	
	/**
	 * 查询未提交的销售订单
	 * @return
	 */
	public String findNotSubmitSalesOrderStorePageResult(){
		salesOrderStorePageResult = salesOrderService.findNotSubmitSalesOrderStorePageResult(khmc, getStart(), getLimit());
		System.out.println(salesOrderStorePageResult);
		return "salesOrderStorePageResult";
	}
	
	/**
	 * 创建销售订单
	 * @return
	 */
	public String createSaleOrder(){
		String loginId = this.findSessionUserId();
		return returnResponseWrite(salesOrderService.createSaleOrder(salesOrderEntity, salesOrderDetailEntityList, loginId, loginId), "创建销售订单失败。");
	}
	
	/**
	 * 根据销售订单ID查询出销售订单
	 * @return
	 */
	public String findSalesOrderResultBySalesOrderId(){
		salesOrderResult = salesOrderService.findSalesOrderResultBySoId(salesOrderId);
		return "salesOrderResult";
	}
	
	/**
	 * 根据销售订单ID查询出销售订单明细List
	 * @return
	 */
	public String findSalesOrderDetailResultListBySalesOrderId(){
		salesOrderDetailResultList = salesOrderService.findSalesOrderDetailResultListBySoId(salesOrderId);
		return "salesOrderDetailResultList";
	}
	
	/**
	 * 修改销售订单
	 * @return
	 */
	public String updateSaleOrder(){
		return returnResponseWrite(salesOrderService.updateSaleOrder(salesOrderEntity, salesOrderDetailEntityList, this.findSessionUserId()), "修改销售订单失败。");
	}
	
	/**
	 * 删除销售订单
	 * @return
	 */
	public String deleSaleOrder(){
		return returnResponseWrite(salesOrderService.deleteSaleOrder(salesOrderIdList), "删除销售订单失败。");
	}
	
	/**
	 * 提交销售订单
	 * @return
	 */
	public String submitSaleOrder(){
		return returnResponseWrite(salesOrderService.updateSubmitSaleOrder(salesOrderIdList, this.findSessionUserId()), "创建销售订单失败。");
	}
	
	/**
	 * 查询未处理的销售订单
	 * @return
	 */
	public String findNotProcessSalesOrderStorePageResult(){
		salesOrderStorePageResult = salesOrderService.findNotProcessSalesOrderStorePageResult(khmc, getStart(), getLimit());
		return "salesOrderStorePageResult";
	}
	
	/**
	 * 销售订单出货
	 * @return
	 */
	public String sealOrderOutGoods(){
		return returnResponseWrite(salesOrderService.createSaleOrderOutGoods(salesOrderOutGoodsDetailHistoryResultList, this.findSessionUserId()), "出货失败。");
	}
	
	//  ----------------------- get set -----------------------------
	public void setSalesOrderService(SalesOrderService salesOrderService) {
		this.salesOrderService = salesOrderService;
	}
	public SalesOrderStorePageResult getSalesOrderStorePageResult() {
		return salesOrderStorePageResult;
	}
	public void setSalesOrderStorePageResult(SalesOrderStorePageResult salesOrderStorePageResult) {
		this.salesOrderStorePageResult = salesOrderStorePageResult;
	}
	public String getKhmc() {
		return khmc;
	}
	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}
	public SalesOrderResult getSalesOrderResult() {
		return salesOrderResult;
	}
	public void setSalesOrderResult(SalesOrderResult salesOrderResult) {
		this.salesOrderResult = salesOrderResult;
	}
	public List<SalesOrderDetailResult> getSalesOrderDetailResultList() {
		return salesOrderDetailResultList;
	}
	public void setSalesOrderDetailResultList(List<SalesOrderDetailResult> salesOrderDetailResultList) {
		this.salesOrderDetailResultList = salesOrderDetailResultList;
	}
	public SalesOrderEntity getSalesOrderEntity() {
		return salesOrderEntity;
	}
	public void setSalesOrderEntity(SalesOrderEntity salesOrderEntity) {
		this.salesOrderEntity = salesOrderEntity;
	}
	public List<SalesOrderDetailEntity> getSalesOrderDetailEntityList() {
		return salesOrderDetailEntityList;
	}
	public void setSalesOrderDetailEntityList(List<SalesOrderDetailEntity> salesOrderDetailEntityList) {
		this.salesOrderDetailEntityList = salesOrderDetailEntityList;
	}
	public String getSalesOrderId() {
		return salesOrderId;
	}
	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}
	public List<String> getSalesOrderIdList() {
		return salesOrderIdList;
	}
	public void setSalesOrderIdList(List<String> salesOrderIdList) {
		this.salesOrderIdList = salesOrderIdList;
	}
	public List<SalesOrderOutGoodsDetailHistoryResult> getSalesOrderOutGoodsDetailHistoryResultList() {
		return salesOrderOutGoodsDetailHistoryResultList;
	}
	public void setSalesOrderOutGoodsDetailHistoryResultList(List<SalesOrderOutGoodsDetailHistoryResult> salesOrderOutGoodsDetailHistoryResultList) {
		this.salesOrderOutGoodsDetailHistoryResultList = salesOrderOutGoodsDetailHistoryResultList;
	}
	
}
