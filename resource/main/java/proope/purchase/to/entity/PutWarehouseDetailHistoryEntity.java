package proope.purchase.to.entity;

import java.math.BigDecimal;

import common.hibernate.BaseEntity;

/**
 * 物料入库历史明细
 * @author 
 *
 */
public class PutWarehouseDetailHistoryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String rklsdm;  //  入库历史代码
	private String cgsqmxlsdm;  //  采购申请明细历史代码
	private String cgddmxlsdm;  //  采购订单明细历史代码',
	private String dhmxlsdm;  //  到货明细历史代码',
	private String wldm;  //  物料代码
	private String wlmc;  //  物料名称
	private BigDecimal sqsl;  //  申请数量',
	private BigDecimal dgsl;  //  订购数量',
	private BigDecimal dhsl;  //  到货数量',
	private BigDecimal rksl;  //  入库数量
	private String jyjg;  //  检验结果
	private String bz;  //  备注
	
	public PutWarehouseDetailHistoryEntity() {
		super();
	}
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getRklsdm() {
		return rklsdm;
	}
	public void setRklsdm(String rklsdm) {
		this.rklsdm = rklsdm;
	}
	public String getCgsqmxlsdm() {
		return cgsqmxlsdm;
	}
	public void setCgsqmxlsdm(String cgsqmxlsdm) {
		this.cgsqmxlsdm = cgsqmxlsdm;
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
	public BigDecimal getRksl() {
		return rksl;
	}
	public void setRksl(BigDecimal rksl) {
		this.rksl = rksl;
	}
	public String getJyjg() {
		return jyjg;
	}
	public void setJyjg(String jyjg) {
		this.jyjg = jyjg;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getCgddmxlsdm() {
		return cgddmxlsdm;
	}
	public void setCgddmxlsdm(String cgddmxlsdm) {
		this.cgddmxlsdm = cgddmxlsdm;
	}
	public String getDhmxlsdm() {
		return dhmxlsdm;
	}
	public void setDhmxlsdm(String dhmxlsdm) {
		this.dhmxlsdm = dhmxlsdm;
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
	public BigDecimal getDhsl() {
		return dhsl;
	}
	public void setDhsl(BigDecimal dhsl) {
		this.dhsl = dhsl;
	}
	
}
