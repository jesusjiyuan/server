package proope.produce.to.entity;

import common.hibernate.BaseEntity;

/**
 * 产品入库历史
 * @author 
 *
 */
public class ProductPutWarehouseHistoryEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String bmdm;  //  部门代码
	private String bmmc;  //  部门名称
	
	public ProductPutWarehouseHistoryEntity() {
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
