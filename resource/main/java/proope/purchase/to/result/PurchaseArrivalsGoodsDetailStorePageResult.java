package proope.purchase.to.result;

import java.util.List;

import common.to.StorePageResultBaseTo;

/**
 * 到货明细分页查询
 * @author 
 *
 */
public class PurchaseArrivalsGoodsDetailStorePageResult extends StorePageResultBaseTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PurchaseArrivalsGoodsDetailResult> purchaseArrivalsGoodsDetailList;
	
	public PurchaseArrivalsGoodsDetailStorePageResult() {
		super();
	}
	
	public PurchaseArrivalsGoodsDetailStorePageResult(Long itemTotal, List<PurchaseArrivalsGoodsDetailResult> purchaseArrivalsGoodsDetailList) {
		super();
		setItemTotal(itemTotal);
		this.purchaseArrivalsGoodsDetailList = purchaseArrivalsGoodsDetailList;
	}

	public List<PurchaseArrivalsGoodsDetailResult> getPurchaseArrivalsGoodsDetailList() {
		return purchaseArrivalsGoodsDetailList;
	}

	public void setPurchaseArrivalsGoodsDetailList(List<PurchaseArrivalsGoodsDetailResult> purchaseArrivalsGoodsDetailList) {
		this.purchaseArrivalsGoodsDetailList = purchaseArrivalsGoodsDetailList;
	}
	
	public Long getItemTotal() {
		return getItemCount();
	}
	
}
