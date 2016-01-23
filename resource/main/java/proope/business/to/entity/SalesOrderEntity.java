package proope.business.to.entity;

import common.hibernate.BaseEntity;

/**
 * 销售订单
 * @author 
 *
 */
public class SalesOrderEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String khdm;  //  客户代码
	private Integer zt;  //  状态
	private String bz;  //  备注
	
	public SalesOrderEntity() {
		super();
	}
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getKhdm() {
		return khdm;
	}
	public void setKhdm(String khdm) {
		this.khdm = khdm;
	}
	public Integer getZt() {
		return zt;
	}
	public void setZt(Integer zt) {
		this.zt = zt;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
