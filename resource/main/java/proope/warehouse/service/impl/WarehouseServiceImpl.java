package proope.warehouse.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import proope.warehouse.WarehouseDao;
import proope.warehouse.service.WarehouseService;
import proope.warehouse.to.result.MaterialInventoryResult;
import proope.warehouse.to.result.MaterialInventoryStorePageResult;
import proope.warehouse.to.result.ProductInventoryResult;
import proope.warehouse.to.result.ProductInventoryStorePageResult;
import common.hibernate.BaseService;
import common.to.StorePageResultTo;

public class WarehouseServiceImpl extends BaseService implements WarehouseService {

	private WarehouseDao warehouseDao;
	public void setWarehouseDao(WarehouseDao warehouseDao) {
		this.warehouseDao = warehouseDao;
	}
	
	@Override
	public MaterialInventoryStorePageResult findMaterialInventoryStorePageResult(
			String wlmc, Integer start, Integer limit) {
		StorePageResultTo storePageResultTo = warehouseDao.searchStorePageSql("select mi.wldm, m.mc, mi.sl, mi.xgrq ", 
				" from tbl_material_inventory mi left join tbl_material m on mi.wldm = m.dm " +
		" where 1=1 " + spellingSql("and m.mc like '%", wlmc, "%' "), start, limit);
		List<MaterialInventoryResult> materialInventoryList = new ArrayList<MaterialInventoryResult>();
		MaterialInventoryResult materialInventoryResult = null;
		Object[] objs = null;
		for(Object obj : storePageResultTo.getItemList()){
			objs = (Object[]) obj;
			materialInventoryResult = new MaterialInventoryResult();
			materialInventoryResult.setWldm(String.valueOf(objs[0]));
			materialInventoryResult.setWlmc(String.valueOf(objs[1]));
			materialInventoryResult.setSl(new BigDecimal(String.valueOf(objs[2])));
			materialInventoryResult.setXgrq(stringToDate(String.valueOf(objs[3]), DBDATEFORMAT));
			materialInventoryList.add(materialInventoryResult);
		}
		return new MaterialInventoryStorePageResult(storePageResultTo.getItemTotal(), materialInventoryList);
	}
	@Override
	public ProductInventoryStorePageResult findProductInventoryStorePageResult(
			String cpmc, Integer start, Integer limit) {
		StorePageResultTo storePageResultTo = warehouseDao.searchStorePageSql("select p.cpdm, t.cpmc, p.sl, p.xgrq ", 
				" from tbl_product_inventory p left join tbl_product t on p.cpdm = t.dm " +
		" where 1=1 " + spellingSql("and t.cpmc like '%", cpmc, "%' "), start, limit);
		List<ProductInventoryResult> productInventoryList = new ArrayList<ProductInventoryResult>();
		ProductInventoryResult productInventoryResult = null;
		Object[] objs = null;
		for(Object obj : storePageResultTo.getItemList()){
			objs = (Object[]) obj;
			productInventoryResult = new ProductInventoryResult();
			productInventoryResult.setCpdm(String.valueOf(objs[0]));
			productInventoryResult.setCpmc(String.valueOf(objs[1]));
			productInventoryResult.setSl(new BigDecimal(String.valueOf(objs[2])));
			productInventoryResult.setXgrq(stringToDate(String.valueOf(objs[3]), DBDATEFORMAT));
			productInventoryList.add(productInventoryResult);
		}
		return new ProductInventoryStorePageResult(storePageResultTo.getItemTotal(), productInventoryList);
	}
	
}
