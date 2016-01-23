package proope.basedata.to.result;

import common.hibernate.BaseEntity;

/**
 * 物料
 * @author 
 *
 */
public class MaterialResult extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;
	private String mc;
	private String gg;
	private String dw;
	
	public MaterialResult() {
		super();
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public String getGg() {
		return gg;
	}
	public void setGg(String gg) {
		this.gg = gg;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String dw) {
		this.dw = dw;
	}
	
}
