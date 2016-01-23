package proope.basedata.to.result;

import common.hibernate.BaseEntity;

/**
 * 供应商
 * @author 
 *
 */
public class SupplierResult extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String gysmc;  //  供应商名称
	private String lxdh;  //  联系电话
	
	public SupplierResult() {
		super();
	}
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getGysmc() {
		return gysmc;
	}
	public void setGysmc(String gysmc) {
		this.gysmc = gysmc;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

}
