package proope.purchase.to.result;

import java.util.List;

import common.to.StorePageResultBaseTo;

/**
 * 采购申请分页
 * @author 
 *
 */
public class PurchaseApplyStorePageResult extends StorePageResultBaseTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PurchaseApplyResult> purchaseApplyList;

	public PurchaseApplyStorePageResult() {
		super();
	}
	
	public PurchaseApplyStorePageResult(Long itemTotal, List<PurchaseApplyResult> purchaseApplyList) {
		super();
		setItemTotal(itemTotal);
		this.purchaseApplyList = purchaseApplyList;
	}

	public List<PurchaseApplyResult> getPurchaseApplyList() {
		return purchaseApplyList;
	}

	public void setPurchaseApplyList(List<PurchaseApplyResult> purchaseApplyList) {
		this.purchaseApplyList = purchaseApplyList;
	}

	public Long getItemTotal() {
		return getItemCount();
	}
}
