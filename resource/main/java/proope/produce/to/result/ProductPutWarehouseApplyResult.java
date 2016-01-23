package proope.produce.to.result;

import proope.produce.to.entity.ProductPutWarehouseApplyEntity;

/**
 * 产品入库申请Result
 * @author 
 *
 */
public class ProductPutWarehouseApplyResult extends	ProductPutWarehouseApplyEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bmmc;  //  部门名称

	public ProductPutWarehouseApplyResult() {
		super();
	}
	
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	
}
