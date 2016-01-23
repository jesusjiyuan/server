package proope.business.action;

import java.util.List;

import proope.business.service.BomService;
import proope.business.to.entity.BomEntity;
import proope.business.to.result.BomResult;
import proope.business.to.result.OrderMaterialStorePageResult;
import proope.business.to.result.ProductStorePageResult;
import common.struts.BaseAction;

public class BomAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BomService bomService;
	
	/* 产品 */
	private ProductStorePageResult productStorePageResult;
	
	private String cpmc;  //  产品名称
	
	private List<BomResult> bomResultList;//  BomResult List
	
	private String productId;  //  产品代码
	
	private List<BomEntity> bomEntityList;  //  BomEntity List
	
	/* 订单物料使用情况 */
	private OrderMaterialStorePageResult orderMaterialStorePageResult;
	
	private String khmc;  //  客户名称
	
	/**
	 * 为显示BOM表，查询出商品
	 * @return
	 */
	public String findProductStorePageResultForBom(){
		productStorePageResult = bomService.findProductStorePageResultForBom(cpmc, getStart(), getLimit());
		return "productStorePageResult";
	}
	
	/**
	 * 根据产品ID，查询出该产品对应的BOM
	 * @return
	 */
	public String findBomListByProductId(){
		bomResultList = bomService.findBomListByProductId(productId);
		return "bomResultList";
	}
	
	/**
	 * 根据产品ID修改对应的BOM
	 * @return
	 */
	public String editBomListByProductId(){
		return returnResponseWrite(bomService.editBomListByProductId(productId, bomEntityList, this.findSessionUserId()), "修改BOM表失败。");
	}
	
	/**
	 * 查询订单的物料使用量
	 * @return
	 */
	public String findOrderMaterialStorePageResult(){
		orderMaterialStorePageResult = bomService.findOrderMaterialStorePageResult(khmc, getStart(), getLimit());
		return "orderMaterialStorePageResult";
	}
	
	//  ------------------  get set  -----------------------
	public void setBomService(BomService bomService) {
		this.bomService = bomService;
	}
	public ProductStorePageResult getProductStorePageResult() {
		return productStorePageResult;
	}
	public void setProductStorePageResult(ProductStorePageResult productStorePageResult) {
		this.productStorePageResult = productStorePageResult;
	}
	public OrderMaterialStorePageResult getOrderMaterialStorePageResult() {
		return orderMaterialStorePageResult;
	}
	public void setOrderMaterialStorePageResult(OrderMaterialStorePageResult orderMaterialStorePageResult) {
		this.orderMaterialStorePageResult = orderMaterialStorePageResult;
	}
	public String getCpmc() {
		return cpmc;
	}
	public void setCpmc(String cpmc) {
		this.cpmc = cpmc;
	}
	public String getKhmc() {
		return khmc;
	}
	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}
	public List<BomResult> getBomResultList() {
		return bomResultList;
	}
	public void setBomResultList(List<BomResult> bomResultList) {
		this.bomResultList = bomResultList;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public List<BomEntity> getBomEntityList() {
		return bomEntityList;
	}
	public void setBomEntityList(List<BomEntity> bomEntityList) {
		this.bomEntityList = bomEntityList;
	}
	
}
