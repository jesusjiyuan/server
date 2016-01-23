package proope.purchase.to.result;

import java.util.List;
import proope.purchase.to.entity.PurchaseOrderHistoryEntity;
import common.to.StorePageResultBaseTo;

/**
 * 采购订单历史查询
 * @author 
 *
 */
public class PurchaseOrderHistoryStorePageResult extends StorePageResultBaseTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PurchaseOrderHistoryEntity> purchaseOrderHistoryList;

	public PurchaseOrderHistoryStorePageResult() {
		super();
	}

	public PurchaseOrderHistoryStorePageResult(Long itemTotal, List<PurchaseOrderHistoryEntity> purchaseOrderHistoryList) {
		super();
		setItemTotal(itemTotal);
		this.purchaseOrderHistoryList = purchaseOrderHistoryList;
	}

	public List<PurchaseOrderHistoryEntity> getPurchaseOrderHistoryList() {
		return this.purchaseOrderHistoryList;
	}

	public void setPurchaseOrderHistoryList(List<PurchaseOrderHistoryEntity> purchaseOrderHistoryList) {
		this.purchaseOrderHistoryList = purchaseOrderHistoryList;
	}

	public Long getItemTotal() {
		return getItemCount();
	}
}
