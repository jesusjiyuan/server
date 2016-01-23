package proope.quality.action;

import java.util.List;

import proope.produce.to.entity.ProductPutWarehouseHistoryEntity;
import proope.produce.to.result.ProductPutWarehouseApplyDetailStorePageResult;
import proope.produce.to.result.ProductPutWarehouseDetailHistoryResult;
import proope.quality.service.ProduceQualityService;
import common.struts.BaseAction;

/**
 * 产品品保
 * @author 
 *
 */
public class ProduceQualityAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProduceQualityService produceQualityService;
	
	/* 产品入库明细分页查询 */
	private ProductPutWarehouseApplyDetailStorePageResult productPutWarehouseApplyDetailStorePageResult;
	
	private String cpmc;  //  产品名称
	
	/* 产品入库历史  */
	private ProductPutWarehouseHistoryEntity productPutWarehouseHistoryEntity;
	
	/* 产品入库历史明细 */
	private List<ProductPutWarehouseDetailHistoryResult> productPutWarehouseDetailHistoryResultList;
	
	/**
	 * 查询入库待检产品
	 * @return
	 */
	public String findWaitCheckProductPutWarehouseApplyDetailStorePageResult(){
		productPutWarehouseApplyDetailStorePageResult = produceQualityService.findWaitCheckProductPutWarehouseApplyDetailStorePageResult(cpmc, getStart(), getLimit());
		return "productPutWarehouseApplyDetailStorePageResult";
	}
	
	/**
	 * 创建产品入库单
	 * @return
	 */
	public String createProductPutWarehouseHistory(){
		return returnResponseWrite(produceQualityService.createProductPutWarehouseHistory(productPutWarehouseHistoryEntity, 
				productPutWarehouseDetailHistoryResultList, this.findSessionUserId()), "创建入库单失败。");
	}
	
	//  ---------------------  get set  -----------------------
	public void setProduceQualityService(ProduceQualityService produceQualityService) {
		this.produceQualityService = produceQualityService;
	}
	public ProductPutWarehouseApplyDetailStorePageResult getProductPutWarehouseApplyDetailStorePageResult() {
		return productPutWarehouseApplyDetailStorePageResult;
	}
	public void setProductPutWarehouseApplyDetailStorePageResult(ProductPutWarehouseApplyDetailStorePageResult productPutWarehouseApplyDetailStorePageResult) {
		this.productPutWarehouseApplyDetailStorePageResult = productPutWarehouseApplyDetailStorePageResult;
	}
	public String getCpmc() {
		return cpmc;
	}
	public void setCpmc(String cpmc) {
		this.cpmc = cpmc;
	}
	public ProductPutWarehouseHistoryEntity getProductPutWarehouseHistoryEntity() {
		return productPutWarehouseHistoryEntity;
	}
	public void setProductPutWarehouseHistoryEntity(ProductPutWarehouseHistoryEntity productPutWarehouseHistoryEntity) {
		this.productPutWarehouseHistoryEntity = productPutWarehouseHistoryEntity;
	}
	public List<ProductPutWarehouseDetailHistoryResult> getProductPutWarehouseDetailHistoryResultList() {
		return productPutWarehouseDetailHistoryResultList;
	}
	public void setProductPutWarehouseDetailHistoryResultList(List<ProductPutWarehouseDetailHistoryResult> productPutWarehouseDetailHistoryResultList) {
		this.productPutWarehouseDetailHistoryResultList = productPutWarehouseDetailHistoryResultList;
	}
	
}
