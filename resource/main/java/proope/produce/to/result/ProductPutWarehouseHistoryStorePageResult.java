package proope.produce.to.result;

import java.util.List;

import proope.produce.to.entity.ProductPutWarehouseHistoryEntity;

import common.to.StorePageResultBaseTo;

/**
 * 产品入库历史分页
 * @author 
 *
 */
public class ProductPutWarehouseHistoryStorePageResult extends StorePageResultBaseTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ProductPutWarehouseHistoryEntity> productPutWarehouseHistoryList;
	
	public ProductPutWarehouseHistoryStorePageResult() {
		super();
	}

	public ProductPutWarehouseHistoryStorePageResult(Long itemTotal, List<ProductPutWarehouseHistoryEntity> productPutWarehouseHistoryList) {
		super();
		setItemTotal(itemTotal);
		this.productPutWarehouseHistoryList = productPutWarehouseHistoryList;
	}

	public List<ProductPutWarehouseHistoryEntity> getProductPutWarehouseHistoryList() {
		return productPutWarehouseHistoryList;
	}

	public void setProductPutWarehouseHistoryList(List<ProductPutWarehouseHistoryEntity> productPutWarehouseHistoryList) {
		this.productPutWarehouseHistoryList = productPutWarehouseHistoryList;
	}
	
	public Long getItemTotal() {
		return getItemCount();
	}
	
}
