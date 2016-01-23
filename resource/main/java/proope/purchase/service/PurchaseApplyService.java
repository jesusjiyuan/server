package proope.purchase.service;

import java.util.List;
import proope.purchase.to.entity.PurchaseApplyDetailEntity;
import proope.purchase.to.entity.PurchaseApplyDetailHistoryEntity;
import proope.purchase.to.entity.PurchaseApplyEntity;
import proope.purchase.to.entity.PurchaseApplyHistoryEntity;
import proope.purchase.to.result.PurchaseApplyDetailResult;
import proope.purchase.to.result.PurchaseApplyDetailStorePageResult;
import proope.purchase.to.result.PurchaseApplyHistoryStorePageResult;
import proope.purchase.to.result.PurchaseApplyResult;
import proope.purchase.to.result.PurchaseApplyStorePageResult;

public interface PurchaseApplyService {
/**
 * 分页查询采购申请
 * @param bmmc
 * @param start
 * @param limit
 * @return
 */
	public PurchaseApplyStorePageResult findPurchaseApplyStorePageResult(String bmmc, Integer start, Integer limit);
	
	/**
	 * 创建采购申请
	 * @param purchaseApplyEntity
	 * @param purchaseApplyDetailList
	 * @param cjr
	 * @param xgr
	 * @return
	 */
	public Boolean createPurchaseApply(PurchaseApplyEntity purchaseApplyEntity, List<PurchaseApplyDetailEntity> purchaseApplyDetailList,
			String cjr, String xgr);
	
	/**
	 * 根据ID查询出采购申请
	 * @param purchaseApplyId
	 * @return
	 */
	public PurchaseApplyResult findPurchaseApplyResult(String purchaseApplyId);
	
	/**
	 * 根据采购申请ID，查询出全部明细
	 * @param purchaseApplyId
	 * @return
	 */
	public List<PurchaseApplyDetailResult> findPurchaseApplyDetailListByPaId(String purchaseApplyId);
	
	/**
	 * 更新采购申请
	 * @param purchaseApplyEntity
	 * @param purchaseApplyDetailList
	 * @param xgr
	 * @return
	 */
	public Boolean updatePurchaseApply(PurchaseApplyEntity purchaseApplyEntity, List<PurchaseApplyDetailEntity> purchaseApplyDetailList,
			String xgr);
	
	/**
	 * 删除采购申请
	 * @param purchaseApplyId
	 * @return
	 */
	public Boolean deletePurchaseApply(List<String> purchaseApplyIdList);
	
	/**
	 * 根据采购申请ID，提交采购申请
	 * @param purchaseApplyIdList
	 * @param xgr
	 * @return
	 */
	public Boolean updateSubmitPurchaseApply(List<String> purchaseApplyIdList, String xgr);
	
	/**
	 * 查询已提交的采购申请明细
	 * @return
	 */
	public PurchaseApplyDetailStorePageResult findHaveSubmitPurchaseApplyDetailStorePageResult(String bmmc, Integer start, Integer limit);
	
	/**
	 * 查询采购申请历史
	 * @param bmmc
	 * @param start
	 * @param limit
	 * @return
	 */
	public PurchaseApplyHistoryStorePageResult findPurchaseApplyHistoryStorePageResult(String bmmc, Integer start, Integer limit);
	
	/**
	 * 根据采购申请历史ID，查询出采购申请历史
	 * @param purchaseApplyHistoryId
	 * @return
	 */
	public PurchaseApplyHistoryEntity findPurchaseApplyHistoryEntityById(String purchaseApplyHistoryId);
	
	/**
	 * 根据采购申请历史ID，查询出采购申请历史明细
	 * @param purchaseApplyHistoryId
	 * @param start
	 * @param limit
	 * @return
	 */
	public List<PurchaseApplyDetailHistoryEntity> findPurchaseApplyDetailHistoryListByPadhId(String purchaseApplyHistoryId);
	
}
