package proope.purchase.to.result;


import java.util.List;

import proope.purchase.to.entity.PurchaseArrivalsGoodsHistoryEntity;

import common.to.StorePageResultBaseTo;

/**
 * 到货历史分页查询
 * @author 
 *
 */
public class PurchaseArrivalsGoodsHistoryStorePageResult extends StorePageResultBaseTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PurchaseArrivalsGoodsHistoryEntity> purchaseArrivalsGoodsHistoryList;

	public PurchaseArrivalsGoodsHistoryStorePageResult() {
		super();
	}

	public PurchaseArrivalsGoodsHistoryStorePageResult(Long itemTotal, List<PurchaseArrivalsGoodsHistoryEntity> purchaseArrivalsGoodsHistoryList) {
		super();
		setItemTotal(itemTotal);
		this.purchaseArrivalsGoodsHistoryList = purchaseArrivalsGoodsHistoryList;
	}

	public List<PurchaseArrivalsGoodsHistoryEntity> getPurchaseArrivalsGoodsHistoryList() {
		return purchaseArrivalsGoodsHistoryList;
	}

	public void setPurchaseArrivalsGoodsHistoryList(List<PurchaseArrivalsGoodsHistoryEntity> purchaseArrivalsGoodsHistoryList) {
		this.purchaseArrivalsGoodsHistoryList = purchaseArrivalsGoodsHistoryList;
	}
	
	public Long getItemTotal() {
		return getItemCount();
	}

}
