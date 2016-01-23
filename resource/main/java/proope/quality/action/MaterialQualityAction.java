package proope.quality.action;

import java.util.List;
import proope.purchase.to.entity.PutWarehouseHistoryEntity;
import proope.purchase.to.result.PurchaseArrivalsGoodsDetailStorePageResult;
import proope.purchase.to.result.PutWarehouseDetailHistoryResult;
import proope.quality.service.MaterialQualityService;
import common.struts.BaseAction;

/**
 * 物料品质管理Action
 * @author 
 *
 */
public class MaterialQualityAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MaterialQualityService materialQualityService;
	
	private String gysmc;  //  供应商名称
	
	private PutWarehouseHistoryEntity putWarehouseHistoryEntity;  //  入库单历史
	
	private List<PutWarehouseDetailHistoryResult> putWarehouseDetailHistoryResultList;  //  采购物料入库明细历史
	
	private PurchaseArrivalsGoodsDetailStorePageResult purchaseArrivalsGoodsDetailStorePageResult;
	
	/**
	 * 
	 * @return
	 */
	public String findPurchaseArrivalsGoodsDetailStorePageResult(){
		purchaseArrivalsGoodsDetailStorePageResult = materialQualityService.findPurchaseArrivalsGoodsDetailStorePageResult(gysmc, getStart(), getLimit());
		return "purchaseArrivalsGoodsDetailStorePageResult";
	}
	
	/**
	 * 创建入库单
	 * @return
	 */
	public String createMaterialPutWarehouse(){
		return returnResponseWrite(materialQualityService.createMaterialPutWarehouseHistory(putWarehouseHistoryEntity, 
				putWarehouseDetailHistoryResultList, this.findSessionUserId()), "创建入库单失败。");
	}

	//  -------------------------- get set -----------------------------------
	public void setMaterialQualityService(MaterialQualityService materialQualityService) {
		this.materialQualityService = materialQualityService;
	}
	public String getGysmc() {
		return gysmc;
	}
	public void setGysmc(String gysmc) {
		this.gysmc = gysmc;
	}
	public PutWarehouseHistoryEntity getPutWarehouseHistoryEntity() {
		return putWarehouseHistoryEntity;
	}
	public void setPutWarehouseHistoryEntity(PutWarehouseHistoryEntity putWarehouseHistoryEntity) {
		this.putWarehouseHistoryEntity = putWarehouseHistoryEntity;
	}
	public List<PutWarehouseDetailHistoryResult> getPutWarehouseDetailHistoryResultList() {
		return putWarehouseDetailHistoryResultList;
	}
	public void setPutWarehouseDetailHistoryResultList(List<PutWarehouseDetailHistoryResult> putWarehouseDetailHistoryResultList) {
		this.putWarehouseDetailHistoryResultList = putWarehouseDetailHistoryResultList;
	}
	public PurchaseArrivalsGoodsDetailStorePageResult getPurchaseArrivalsGoodsDetailStorePageResult() {
		return purchaseArrivalsGoodsDetailStorePageResult;
	}
	public void setPurchaseArrivalsGoodsDetailStorePageResult(PurchaseArrivalsGoodsDetailStorePageResult purchaseArrivalsGoodsDetailStorePageResult) {
		this.purchaseArrivalsGoodsDetailStorePageResult = purchaseArrivalsGoodsDetailStorePageResult;
	}
	
}
