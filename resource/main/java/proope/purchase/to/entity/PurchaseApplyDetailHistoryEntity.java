package proope.purchase.to.entity;

import java.math.BigDecimal;
import common.hibernate.BaseEntity;

/**
 * 采购申请明细历史
 * @author 
 *
 */
public class PurchaseApplyDetailHistoryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;
	private String cgsqlsdm;
	private String wldm;
	private String wlmc;
	private BigDecimal sqsl;
	private BigDecimal sjcgsl;
	
	public PurchaseApplyDetailHistoryEntity() {
		super();
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getCgsqlsdm() {
		return cgsqlsdm;
	}
	public void setCgsqlsdm(String cgsqlsdm) {
		this.cgsqlsdm = cgsqlsdm;
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
	public BigDecimal getSqsl() {
		return sqsl;
	}
	public void setSqsl(BigDecimal sqsl) {
		this.sqsl = sqsl;
	}
	public BigDecimal getSjcgsl() {
		return sjcgsl;
	}
	public void setSjcgsl(BigDecimal sjcgsl) {
		this.sjcgsl = sjcgsl;
	}
	
}
