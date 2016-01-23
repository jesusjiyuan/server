package proope.basedata.to.result;

import common.hibernate.BaseEntity;

/**
 * 基础数据－部门
 * @author 
 *
 */
public class DepartmentResult extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dm;  //  代码
	private String bmmc;  //  部门名称
	
	public DepartmentResult() {
		super();
	}
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	
}
