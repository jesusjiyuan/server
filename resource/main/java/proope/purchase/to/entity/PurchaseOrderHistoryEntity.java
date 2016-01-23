package proope.purchase.to.entity;

import common.hibernate.BaseEntity;

/**
 * 采购订单历史
 * @author 
 *
 */
public class PurchaseOrderHistoryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String gysdm;  //  供应商代码
	private String gysmc;  //  供应商名称
	private String gysdh;  //  供应商电话
	private String gyscz;  //  供应商传真
	private String shf;  //  收货方
	private String shflxr;  //  收货方联系人
	private String shfdh;  //  收货方电话
	private String shfcz;  //  收货方传真
	
	public PurchaseOrderHistoryEntity() {
		super();
	}
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getGysdm() {
		return gysdm;
	}
	public void setGysdm(String gysdm) {
		this.gysdm = gysdm;
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
	public String getShf() {
		return shf;
	}
	public void setShf(String shf) {
		this.shf = shf;
	}
	public String getShflxr() {
		return shflxr;
	}
	public void setShflxr(String shflxr) {
		this.shflxr = shflxr;
	}
	public String getShfdh() {
		return shfdh;
	}
	public void setShfdh(String shfdh) {
		this.shfdh = shfdh;
	}
	public String getShfcz() {
		return shfcz;
	}
	public void setShfcz(String shfcz) {
		this.shfcz = shfcz;
	}
	
}
