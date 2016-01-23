package proope.purchase.to.entity;

import java.math.BigDecimal;

import common.hibernate.BaseEntity;

/**
 * 采购订单明细
 * @author 
 *
 */
public class PurchaseOrderDetailEntity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String cgdddm;  //  采购订单代码
	private String cgsqmxdm;  //  采购申请明细代码
	private String wldm;  //  物料代码
	private BigDecimal dj;  //  含税单价
	private BigDecimal sqsl;  //  申请数量
	private BigDecimal dgsl;  //  订购数量
	private BigDecimal ydhsl;  //  已到货数量
	private String bz;  //  备注
	
	public PurchaseOrderDetailEntity() {
		super();
	}
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getCgdddm() {
		return cgdddm;
	}
	public void setCgdddm(String cgdddm) {
		this.cgdddm = cgdddm;
	}
	public String getWldm() {
		return wldm;
	}
	public void setWldm(String wldm) {
		this.wldm = wldm;
	}
	public BigDecimal getDj() {
		return dj;
	}
	public void setDj(BigDecimal dj) {
		this.dj = dj;
	}
	public BigDecimal getDgsl() {
		return dgsl;
	}
	public void setDgsl(BigDecimal dgsl) {
		this.dgsl = dgsl;
	}
	public String getCgsqmxdm() {
		return cgsqmxdm;
	}
	public void setCgsqmxdm(String cgsqmxdm) {
		this.cgsqmxdm = cgsqmxdm;
	}
	public BigDecimal getSqsl() {
		return sqsl;
	}
	public void setSqsl(BigDecimal sqsl) {
		this.sqsl = sqsl;
	}
	public BigDecimal getYdhsl() {
		return ydhsl;
	}
	public void setYdhsl(BigDecimal ydhsl) {
		this.ydhsl = ydhsl;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
