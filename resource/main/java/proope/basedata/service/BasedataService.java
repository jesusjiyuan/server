package proope.basedata.service;

import java.util.List;

import proope.basedata.to.result.ClientResult;
import proope.basedata.to.result.DepartmentResult;
import proope.basedata.to.result.MaterialResult;
import proope.basedata.to.result.SupplierResult;
import proope.business.to.result.ProductResult;

/**
 * 对应基础数据的service层接口
 * @author 
 *
 */
public interface BasedataService {

	/**
	 * 查询出还没有未提交创建采购申请的部门
	 * @return
	 */
	public List<DepartmentResult> findNotSubmitPurchaseApplyDepartment();
	
	/**
	 * 查询出采购申请时的物料，即采购申请明细
	 * @return
	 */
	public List<MaterialResult> findPurchaseApplyMaterialList();
	
	/**
	 * 查询出全部供应商
	 * @return
	 */
	public List<SupplierResult> findAllSupplierList();
	
	/**
	 * 查询出全部客户
	 * @return
	 */
	public List<ClientResult> findAllClientList();
	
	/**
	 * 查询出全部产品
	 * @return
	 */
	public List<ProductResult> findAllProductList();
}
