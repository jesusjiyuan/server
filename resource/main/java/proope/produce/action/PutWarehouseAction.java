package proope.produce.action;

import java.util.List;

import proope.produce.service.PutWarehouseService;
import proope.produce.to.entity.ProductPutWarehouseApplyDetailHistoryEntity;
import proope.produce.to.entity.ProductPutWarehouseApplyHistoryEntity;
import proope.produce.to.entity.ProductPutWarehouseDetailHistoryEntity;
import proope.produce.to.result.ProductPutWarehouseApplyDetailResult;
import proope.produce.to.result.ProductPutWarehouseApplyStorePageResult;
import proope.produce.to.result.ProductPutWarehouseHistoryStorePageResult;
import common.struts.BaseAction;

public class PutWarehouseAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PutWarehouseService putWarehouseService;

	/* 产品入库申请分页 */
	private ProductPutWarehouseApplyStorePageResult productPutWarehouseApplyStorePageResult;
	
	private String productPutWarehouseApplyId;  //  入库申请ID
	
	/* 入库申请明细List */
	private List<ProductPutWarehouseApplyDetailResult> productPutWarehouseApplyDetailResultList;
	
	private String bmmc;  //  部门名称
	
	/* 产品入库历史分页 */
	private ProductPutWarehouseHistoryStorePageResult productPutWarehouseHistoryStorePageResult;
	
	private String productPutWarehouseHistoryId; //  产品入库历史ID

	/* 产品入库历史明细 */
	private List<ProductPutWarehouseDetailHistoryEntity> productPutWarehouseDetailHistoryEntityList;
	
	/* 产品入库申请历史 */
	private ProductPutWarehouseApplyHistoryEntity productPutWarehouseApplyHistoryEntity;
	
	/* 产品入库申请明细历史 */
	private List<ProductPutWarehouseApplyDetailHistoryEntity> productPutWarehouseApplyDetailHistoryEntityList;
	
	
	/**
	 * 查询入库申请分页
	 * @return
	 */
	public String findProductPutWarehouseApplyStorePageResult(){
		productPutWarehouseApplyStorePageResult = putWarehouseService.findProductPutWarehouseApplyStorePageResult(bmmc, getStart(), getLimit());
		return "productPutWarehouseApplyStorePageResult";
	}
	
	/**
	 * 根据入库申请ID，查询出明细
	 * @return
	 */
	public String findProductPutWarehouseApplyDetailResultListByPpwaId(){
		productPutWarehouseApplyDetailResultList = putWarehouseService.findProductPutWarehouseApplyDetailResultListByPpwaId(productPutWarehouseApplyId);
		return "productPutWarehouseApplyDetailResultList";
	}
	
	/**
	 * 创建入库申请
	 * @return
	 */
	public String createProductPutWharehouseApply(){
		return returnResponseWrite(putWarehouseService.createProductPutWarehouseApply(productPutWarehouseApplyHistoryEntity, 
				productPutWarehouseApplyDetailHistoryEntityList, this.findSessionUserId()), "创建产品入库申请失败。");
	}
	
	/**
	 * 查询产品入库历史记录分页
	 * @return
	 */
	public String findProductPutWarehouseHistoryStorePageResult(){
		productPutWarehouseHistoryStorePageResult = putWarehouseService.findProductPutWarehouseHistoryStorePageResult(bmmc, getStart(), getLimit());
		return "productPutWarehouseHistoryStorePageResult";
	}
	
	/**
	 * 根据入库历史ID，查询出入库历史明细 
	 * @return
	 */
	public String findProductPutWarehouseDetailHistoryListByPpwhId(){
		productPutWarehouseDetailHistoryEntityList = putWarehouseService.findProductPutWarehouseDetailHistoryListByPpwhId(productPutWarehouseHistoryId);
		return "productPutWarehouseDetailHistoryEntityList";
	}
	
	//  ------------------------  get set  -----------------------------
	public void setPutWarehouseService(PutWarehouseService putWarehouseService) {
		this.putWarehouseService = putWarehouseService;
	}
	public ProductPutWarehouseApplyStorePageResult getProductPutWarehouseApplyStorePageResult() {
		return productPutWarehouseApplyStorePageResult;
	}
	public void setProductPutWarehouseApplyStorePageResult(
			ProductPutWarehouseApplyStorePageResult productPutWarehouseApplyStorePageResult) {
		this.productPutWarehouseApplyStorePageResult = productPutWarehouseApplyStorePageResult;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public ProductPutWarehouseHistoryStorePageResult getProductPutWarehouseHistoryStorePageResult() {
		return productPutWarehouseHistoryStorePageResult;
	}
	public void setProductPutWarehouseHistoryStorePageResult(ProductPutWarehouseHistoryStorePageResult productPutWarehouseHistoryStorePageResult) {
		this.productPutWarehouseHistoryStorePageResult = productPutWarehouseHistoryStorePageResult;
	}
	public ProductPutWarehouseApplyHistoryEntity getProductPutWarehouseApplyHistoryEntity() {
		return productPutWarehouseApplyHistoryEntity;
	}
	public void setProductPutWarehouseApplyHistoryEntity(ProductPutWarehouseApplyHistoryEntity productPutWarehouseApplyHistoryEntity) {
		this.productPutWarehouseApplyHistoryEntity = productPutWarehouseApplyHistoryEntity;
	}
	public List<ProductPutWarehouseApplyDetailHistoryEntity> getProductPutWarehouseApplyDetailHistoryEntityList() {
		return productPutWarehouseApplyDetailHistoryEntityList;
	}
	public void setProductPutWarehouseApplyDetailHistoryEntityList(List<ProductPutWarehouseApplyDetailHistoryEntity> productPutWarehouseApplyDetailHistoryEntityList) {
		this.productPutWarehouseApplyDetailHistoryEntityList = productPutWarehouseApplyDetailHistoryEntityList;
	}
	public String getProductPutWarehouseHistoryId() {
		return productPutWarehouseHistoryId;
	}
	public void setProductPutWarehouseHistoryId(String productPutWarehouseHistoryId) {
		this.productPutWarehouseHistoryId = productPutWarehouseHistoryId;
	}
	public List<ProductPutWarehouseDetailHistoryEntity> getProductPutWarehouseDetailHistoryEntityList() {
		return productPutWarehouseDetailHistoryEntityList;
	}
	public void setProductPutWarehouseDetailHistoryEntityList(List<ProductPutWarehouseDetailHistoryEntity> productPutWarehouseDetailHistoryEntityList) {
		this.productPutWarehouseDetailHistoryEntityList = productPutWarehouseDetailHistoryEntityList;
	}
	public String getProductPutWarehouseApplyId() {
		return productPutWarehouseApplyId;
	}
	public void setProductPutWarehouseApplyId(String productPutWarehouseApplyId) {
		this.productPutWarehouseApplyId = productPutWarehouseApplyId;
	}
	public List<ProductPutWarehouseApplyDetailResult> getProductPutWarehouseApplyDetailResultList() {
		return productPutWarehouseApplyDetailResultList;
	}
	public void setProductPutWarehouseApplyDetailResultList(List<ProductPutWarehouseApplyDetailResult> productPutWarehouseApplyDetailResultList) {
		this.productPutWarehouseApplyDetailResultList = productPutWarehouseApplyDetailResultList;
	}
	
}
