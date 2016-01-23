package proope.purchase.to.entity;

import java.math.BigDecimal;

import common.hibernate.BaseEntity;

/**
 * 采购订单明细历史
 * @author 
 *
 */
public class PurchaseOrderDetailHistoryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String cgddlsdm;  //  采购订单历史代码
	private String cgsqmxlsdm;  //  采购申请明细历史代码
	private String wldm;  //  物料代码
	private String wlmc;  //  物料名称
	private String wlgg;  //  规格
	private String wldw;  //  单位
	private BigDecimal dj;  //  含税单价
	private BigDecimal sqsl;  //  申请数量
	private BigDecimal dgsl;  //  订购数量
	private BigDecimal dhsl;  //  到货数量
	private String bz;  //  备注
	
	public PurchaseOrderDetailHistoryEntity() {
		super();
	}
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getCgddlsdm() {
		return cgddlsdm;
	}
	public void setCgddlsdm(String cgddlsdm) {
		this.cgddlsdm = cgddlsdm;
	}
	public String getWldm() {
		return wldm;
	}
	public void setWldm(String wldm) {
		this.wldm = wldm;
	}
	public String getWlmc() {
		return wlmc;
	}
	public void setWlmc(String wlmc) {
		this.wlmc = wlmc;
	}
	public String getWlgg() {
		return wlgg;
	}
	public void setWlgg(String wlgg) {
		this.wlgg = wlgg;
	}
	public String getWldw() {
		return wldw;
	}
	public void setWldw(String wldw) {
		this.wldw = wldw;
	}
	public BigDecimal getDj() {
		return dj;
	}
	public void setDj(BigDecimal dj) {
		this.dj = dj;
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
	public String getCgsqmxlsdm() {
		return cgsqmxlsdm;
	}
	public void setCgsqmxlsdm(String cgsqmxlsdm) {
		this.cgsqmxlsdm = cgsqmxlsdm;
	}
	public BigDecimal getDhsl() {
		return dhsl;
	}
	public void setDhsl(BigDecimal dhsl) {
		this.dhsl = dhsl;
	}
	public String getBz() {
		if (this.bz == null || "null".equals(this.bz)) {
			return "";
		}
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
