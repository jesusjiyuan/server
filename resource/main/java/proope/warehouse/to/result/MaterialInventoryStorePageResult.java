package proope.warehouse.to.result;

import java.util.List;
import common.to.StorePageResultBaseTo;

/**
 * 物料库存分页
 * @author 
 *
 */
public class MaterialInventoryStorePageResult extends StorePageResultBaseTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<MaterialInventoryResult> materialInventoryList;

	
	public MaterialInventoryStorePageResult() {
		super();
	}

	public MaterialInventoryStorePageResult(Long itemTotal, List<MaterialInventoryResult> materialInventoryList) {
		super();
		setItemTotal(itemTotal);
		this.materialInventoryList = materialInventoryList;
	}

	public List<MaterialInventoryResult> getMaterialInventoryList() {
		return materialInventoryList;
	}
	public void setMaterialInventoryList(List<MaterialInventoryResult> materialInventoryList) {
		this.materialInventoryList = materialInventoryList;
	}
	
	public Long getItemTotal() {
		return getItemCount();
	}
}
