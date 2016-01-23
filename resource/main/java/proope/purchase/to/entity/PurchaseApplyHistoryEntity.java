package proope.purchase.to.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import common.Config;
import common.hibernate.BaseEntity;

/**
 * 采购申请历史
 * @author 
 *
 */
public class PurchaseApplyHistoryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dm;  //  代码
	private String bmdm;  //  部门代码
	private String bmmc;  //  部门名称
	private Date sqtcsj;  //  申请提出时间
	
	public PurchaseApplyHistoryEntity() {
		super();
	}
	
	public PurchaseApplyHistoryEntity(String dm, String bmdm, String bmmc, Date sqtcsj) {
		super();
		this.dm = dm;
		this.bmdm = bmdm;
		this.bmmc = bmmc;
		this.sqtcsj = sqtcsj;
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
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public Date getSqtcsj() {
		return sqtcsj;
	}
	public void setSqtcsj(Date sqtcsj) {
		this.sqtcsj = sqtcsj;
	}
	
	public String getSqtcsjYMDHMS(){
		if (this.sqtcsj != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(Config.YYYYMMDDHHMMSS);
			return sdf.format(this.sqtcsj);
		}
		return "";
	}
	
}
