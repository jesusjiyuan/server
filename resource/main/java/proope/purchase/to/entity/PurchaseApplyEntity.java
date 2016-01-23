package proope.purchase.to.entity;

import common.hibernate.BaseEntity;

/**
 * 采购申请表
 * @author 
 *
 */
public class PurchaseApplyEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dm;  //  代码
	private String bmdm;  //  部门代码
	private Integer zt;  //  状态
	
	public PurchaseApplyEntity() {
		super();
	}
	public PurchaseApplyEntity(String dm, String bmdm, Integer zt) {
		super();
		this.dm = dm;
		this.bmdm = bmdm;
		this.zt = zt;
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getBmdm() {
		return bmdm;
	}
	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	
}
