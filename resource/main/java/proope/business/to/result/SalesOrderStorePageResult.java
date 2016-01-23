package proope.business.to.result;

import java.util.List;

import common.to.StorePageResultBaseTo;

/**
 * 销售订单查询
 * @author 
 *
 */
public class SalesOrderStorePageResult extends StorePageResultBaseTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SalesOrderResult> salesOrderList;
	public SalesOrderStorePageResult() {
		super();
	}
	public SalesOrderStorePageResult(Long itemTotal, List<SalesOrderResult> salesOrderList) {
		super();
		setItemTotal(itemTotal);
		this.salesOrderList = salesOrderList;
	}

	public List<SalesOrderResult> getSalesOrderList() {
		return salesOrderList;
	}

	public void setSalesOrderList(List<SalesOrderResult> salesOrderList) {
		this.salesOrderList = salesOrderList;
	}

	public Long getItemTotal() {
		return getItemCount();
	}
	
}
