package proope.produce.to.result;

import java.util.List;

import common.to.StorePageResultBaseTo;

/**
 * 产品入库申请分页
 * @author 
 *
 */
public class ProductPutWarehouseApplyStorePageResult extends StorePageResultBaseTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ProductPutWarehouseApplyResult> productPutWarehouseApplyList;

	public ProductPutWarehouseApplyStorePageResult() {
		super();
	}
	
	public ProductPutWarehouseApplyStorePageResult(Long itemTotal, List<ProductPutWarehouseApplyResult> productPutWarehouseApplyList) {
		super();
		setItemTotal(itemTotal);
		this.productPutWarehouseApplyList = productPutWarehouseApplyList;
	}

	public List<ProductPutWarehouseApplyResult> getProductPutWarehouseApplyList() {
		return productPutWarehouseApplyList;
	}

	public void setProductPutWarehouseApplyList(List<ProductPutWarehouseApplyResult> productPutWarehouseApplyList) {
		this.productPutWarehouseApplyList = productPutWarehouseApplyList;
	}
	
	public Long getItemTotal() {
		return getItemCount();
	}
	
}
