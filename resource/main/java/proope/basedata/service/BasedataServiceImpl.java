package proope.basedata.service;

import java.util.ArrayList;
import java.util.List;
import proope.basedata.BasedataDao;
import proope.basedata.to.result.ClientResult;
import proope.basedata.to.result.DepartmentResult;
import proope.basedata.to.result.MaterialResult;
import proope.basedata.to.result.SupplierResult;
import proope.business.to.result.ProductResult;
import common.hibernate.BaseService;

public class BasedataServiceImpl extends BaseService implements BasedataService {

	private BasedataDao basedataDao;
	public void setBasedataDao(BasedataDao basedataDao) {
		this.basedataDao = basedataDao;
	}

	@Override
	public List<DepartmentResult> findNotSubmitPurchaseApplyDepartment() {
		List<Object[]> objsList = basedataDao.searchAllSql("select dm, bmmc from tbl_department where dm not in (select bmdm from tbl_purchase_apply where zt = 1)");
		List<DepartmentResult> departmentList = new ArrayList<DepartmentResult>();
		DepartmentResult departmentResult = null;
		for (Object[] objects : objsList) {
			departmentResult = new DepartmentResult();
			departmentResult.setDm(String.valueOf(objects[0]));
			departmentResult.setBmmc(String.valueOf(objects[1]));
			departmentList.add(departmentResult);
		}
		return departmentList;
	}

	@Override
	public List<MaterialResult> findPurchaseApplyMaterialList() {
		List<Object[]> objsList = basedataDao.searchAllSql("select dm, mc, gg, dw from tbl_material");
		List<MaterialResult> materialList = new ArrayList<MaterialResult>();
		MaterialResult materialResult = null;
		for (Object[] objects : objsList) {
			materialResult = new MaterialResult();
			materialResult.setDm(String.valueOf(objects[0]));
			materialResult.setMc(String.valueOf(objects[1]));
			materialResult.setGg(String.valueOf(objects[2]));
			materialResult.setDw(String.valueOf(objects[3]));
			materialList.add(materialResult);
		}
		return materialList;
	}

	@Override
	public List<SupplierResult> findAllSupplierList() {
		List<Object[]> objsList = basedataDao.searchAllSql("select dm, gysmc,lxdh from tbl_supplier");
		List<SupplierResult> supplierList = new ArrayList<SupplierResult>();
		SupplierResult supplierResult = null;
		for (Object[] objects : objsList) {
			supplierResult = new SupplierResult();
			supplierResult.setDm(String.valueOf(objects[0]));
			supplierResult.setGysmc(String.valueOf(objects[1]));
			supplierResult.setLxdh(String.valueOf(objects[2]));
			supplierList.add(supplierResult);
		}
		return supplierList;
	}

	@Override
	public List<ClientResult> findAllClientList() {
		List<Object[]> objsList = basedataDao.searchAllSql("select dm, khmc from tbl_client");
		List<ClientResult> clientList = new ArrayList<ClientResult>();
		ClientResult clientResult = null;
		for (Object[] objects : objsList) {
			clientResult = new ClientResult();
			clientResult.setDm(String.valueOf(objects[0]));
			clientResult.setKhmc(String.valueOf(objects[1]));
			clientList.add(clientResult);
		}
		return clientList;
	}

	@Override
	public List<ProductResult> findAllProductList() {
		List<Object[]> objsList = basedataDao.searchAllSql("select dm,cpmc,xh from tbl_product");
		List<ProductResult> productList = new ArrayList<ProductResult>();
		ProductResult productResult = null;
		for (Object[] objects : objsList) {
			productResult = new ProductResult();
			productResult.setDm(String.valueOf(objects[0]));
			productResult.setCpmc(String.valueOf(objects[1]));
			productResult.setXh(String.valueOf(objects[2]));
			productList.add(productResult);
		}
		return productList;
	}

}
