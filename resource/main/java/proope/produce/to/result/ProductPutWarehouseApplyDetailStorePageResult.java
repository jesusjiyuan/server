package proope.produce.to.result;

import java.util.List;

import common.to.StorePageResultBaseTo;

/**
 * 产品入库申请明细分页查询
 * @author 
 *
 */
public class ProductPutWarehouseApplyDetailStorePageResult extends StorePageResultBaseTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ProductPutWarehouseApplyDetailResult> productPutWarehouseApplyDetailList;

	public ProductPutWarehouseApplyDetailStorePageResult() {
		super();
	}
	
	public ProductPutWarehouseApplyDetailStorePageResult(Long itemTotal, List<ProductPutWarehouseApplyDetailResult> productPutWarehouseApplyDetailList) {
		super();
		setItemTotal(itemTotal);
		this.productPutWarehouseApplyDetailList = productPutWarehouseApplyDetailList;
	}
	
	public List<ProductPutWarehouseApplyDetailResult> getProductPutWarehouseApplyDetailList() {
		return productPutWarehouseApplyDetailList;
	}
	public void setProductPutWarehouseApplyDetailList(
			List<ProductPutWarehouseApplyDetailResult> productPutWarehouseApplyDetailList) {
		this.productPutWarehouseApplyDetailList = productPutWarehouseApplyDetailList;
	}

	public Long getItemTotal() {
		return getItemCount();
	}
}
