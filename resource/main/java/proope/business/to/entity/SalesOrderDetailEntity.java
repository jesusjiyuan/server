package proope.business.to.entity;

import java.math.BigDecimal;

import common.hibernate.BaseEntity;

/**
 * 销售订单明细
 * @author 
 *
 */
public class SalesOrderDetailEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dm;  //  代码
	private String xsdddm;  //  销售订单代码
	private String cpdm;  //  产品代码
	private BigDecimal sl;  //  数量
	private BigDecimal dj;  //  单价
	private BigDecimal yjfsl;  //  已交付数量
	
	public SalesOrderDetailEntity() {
		super();
	}
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getXsdddm() {
		return xsdddm;
	}
	public void setXsdddm(String xsdddm) {
		this.xsdddm = xsdddm;
	}
	public String getCpdm() {
		return cpdm;
	}
	public void setCpdm(String cpdm) {
		this.cpdm = cpdm;
	}
	public BigDecimal getSl() {
		return sl;
	}
	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}
	public BigDecimal getDj() {
		return dj;
	}
	public void setDj(BigDecimal dj) {
		this.dj = dj;
	}
	public BigDecimal getYjfsl() {
		return yjfsl;
	}
	public void setYjfsl(BigDecimal yjfsl) {
		this.yjfsl = yjfsl;
	}
	
}
