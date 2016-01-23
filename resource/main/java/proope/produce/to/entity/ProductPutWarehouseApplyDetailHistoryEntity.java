package proope.produce.to.entity;

import java.math.BigDecimal;
import common.hibernate.BaseEntity;

/**
 * 产品入库申请明细历史
 * @author 
 *
 */
public class ProductPutWarehouseApplyDetailHistoryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String cprksqlldm;  //  产品入库申请历史代码
	private String cpdm;  //  产品代码
	private String cpmc;  //  产品名称
	private BigDecimal sqsl;  //  申请数量
	private BigDecimal rksl;  //  入库数量
	
	public ProductPutWarehouseApplyDetailHistoryEntity() {
		super();
	}
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getCprksqlldm() {
		return cprksqlldm;
	}
	public void setCprksqlldm(String cprksqlldm) {
		this.cprksqlldm = cprksqlldm;
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
	public BigDecimal getSqsl() {
		return sqsl;
	}
	public void setSqsl(BigDecimal sqsl) {
		this.sqsl = sqsl;
	}
	public BigDecimal getRksl() {
		return rksl;
	}
	public void setRksl(BigDecimal rksl) {
		this.rksl = rksl;
	}
	
}
