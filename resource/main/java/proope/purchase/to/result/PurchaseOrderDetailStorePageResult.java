package proope.purchase.to.result;

import java.util.List;
import common.to.StorePageResultBaseTo;

/**
 * 采购明细查询
 * @author 
 *
 */
public class PurchaseOrderDetailStorePageResult extends StorePageResultBaseTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PurchaseOrderDetailResult> purchaseOrderDetailList;

	public PurchaseOrderDetailStorePageResult() {
		super();
	}

	public PurchaseOrderDetailStorePageResult(Long itemTotal, List<PurchaseOrderDetailResult> purchaseOrderDetailList) {
		super();
		setItemTotal(itemTotal);
		this.purchaseOrderDetailList = purchaseOrderDetailList;
	}

	public List<PurchaseOrderDetailResult> getPurchaseOrderDetailList() {
		return purchaseOrderDetailList;
	}

	public void setPurchaseOrderDetailList(
			List<PurchaseOrderDetailResult> purchaseOrderDetailList) {
		this.purchaseOrderDetailList = purchaseOrderDetailList;
	}
	
	public Long getItemTotal() {
		return getItemCount();
	}
}
