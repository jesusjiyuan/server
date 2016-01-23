package proope.business.to.result;

import common.hibernate.BaseEntity;

/**
 * 产品
 * @author 
 *
 */
public class ProductResult extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String cpmc;  //  产品名称
	private String xh;  //  型号
	
	public ProductResult() {
		super();
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getCpmc() {
		return cpmc;
	}
	public void setCpmc(String cpmc) {
		this.cpmc = cpmc;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	
}
