package proope.business.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import proope.business.BusinessDao;
import proope.business.service.BomService;
import proope.business.to.entity.BomEntity;
import proope.business.to.result.BomResult;
import proope.business.to.result.OrderMaterialResult;
import proope.business.to.result.OrderMaterialStorePageResult;
import proope.business.to.result.ProductResult;
import proope.business.to.result.ProductStorePageResult;
import common.hibernate.BaseService;
import common.to.StorePageResultTo;

public class BomServiceImpl extends BaseService implements BomService {

	private BusinessDao businessDao;
	public void setBusinessDao(BusinessDao businessDao) {
		this.businessDao = businessDao;
	}
	@Override
	public ProductStorePageResult findProductStorePageResultForBom(String cpmc,	Integer start, Integer limit) {
		StorePageResultTo storePageResultTo = businessDao.searchStorePageSql("select dm, cpmc, xh ", 
				" from tbl_product where 1=1 " + spellingSql("and cpmc like '%", cpmc, "%' "), start, limit);
		List<ProductResult> productList = new ArrayList<ProductResult>();
		ProductResult productResult = null;
		Object[] objs = null;
		for(Object obj : storePageResultTo.getItemList()){
			objs = (Object[]) obj;
			productResult = new ProductResult();
			productResult.setDm(String.valueOf(objs[0]));
			productResult.setCpmc(String.valueOf(objs[1]));
			productResult.setXh(String.valueOf(objs[2]));
			productList.add(productResult);
		}
		return new ProductStorePageResult(storePageResultTo.getItemTotal(), productList);
	}
	
	@Override
	public List<BomResult> findBomListByProductId(String productId) {
		List<Object[]> objsList = businessDao.searchAllSql("select bom.dm, bom.wldm, m.mc, bom.sl from tbl_bom bom left join tbl_material m on bom.wldm = m.dm where bom.cpdm = '" + productId + "'");
		List<BomResult> bomList = new ArrayList<BomResult>();
		BomResult bomResult = null;
		for (Object[] objects : objsList) {
			bomResult = new BomResult();
			bomResult.setDm(String.valueOf(objects[0]));
			bomResult.setWldm(String.valueOf(objects[1]));
			bomResult.setWlmc(String.valueOf(objects[2]));
			bomResult.setSl(new BigDecimal(String.valueOf(objects[3])));
			bomList.add(bomResult);
		}
		return bomList;
	}
	
	@Override
	public Boolean editBomListByProductId(String productId,	List<BomEntity> bomEntityList, String xgr) {
		businessDao.executeSql("delete from tbl_bom where cpdm = '"+productId+"'");
		Date nowDate = new Date();
		for(BomEntity bomEntity : bomEntityList){
			bomEntity.setDm(tool.UUID.currentTimeMillisAsRandomNumber());
			bomEntity.setCpdm(productId);
			bomEntity.setCjr(xgr);
			bomEntity.setCjrq(nowDate);
			bomEntity.setXgr(xgr);
			bomEntity.setXgrq(nowDate);
			businessDao.save(bomEntity);
		}
		return true;
	}
	
	@Override
	public OrderMaterialStorePageResult findOrderMaterialStorePageResult(String khmc, Integer start, Integer limit) {
		String listSql = "select cpwl.xsdddm, cpwl.ddmxdm, cpwl.wldm, m.mc, cpwl.wlsl, cpwl.cpdm, p.cpmc, cpwl.cpsl, cpwl.cpwlsl, cpwl.khmc " +
					"from (select b.wldm, b.sl as wlsl, (b.sl * cp.cpsl) as cpwlsl, cp.ddmxdm, cp.xsdddm, cp.cpdm, cp.cpsl, cp.khmc from tbl_bom b left join " +
					"(select sod.dm as ddmxdm, sod.xsdddm, sod.cpdm, sod.sl as cpsl, t.khmc " +
					"from tbl_sales_order_detail sod left join " +
					"(select so.dm as dddm, so.khdm, c.khmc from tbl_sales_order so left join tbl_client c on so.khdm = c.dm) t " +
					"on sod.xsdddm = t.dddm where 1=1 " + spellingSql("and khmc like '%", khmc, "%' ") + 
					" ) cp on b.cpdm = cp.cpdm where cp.khmc is not null  limit "+start+", "+limit+
					") cpwl left join tbl_product p on cpwl.cpdm = p.dm left join tbl_material m on cpwl.wldm = m.dm";
		String countSql = "select count(*) as " + SQLASNAMEITEMCOUNT +
						" from tbl_bom b left join (select sod.cpdm, t.khmc from tbl_sales_order_detail sod left join " + 
						"(select so.dm as dddm, c.khmc from tbl_sales_order so left join tbl_client c on so.khdm = c.dm) t " +
						"on sod.xsdddm = t.dddm where 1=1 " + spellingSql("and khmc like '%", khmc, "%' ") + ") cp on b.cpdm = cp.cpdm where cp.khmc is not null ";
		StorePageResultTo storePageResultTo = businessDao.searchStorePageSql(listSql, countSql);
		List<OrderMaterialResult> orderMaterialResultList = new ArrayList<OrderMaterialResult>();
		OrderMaterialResult orderMaterialResult = null;
		Object[] objs = null;
		for(Object obj : storePageResultTo.getItemList()){
			objs = (Object[]) obj;
			orderMaterialResult = new OrderMaterialResult();
			orderMaterialResult.setXsdddm(String.valueOf(objs[0]));
			orderMaterialResult.setDdmxdm(String.valueOf(objs[1]));
			orderMaterialResult.setWldm(String.valueOf(objs[2]));
			orderMaterialResult.setWlmc(String.valueOf(objs[3]));
			orderMaterialResult.setWlsl(String.valueOf(objs[4]));
			orderMaterialResult.setCpdm(String.valueOf(objs[5]));
			orderMaterialResult.setCpmc(String.valueOf(objs[6]));
			orderMaterialResult.setCpsl(String.valueOf(objs[7]));
			orderMaterialResult.setCpwlsl(String.valueOf(objs[8]));
			orderMaterialResult.setKhmc(String.valueOf(objs[9]));
			orderMaterialResultList.add(orderMaterialResult);
		}
		return new OrderMaterialStorePageResult(storePageResultTo.getItemTotal(), orderMaterialResultList);
	}
	
}
