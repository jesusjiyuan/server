package proope.business.to.entity;

import java.math.BigDecimal;

import common.hibernate.BaseEntity;

/**
 * 销售订单明细历史
 * @author 
 *
 */
public class SalesOrderDetailHistoryEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String xsddlsdm;  //  销售订单历史代码
	private String cpdm;  //  产品代码
	private String cpmc;  //  产品名称
	private BigDecimal sl;  //  数量
	private BigDecimal dj;  //  单价
	private BigDecimal sjsl;  //  实际数量
	
	public SalesOrderDetailHistoryEntity() {
		super();
	}
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getXsddlsdm() {
		return xsddlsdm;
	}
	public void setXsddlsdm(String xsddlsdm) {
		this.xsddlsdm = xsddlsdm;
	}
	public String getCpdm() {
		return cpdm;
	}
	public void setCpdm(String cpdm) {
		this.cpdm = cpdm;
	}
	public String getCpmc() {
		return cpmc;
	}
	public void setCpmc(String cpmc) {
		this.cpmc = cpmc;
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
	public BigDecimal getSjsl() {
		return sjsl;
	}
	public void setSjsl(BigDecimal sjsl) {
		this.sjsl = sjsl;
	}
	
}
