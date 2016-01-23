package proope.produce.to.entity;

import common.hibernate.BaseEntity;

/**
 * 产品入库申请
 * @author 
 *
 */
public class ProductPutWarehouseApplyEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String bmdm;  //  部门代码
	
	public ProductPutWarehouseApplyEntity() {
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
	
}
