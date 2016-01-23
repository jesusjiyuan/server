package proope.purchase.to.entity;

import java.math.BigDecimal;

import common.hibernate.BaseEntity;

/**
 * 采购到货明细表
 * @author 
 *
 */
public class PurchaseArrivalsGoodsDetailEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String dhdm;  //  到货代码
	private String cgsqmxdm;  //  采购申请明细代码
	private String cgddmxdm;  //  采购订单明细代码
	private String wldm;  //  物料代码
	private BigDecimal sqsl;  //  申请数量
	private BigDecimal dgsl;  //  订购数量
	private BigDecimal dhsl;  //  到货数量
	private BigDecimal yrksl;  //  已入库数量
	
	
	public PurchaseArrivalsGoodsDetailEntity() {
		super();
	}
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getDhdm() {
		return dhdm;
	}
	public void setDhdm(String dhdm) {
		this.dhdm = dhdm;
	}
	public String getWldm() {
		return wldm;
	}
	public void setWldm(String wldm) {
		this.wldm = wldm;
	}
	public BigDecimal getDhsl() {
		return dhsl;
	}
	public void setDhsl(BigDecimal dhsl) {
		this.dhsl = dhsl;
	}
	public String getCgsqmxdm() {
		return cgsqmxdm;
	}
	public void setCgsqmxdm(String cgsqmxdm) {
		this.cgsqmxdm = cgsqmxdm;
	}
	public String getCgddmxdm() {
		return cgddmxdm;
	}
	public void setCgddmxdm(String cgddmxdm) {
		this.cgddmxdm = cgddmxdm;
	}
	public BigDecimal getSqsl() {
		return sqsl;
	}
	public void setSqsl(BigDecimal sqsl) {
		this.sqsl = sqsl;
	}
	public BigDecimal getDgsl() {
		return dgsl;
	}
	public void setDgsl(BigDecimal dgsl) {
		this.dgsl = dgsl;
	}
	public BigDecimal getYrksl() {
		return yrksl;
	}
	public void setYrksl(BigDecimal yrksl) {
		this.yrksl = yrksl;
	}
	
}
