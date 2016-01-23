package proope.warehouse.to.result;

import java.util.List;
import common.to.StorePageResultBaseTo;

/**
 * 产品库存查询
 * @author 
 *
 */
public class ProductInventoryStorePageResult extends StorePageResultBaseTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ProductInventoryResult> productInventoryList;

	public ProductInventoryStorePageResult() {
		super();
	}
	
	public ProductInventoryStorePageResult(Long itemTotal, List<ProductInventoryResult> productInventoryList) {
		super();
		setItemTotal(itemTotal);
		this.productInventoryList = productInventoryList;
	}
	public List<ProductInventoryResult> getProductInventoryList() {
		return productInventoryList;
	}
	public void setProductInventoryList(List<ProductInventoryResult> productInventoryList) {
		this.productInventoryList = productInventoryList;
	}
	
	public Long getItemTotal() {
		return getItemCount();
	}
}
