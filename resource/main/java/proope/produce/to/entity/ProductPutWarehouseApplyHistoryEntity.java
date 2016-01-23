package proope.produce.to.entity;

import common.hibernate.BaseEntity;

/**
 * 入库申请历史表
 * @author 
 *
 */
public class ProductPutWarehouseApplyHistoryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //   代码
	private String bmdm;  //   部门代码
	private String bmmc;  //   部门名称
	
	public ProductPutWarehouseApplyHistoryEntity() {
		super();
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
	
}
