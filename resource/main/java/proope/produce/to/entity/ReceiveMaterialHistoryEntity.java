package proope.produce.to.entity;

import common.hibernate.BaseEntity;

/**
 * 领料历史表
 * 
 * @author
 *
 */
public class ReceiveMaterialHistoryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dm; // 代码
	private String bmdm; // 部门代码
	private int scyl; // 生产用料
	private int ypyl; // 样品用料
	private int bgyl; // 办公用料
	private int qtyl; // 其它用料
	private String bmmc; // 部门名称

	public ReceiveMaterialHistoryEntity() {
		super();
	}

	
	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}
	
	

	public int getScyl() {
		return scyl;
	}


	public void setScyl(int scyl) {
		this.scyl = scyl;
	}


	public int getYpyl() {
		return ypyl;
	}


	public void setYpyl(int ypyl) {
		this.ypyl = ypyl;
	}


	public int getBgyl() {
		return bgyl;
	}


	public void setBgyl(int bgyl) {
		this.bgyl = bgyl;
	}


	public int getQtyl() {
		return qtyl;
	}


	public void setQtyl(int qtyl) {
		this.qtyl = qtyl;
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

}
