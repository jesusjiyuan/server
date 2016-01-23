package proope.warehouse.action;

import proope.warehouse.service.WarehouseService;
import proope.warehouse.to.result.MaterialInventoryStorePageResult;
import proope.warehouse.to.result.ProductInventoryStorePageResult;
import common.struts.BaseAction;

public class WarehouseAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private WarehouseService warehouseService;

	/* 物料库存 */
	private MaterialInventoryStorePageResult materialInventoryStorePageResult;
	
	private String wlmc;  //  物料名称
	
	/* 产品库存 */
	private ProductInventoryStorePageResult productInventoryStorePageResult;
	
	private String cpmc;  //  产品名称
	
	/**
	 * 查询物料库存
	 * @return
	 */
	public String findMaterialInventoryStorePageResult(){
		materialInventoryStorePageResult = warehouseService.findMaterialInventoryStorePageResult(
				wlmc, getStart(), getLimit());
		return "materialInventoryStorePageResult";
	}
	
	/**
	 * 查询产品库存
	 * @return
	 */
	public String findProductInventoryStorePageResult(){
		productInventoryStorePageResult = warehouseService.findProductInventoryStorePageResult(
				cpmc, getStart(), getLimit());
		return "productInventoryStorePageResult";
	}
	
	//  --------------------------  get set  ---------------------------
	public void setWarehouseService(WarehouseService warehouseService) {
		this.warehouseService = warehouseService;
	}
	public MaterialInventoryStorePageResult getMaterialInventoryStorePageResult() {
		return materialInventoryStorePageResult;
	}
	public void setMaterialInventoryStorePageResult(MaterialInventoryStorePageResult materialInventoryStorePageResult) {
		this.materialInventoryStorePageResult = materialInventoryStorePageResult;
	}
	public String getWlmc() {
		return wlmc;
	}
	public void setWlmc(String wlmc) {
		this.wlmc = wlmc;
	}
	public ProductInventoryStorePageResult getProductInventoryStorePageResult() {
		return productInventoryStorePageResult;
	}
	public void setProductInventoryStorePageResult(ProductInventoryStorePageResult productInventoryStorePageResult) {
		this.productInventoryStorePageResult = productInventoryStorePageResult;
	}
	public String getCpmc() {
		return cpmc;
	}
	public void setCpmc(String cpmc) {
		this.cpmc = cpmc;
	}
	
}
