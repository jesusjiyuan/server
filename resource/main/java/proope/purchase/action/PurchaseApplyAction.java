package proope.purchase.action;

import java.util.List;
import proope.purchase.service.PurchaseApplyService;
import proope.purchase.to.entity.PurchaseApplyDetailEntity;
import proope.purchase.to.entity.PurchaseApplyDetailHistoryEntity;
import proope.purchase.to.entity.PurchaseApplyEntity;
import proope.purchase.to.entity.PurchaseApplyHistoryEntity;
import proope.purchase.to.result.PurchaseApplyDetailResult;
import proope.purchase.to.result.PurchaseApplyDetailStorePageResult;
import proope.purchase.to.result.PurchaseApplyHistoryStorePageResult;
import proope.purchase.to.result.PurchaseApplyStorePageResult;
import common.struts.BaseAction;

/**
 * 采购申请action
 * @author 
 *
 */
public class PurchaseApplyAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PurchaseApplyService purchaseApplyService;
	
	/* 采购申请查询 */
	private PurchaseApplyStorePageResult purchaseApplyStorePageResult;
	
	/* 采购申请明细分页 */
	private PurchaseApplyDetailStorePageResult purchaseApplyDetailStorePageResult;
	
	private List<PurchaseApplyDetailResult> purchaseApplyDetailResultList;  //  查询全部的采购申请明细
	
	private String bmmc;  //  部门名称，查询条件
	
	private String purchaseApplyId;  //  采购申请代码
	
	public List<String> purchaseApplyIdList;  //  采购申请代码List
	
	private PurchaseApplyEntity purchaseApplyEntity;  //  采购申请实体类
	
	private List<PurchaseApplyDetailEntity> purchaseApplyDetailList; //  采购申请明细列表
	
	/* 采购申请历史查询 */
	private PurchaseApplyHistoryStorePageResult purchaseApplyHistoryStorePageResult;
	
	/* 采购申请历史明细 */
	List<PurchaseApplyDetailHistoryEntity> purchaseApplyDetailHistoryList;
	
	private PurchaseApplyHistoryEntity purchaseApplyHistoryEntity;  //  采购申请历史
	
	private String purchaseApplyHistoryId;  //采购申请历史ID  
	
	/**
	 * 查询采购申请
	 * @return
	 */
	public String findPurchaseApplyStorePageResult(){
		purchaseApplyStorePageResult = purchaseApplyService.findPurchaseApplyStorePageResult(getBmmc(), getStart(), getLimit());
		return "purchaseApplyStorePageResult";
	}
	
	/**
	 * 创建采购申请
	 * @return
	 */
	public String createPurchaseApply(){
		String loginUserId = this.findSessionUserId();
		return this.returnResponseWrite(purchaseApplyService.createPurchaseApply(purchaseApplyEntity, purchaseApplyDetailList,
				loginUserId, loginUserId), "创建采购申请失败。");
	}
	
	/**
	 * 根据采购申请ID，查询出该采购申请下全部的明细
	 * @return
	 */
	public String findPurchaseApplyDetailResultListByPaId(){
		purchaseApplyDetailResultList = purchaseApplyService.findPurchaseApplyDetailListByPaId(purchaseApplyId);
		return "purchaseApplyDetailResultList";
	}
	
	/**
	 * 更新采购申请
	 * @return
	 */
	public String updatePurchaseApply(){
		String loginUserId = this.findSessionUserId();
		return this.returnResponseWrite(purchaseApplyService.updatePurchaseApply(purchaseApplyEntity, purchaseApplyDetailList,
				loginUserId), "修改采购申请失败。");
	}
	
	/**
	 * 删除采购申请
	 * @return
	 */
	public String deletePurchaseApply(){
		return this.returnResponseWrite(purchaseApplyService.deletePurchaseApply(purchaseApplyIdList), "删除采购申请失败。");
	}
	
	/**
	 * 提交采购申请
	 * @return
	 */
	public String submitPurchaseApply(){
		String loginUserId = this.findSessionUserId();
		return this.returnResponseWrite(purchaseApplyService.updateSubmitPurchaseApply(purchaseApplyIdList, loginUserId), "提交采购申请失败。");
	}
	
	//  ---------------------  寻比议  -------------------------------
	
	/**
	 * 查询采购申请明细  对应寻比议
	 * @return
	 */
	public String findPurchaseApplyDetailStorePageResult(){
		purchaseApplyDetailStorePageResult = purchaseApplyService.findHaveSubmitPurchaseApplyDetailStorePageResult(bmmc, getStart(), getLimit());
		return "purchaseApplyDetailStorePageResult";
	}
	
    //  --------------------------------  采购申请历史  --------------------------------------------
	/**
	 * 查询采购申请历史
	 * @return
	 */
	public String findPurchaseApplyHistoryStorePageResult(){
		purchaseApplyHistoryStorePageResult = purchaseApplyService.findPurchaseApplyHistoryStorePageResult(bmmc, getStart(), getLimit());
		return "purchaseApplyHistoryStorePageResult";
	}
	
	/**
	 * 根据采购申请历史ID，查询采购申请历史
	 * @return
	 */
	public String findPurchaseApplyHistoryEntityById(){
		purchaseApplyHistoryEntity = purchaseApplyService.findPurchaseApplyHistoryEntityById(purchaseApplyHistoryId);
		return "purchaseApplyHistoryEntity";
	}
	
	/**
	 * 根据采购申请历史ID，查询采购申请明细历史
	 * @return
	 */
	public String findPurchaseApplyDetailHistoryByPadhId(){
		purchaseApplyDetailHistoryList = purchaseApplyService.findPurchaseApplyDetailHistoryListByPadhId(purchaseApplyHistoryId);
		return "purchaseApplyDetailHistoryList";
	}
	
	//  --------------------  get set -------------------------
	public void setPurchaseApplyService(PurchaseApplyService purchaseApplyService) {
		this.purchaseApplyService = purchaseApplyService;
	}
	
	public PurchaseApplyStorePageResult getPurchaseApplyStorePageResult() {
		return purchaseApplyStorePageResult;
	}
	public void setPurchaseApplyStorePageResult(PurchaseApplyStorePageResult purchaseApplyStorePageResult) {
		this.purchaseApplyStorePageResult = purchaseApplyStorePageResult;
	}
	public PurchaseApplyHistoryStorePageResult getPurchaseApplyHistoryStorePageResult() {
		return purchaseApplyHistoryStorePageResult;
	}
	public void setPurchaseApplyHistoryStorePageResult(PurchaseApplyHistoryStorePageResult purchaseApplyHistoryStorePageResult) {
		this.purchaseApplyHistoryStorePageResult = purchaseApplyHistoryStorePageResult;
	}
	public PurchaseApplyDetailStorePageResult getPurchaseApplyDetailStorePageResult() {
		return purchaseApplyDetailStorePageResult;
	}
	public void setPurchaseApplyDetailStorePageResult(PurchaseApplyDetailStorePageResult purchaseApplyDetailStorePageResult) {
		this.purchaseApplyDetailStorePageResult = purchaseApplyDetailStorePageResult;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public PurchaseApplyEntity getPurchaseApplyEntity() {
		return purchaseApplyEntity;
	}
	public void setPurchaseApplyEntity(PurchaseApplyEntity purchaseApplyEntity) {
		this.purchaseApplyEntity = purchaseApplyEntity;
	}
	public List<PurchaseApplyDetailEntity> getPurchaseApplyDetailList() {
		return purchaseApplyDetailList;
	}
	public void setPurchaseApplyDetailList(List<PurchaseApplyDetailEntity> purchaseApplyDetailList) {
		this.purchaseApplyDetailList = purchaseApplyDetailList;
	}
	public String getPurchaseApplyId() {
		return purchaseApplyId;
	}
	public void setPurchaseApplyId(String purchaseApplyId) {
		this.purchaseApplyId = purchaseApplyId;
	}
	public String getPurchaseApplyHistoryId() {
		return purchaseApplyHistoryId;
	}
	public void setPurchaseApplyHistoryId(String purchaseApplyHistoryId) {
		this.purchaseApplyHistoryId = purchaseApplyHistoryId;
	}
	public PurchaseApplyHistoryEntity getPurchaseApplyHistoryEntity() {
		return purchaseApplyHistoryEntity;
	}
	public void setPurchaseApplyHistoryEntity(PurchaseApplyHistoryEntity purchaseApplyHistoryEntity) {
		this.purchaseApplyHistoryEntity = purchaseApplyHistoryEntity;
	}
	public List<PurchaseApplyDetailResult> getPurchaseApplyDetailResultList() {
		return purchaseApplyDetailResultList;
	}
	public void setPurchaseApplyDetailResultList(List<PurchaseApplyDetailResult> purchaseApplyDetailResultList) {
		this.purchaseApplyDetailResultList = purchaseApplyDetailResultList;
	}
	public List<String> getPurchaseApplyIdList() {
		return purchaseApplyIdList;
	}
	public void setPurchaseApplyIdList(List<String> purchaseApplyIdList) {
		this.purchaseApplyIdList = purchaseApplyIdList;
	}
	public List<PurchaseApplyDetailHistoryEntity> getPurchaseApplyDetailHistoryList() {
		return purchaseApplyDetailHistoryList;
	}
	public void setPurchaseApplyDetailHistoryList(List<PurchaseApplyDetailHistoryEntity> purchaseApplyDetailHistoryList) {
		this.purchaseApplyDetailHistoryList = purchaseApplyDetailHistoryList;
	}
	
}
