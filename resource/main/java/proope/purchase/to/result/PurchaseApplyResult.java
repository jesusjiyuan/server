package proope.purchase.to.result;

import proope.purchase.to.entity.PurchaseApplyEntity;

public class PurchaseApplyResult extends PurchaseApplyEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String bmmc;  //  部门名称

	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	
}
