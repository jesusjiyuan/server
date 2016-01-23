package proope.warehouse.to.result;

import proope.warehouse.to.entity.MaterialInventoryEntity;

/**
 * 物料库存量Result
 * @author 
 *
 */
public class MaterialInventoryResult extends MaterialInventoryEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String wlmc;  //  物料名称

	public MaterialInventoryResult() {
		super();
	}

	public String getWlmc() {
		return wlmc;
	}

	public void setWlmc(String wlmc) {
		this.wlmc = wlmc;
	}
	
}
