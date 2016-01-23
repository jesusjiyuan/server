package proope.basedata.action;

import java.util.List;
import proope.basedata.service.BasedataService;
import proope.basedata.to.result.ClientResult;
import proope.basedata.to.result.DepartmentResult;
import proope.basedata.to.result.MaterialResult;
import proope.basedata.to.result.SupplierResult;
import proope.business.to.result.ProductResult;
import common.struts.BaseAction;

/**
 * 基础数据action，用于本工程中查询基础数据
 * @author 
 *
 */
public class BasedataAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BasedataService basedataService;
	
	/* 部门List */
	private List<DepartmentResult> departmentResultList;

	/* 物料List */
	private List<MaterialResult> materialList;
	
	/* 供应商List */
	private List<SupplierResult> supplierList;
	
	/* 产品List */
	private List<ClientResult> clientList;
	
	/* 产品 */
	private List<ProductResult> productList;
	
	/**
	 * 查询还没有创建采购申请的部门
	 * @return
	 */
	public String findNotSubmitPurchaseApplyDepartment(){
		departmentResultList = basedataService.findNotSubmitPurchaseApplyDepartment();
		return "departmentResultList";
	}
	
	/**
	 * 查询出全部物料
	 * @return
	 */
	public String findAllMaterial(){
		materialList = basedataService.findPurchaseApplyMaterialList();
		return "materialList";
	}
	
	/**
	 * 查询出供应商
	 * @return
	 */
	public String findAllSupplier(){
		supplierList = basedataService.findAllSupplierList();
		return "supplierList";
	}
	
	/**
	 * 查询出全部客户
	 * @return
	 */
	public String findAllClient(){
		clientList = basedataService.findAllClientList();
		return "clientList";
	}
	
	/**
	 * 查询出产品
	 * @return
	 */
	public String findAllProduct(){
		productList = basedataService.findAllProductList();
		return "productList";
	}

	//  -----------------------------  get set -------------------------------

	public void setBasedataService(BasedataService basedataService) {
		this.basedataService = basedataService;
	}
	public List<DepartmentResult> getDepartmentResultList() {
		return departmentResultList;
	}
	public void setDepartmentResultList(List<DepartmentResult> departmentResultList) {
		this.departmentResultList = departmentResultList;
	}
	public List<MaterialResult> getMaterialList() {
		return materialList;
	}
	public void setMaterialList(List<MaterialResult> materialList) {
		this.materialList = materialList;
	}
	public List<SupplierResult> getSupplierList() {
		return supplierList;
	}
	public void setSupplierList(List<SupplierResult> supplierList) {
		this.supplierList = supplierList;
	}
	public List<ClientResult> getClientList() {
		return clientList;
	}
	public void setClientList(List<ClientResult> clientList) {
		this.clientList = clientList;
	}
	public List<ProductResult> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductResult> productList) {
		this.productList = productList;
	}
	
}
