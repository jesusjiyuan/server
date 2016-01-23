package proope.basedata.to.result;

import common.hibernate.BaseEntity;

/**
 * 客户
 * @author 
 *
 */
public class ClientResult extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dm;  //  代码
	private String khmc;  //  客户名称
	
	public ClientResult() {
		super();
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getKhmc() {
		return khmc;
	}
	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}
	
}
