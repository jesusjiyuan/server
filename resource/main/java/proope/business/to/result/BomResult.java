package proope.business.to.result;

import proope.business.to.entity.BomEntity;

/**
 * Bom表对应的Result
 * @author 
 *
 */
public class BomResult extends BomEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String wlmc;  //  物料名称

	public BomResult() {
		super();
	}
	public String getWlmc() {
		return wlmc;
	}
	public void setWlmc(String wlmc) {
		this.wlmc = wlmc;
	}
	
}
