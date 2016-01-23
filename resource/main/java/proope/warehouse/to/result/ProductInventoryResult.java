package proope.warehouse.to.result;

import proope.warehouse.to.entity.ProductInventoryEntity;

/**
 * 产品库存量Result
 * @author 
 *
 */
public class ProductInventoryResult extends ProductInventoryEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cpmc;  //  产品名称

	public ProductInventoryResult() {
		super();
	}

	public String getCpmc() {
		return cpmc;
	}

	public void setCpmc(String cpmc) {
		this.cpmc = cpmc;
	}
	
}
