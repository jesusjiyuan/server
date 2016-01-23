package proope.purchase.to.result;

import proope.purchase.to.entity.PurchaseApplyDetailEntity;

/**
 * 采购申请明细Result
 * @author 
 *
 */
public class PurchaseApplyDetailResult extends PurchaseApplyDetailEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String wlmc;  //  物料名称
	private String gg;  //  规格
	private String bmmc;  //  部门名称

	public PurchaseApplyDetailResult() {
		super();
	}

	public String getWlmc() {
		return wlmc;
	}

	public void setWlmc(String wlmc) {
		this.wlmc = wlmc;
	}

	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	public String getGg() {
		return gg;
	}

	public void setGg(String gg) {
		this.gg = gg;
	}
	
}
