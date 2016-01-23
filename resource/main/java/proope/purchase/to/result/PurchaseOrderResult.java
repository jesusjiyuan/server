package proope.purchase.to.result;

import proope.purchase.to.entity.PurchaseOrderEntity;

/**
 * 采购订单
 * @author 
 *
 */
public class PurchaseOrderResult extends PurchaseOrderEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String gysmc;  //供应商名称
	private String gysdh;  //  供应商电话
	private String gyscz;  //  供应商传真
	
	public PurchaseOrderResult() {
		super();
	}

	public String getGysmc() {
		return gysmc;
	}

	public void setGysmc(String gysmc) {
		this.gysmc = gysmc;
	}

	public String getGysdh() {
		return gysdh;
	}

	public void setGysdh(String gysdh) {
		this.gysdh = gysdh;
	}

	public String getGyscz() {
		return gyscz;
	}

	public void setGyscz(String gyscz) {
		this.gyscz = gyscz;
	}
	
}
