package proope.produce.to.entity;

import java.math.BigDecimal;

import common.hibernate.BaseEntity;

/**
 * 产品入库明细
 * @author 
 *
 */
public class ProductPutWarehouseApplyDetailEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String cprksqdm;  //  产品入库申请代码
	private String cpdm;  //  产品代码
	private BigDecimal sqsl;  //  申请数量
	private BigDecimal yrksl;  //  已入库数量
	
	public ProductPutWarehouseApplyDetailEntity() {
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
	public BigDecimal getYrksl() {
		return yrksl;
	}
	public void setYrksl(BigDecimal yrksl) {
		this.yrksl = yrksl;
	}
	public String getCprksqdm() {
		return cprksqdm;
	}
	public void setCprksqdm(String cprksqdm) {
		this.cprksqdm = cprksqdm;
	}
	public BigDecimal getSqsl() {
		return sqsl;
	}
	public void setSqsl(BigDecimal sqsl) {
		this.sqsl = sqsl;
	}
	
}
