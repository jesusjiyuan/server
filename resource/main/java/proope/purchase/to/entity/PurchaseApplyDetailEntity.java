package proope.purchase.to.entity;

import java.math.BigDecimal;
import common.hibernate.BaseEntity;

/**
 * 采购申请明细
 * @author 
 *
 */
public class PurchaseApplyDetailEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dm;  //  代码
	private String cgsqdm;  //  采购申请代码
	private String wldm;  //  物料代码
	private BigDecimal sqsl;  //  申请数量
	private BigDecimal ycgsl;  //  已采购数量
	
	public PurchaseApplyDetailEntity() {
		super();
	}
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getCgsqdm() {
		return cgsqdm;
	}
	public void setCgsqdm(String cgsqdm) {
		this.cgsqdm = cgsqdm;
	}
	public String getWldm() {
		return wldm;
	}
	public void setWldm(String wldm) {
		this.wldm = wldm;
	}
	public BigDecimal getSqsl() {
		return sqsl;
	}
	public void setSqsl(BigDecimal sqsl) {
		this.sqsl = sqsl;
	}
	public BigDecimal getYcgsl() {
		return ycgsl;
	}
	public void setYcgsl(BigDecimal ycgsl) {
		this.ycgsl = ycgsl;
	}
	
}
