package proope.produce.to.result;

import proope.produce.to.entity.ProductPutWarehouseApplyDetailEntity;

/**
 * 产品入库明细Result
 * @author 
 *
 */
public class ProductPutWarehouseApplyDetailResult extends ProductPutWarehouseApplyDetailEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cpmc;  //  产品名称

	public ProductPutWarehouseApplyDetailResult() {
		super();
	}

	public String getCpmc() {
		return cpmc;
	}

	public void setCpmc(String cpmc) {
		this.cpmc = cpmc;
	}
	
}
