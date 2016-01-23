package proope.business.to.entity;

import java.math.BigDecimal;

import common.hibernate.BaseEntity;

/**
 * BOM表
 * @author 
 *
 */
public class BomEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String cpdm;  //  产品代码
	private String wldm;  //  物料代码
	private BigDecimal sl;  //  数量
	
	public BomEntity() {
		super();
	}
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getCpdm() {
		return cpdm;
	}
	public void setCpdm(String cpdm) {
		this.cpdm = cpdm;
	}
	public String getWldm() {
		return wldm;
	}
	public void setWldm(String wldm) {
		this.wldm = wldm;
	}
	public BigDecimal getSl() {
		return sl;
	}
	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}
	
}
