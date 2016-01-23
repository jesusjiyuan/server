package proope.purchase.to.result;

import java.util.List;
import common.to.StorePageResultBaseTo;

/**
 * 采购订单查询
 * @author 
 *
 */
public class PurchaseOrderStorePageResult extends StorePageResultBaseTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<proope.purchase.to.result.PurchaseOrderResult> purchaseOrderList;
	
	public PurchaseOrderStorePageResult() {
		super();
	}

	public PurchaseOrderStorePageResult(Long itemTotal,	List<proope.purchase.to.result.PurchaseOrderResult> purchaseOrderList) {
		super();
		setItemTotal(itemTotal);
		this.purchaseOrderList = purchaseOrderList;
	}

	public List<proope.purchase.to.result.PurchaseOrderResult> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	public void setPurchaseOrderList(List<proope.purchase.to.result.PurchaseOrderResult> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}
	
	public Long getItemTotal() {
		return getItemCount();
	}
	
}
