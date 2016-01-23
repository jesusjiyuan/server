package proope.purchase.to.entity;

import common.hibernate.BaseEntity;

/**
 * 采购订单
 * @author 
 *
 */
public class PurchaseOrderEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String gysdm;  //  供应商代码
	private String shf;  //  收货方
	private String shflxr;  //  收货方联系人
	private String shfdh;  //  收货方电话
	private String shfcz;  //  收货方传真
	private Integer zt;  //  状态
	
	public PurchaseOrderEntity() {
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
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	
}
