package proope.purchase.to.result;

import java.util.List;
import common.to.StorePageResultBaseTo;

/**
 * 采购申请明细查询结果
 * @author 
 *
 */
public class PurchaseApplyDetailStorePageResult extends StorePageResultBaseTo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PurchaseApplyDetailResult> purchaseApplyDetailList;

	public PurchaseApplyDetailStorePageResult() {
		super();
	}

	public PurchaseApplyDetailStorePageResult(Long itemTotal, List<PurchaseApplyDetailResult> purchaseApplyDetailList) {
		super();
		setItemTotal(itemTotal);
		this.purchaseApplyDetailList = purchaseApplyDetailList;
	}

	public List<PurchaseApplyDetailResult> getPurchaseApplyDetailList() {
		return purchaseApplyDetailList;
	}

	public void setPurchaseApplyDetailList(
			List<PurchaseApplyDetailResult> purchaseApplyDetailList) {
		this.purchaseApplyDetailList = purchaseApplyDetailList;
	}
	
	public Long getItemTotal() {
		return getItemCount();
	}
	
}
