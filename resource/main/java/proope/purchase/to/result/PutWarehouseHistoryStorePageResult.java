package proope.purchase.to.result;

import java.util.List;
import proope.purchase.to.entity.PutWarehouseHistoryEntity;
import common.to.StorePageResultBaseTo;

/**
 * 入库单查询
 * @author 
 *
 */
public class PutWarehouseHistoryStorePageResult extends StorePageResultBaseTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<PutWarehouseHistoryEntity> putWarehouseHistoryList;

	public PutWarehouseHistoryStorePageResult() {
		super();
	}

	public PutWarehouseHistoryStorePageResult(Long itemTotal,	List<PutWarehouseHistoryEntity> putWarehouseHistoryList) {
		super();
		setItemTotal(itemTotal);
		this.putWarehouseHistoryList = putWarehouseHistoryList;
	}

	public List<PutWarehouseHistoryEntity> getPutWarehouseHistoryList() {
		return putWarehouseHistoryList;
	}

	public void setPutWarehouseHistoryList(List<PutWarehouseHistoryEntity> putWarehouseHistoryList) {
		this.putWarehouseHistoryList = putWarehouseHistoryList;
	}

	public Long getItemTotal() {
		return getItemCount();
	}
	
}
