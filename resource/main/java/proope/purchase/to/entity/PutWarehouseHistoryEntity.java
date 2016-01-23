package proope.purchase.to.entity;

import common.hibernate.BaseEntity;

/**
 * 入库历史
 * @author 
 *
 */
public class PutWarehouseHistoryEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String rkbmdm;  //  入库部门代码
	private String rkbmmc;  //  入库部门名称
	private String rklb;  //  入库类别
	private String sccj;  //  生产车间
	private String bz;  //  备注
	
	public PutWarehouseHistoryEntity() {
		super();
	}
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getRkbmdm() {
		return rkbmdm;
	}
	public void setRkbmdm(String rkbmdm) {
		this.rkbmdm = rkbmdm;
	}
	public String getRklb() {
		return rklb;
	}
	public void setRklb(String rklb) {
		this.rklb = rklb;
	}
	public String getSccj() {
		return sccj;
	}
	public void setSccj(String sccj) {
		this.sccj = sccj;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getRkbmmc() {
		return rkbmmc;
	}
	public void setRkbmmc(String rkbmmc) {
		this.rkbmmc = rkbmmc;
	}
	
}
