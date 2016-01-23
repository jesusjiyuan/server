package proope.produce.to.result;

import java.util.List;
import proope.produce.to.entity.ReceiveMaterialHistoryEntity;
import common.to.StorePageResultBaseTo;

/**
 * 领料历史
 * @author 
 *
 */
public class ReceiveMaterialHistoryStorePageResult extends StorePageResultBaseTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ReceiveMaterialHistoryEntity> receiveMaterialHistoryEntityList;

	public ReceiveMaterialHistoryStorePageResult() {
		super();
	}
	
	public ReceiveMaterialHistoryStorePageResult(Long itemTotal, List<ReceiveMaterialHistoryEntity> receiveMaterialHistoryEntityList) {
		super();
		setItemTotal(itemTotal);
		this.receiveMaterialHistoryEntityList = receiveMaterialHistoryEntityList;
	}

	public List<ReceiveMaterialHistoryEntity> getReceiveMaterialHistoryEntityList() {
		return receiveMaterialHistoryEntityList;
	}

	public void setReceiveMaterialHistoryEntityList(List<ReceiveMaterialHistoryEntity> receiveMaterialHistoryEntityList) {
		this.receiveMaterialHistoryEntityList = receiveMaterialHistoryEntityList;
	}
	
	public Long getItemTotal() {
		return getItemCount();
	}
}
