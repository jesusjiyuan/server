package proope.business.to.entity;

import java.math.BigDecimal;

import common.hibernate.BaseEntity;

/**
 * 销售订单出货明细历史
 * @author 
 *
 */
public class SalesOrderOutGoodsDetailHistoryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dm;  //  代码
	private String xsddlsdm;  //  销售订单历史代码
	private String xsddmxlsdm;  //  销售订单明细历史代码
	private String cpdm;  //  产品代码
	private String cpmc;  //  产品名称
	private BigDecimal chsl;  //  出货数量
	private String bz;  //  备注
	
	public SalesOrderOutGoodsDetailHistoryEntity() {
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
	public BigDecimal getChsl() {
		return chsl;
	}
	public void setChsl(BigDecimal chsl) {
		this.chsl = chsl;
	}
	public String getXsddmxlsdm() {
		return xsddmxlsdm;
	}
	public void setXsddmxlsdm(String xsddmxlsdm) {
		this.xsddmxlsdm = xsddmxlsdm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
