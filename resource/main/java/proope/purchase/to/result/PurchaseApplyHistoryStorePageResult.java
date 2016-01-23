package proope.purchase.to.result;

import java.util.List;
import proope.purchase.to.entity.PurchaseApplyHistoryEntity;
import common.to.StorePageResultBaseTo;

/**
 * 采购申请历史分页结果
 * @author 
 *
 */
public class PurchaseApplyHistoryStorePageResult extends StorePageResultBaseTo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PurchaseApplyHistoryEntity> purchaseApplyHistoryList;
	
	public PurchaseApplyHistoryStorePageResult() {
		super();
	}
	
	public PurchaseApplyHistoryStorePageResult(Long itemTotal, List<PurchaseApplyHistoryEntity> purchaseApplyHistoryList) {
		super();
		setItemTotal(itemTotal);
		this.purchaseApplyHistoryList = purchaseApplyHistoryList;
	}
	
	public List<PurchaseApplyHistoryEntity> getPurchaseApplyHistoryList() {
		return purchaseApplyHistoryList;
	}

	public void setPurchaseApplyHistoryList(List<PurchaseApplyHistoryEntity> purchaseApplyHistoryList) {
		this.purchaseApplyHistoryList = purchaseApplyHistoryList;
	}
	
	public Long getItemTotal() {
		return getItemCount();
	}
}
