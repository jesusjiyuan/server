package proope.business.to.result;

import java.util.List;
import common.to.StorePageResultBaseTo;

/**
 * 订单物料使用查询
 * @author Administrator
 *
 */
public class OrderMaterialStorePageResult extends StorePageResultBaseTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<OrderMaterialResult> orderMaterialList;
	
	public OrderMaterialStorePageResult() {
		super();
	}
	
	public OrderMaterialStorePageResult(Long itemTotal, List<OrderMaterialResult> orderMaterialList) {
		super();
		setItemTotal(itemTotal);
		this.orderMaterialList = orderMaterialList;
	}

	public List<OrderMaterialResult> getOrderMaterialList() {
		return orderMaterialList;
	}

	public void setOrderMaterialList(List<OrderMaterialResult> orderMaterialList) {
		this.orderMaterialList = orderMaterialList;
	}
	
	public Long getItemTotal() {
		return getItemCount();
	}
}
