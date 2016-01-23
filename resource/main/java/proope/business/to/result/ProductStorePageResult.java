package proope.business.to.result;

import java.util.List;

import common.to.StorePageResultBaseTo;

/**
 * 查询产品
 * @author 
 *
 */
public class ProductStorePageResult extends StorePageResultBaseTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ProductResult> productList;
	
	public ProductStorePageResult() {
		super();
	}

	public ProductStorePageResult(Long itemTotal, List<ProductResult> productList) {
		super();
		setItemTotal(itemTotal);
		this.productList = productList;
	}

	public List<ProductResult> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductResult> productList) {
		this.productList = productList;
	}
	
	public Long getItemTotal() {
		return getItemCount();
	}
	
}
