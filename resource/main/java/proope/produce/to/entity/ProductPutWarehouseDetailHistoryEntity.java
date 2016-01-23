package proope.produce.to.entity;

import java.math.BigDecimal;
import common.hibernate.BaseEntity;

/**
 * 产品入库明细历史
 * @author 
 *
 */
public class ProductPutWarehouseDetailHistoryEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String cprklsdm;  //  产品入库历史代码
	private String rksqlldm;  //  产品入库申请历史代码
	private String rksqmxlldm;  //  产品入库申请明细历史代码
	private String cpdm;  //  产品代码
	private String cpmc;  //  产品名称
	private BigDecimal sl;  //  数量
	private String bz;
	
	public ProductPutWarehouseDetailHistoryEntity() {
		super();
	}
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getCprklsdm() {
		return cprklsdm;
	}
	public void setCprklsdm(String cprklsdm) {
		this.cprklsdm = cprklsdm;
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
	public String getRksqlldm() {
		return rksqlldm;
	}
	public void setRksqlldm(String rksqlldm) {
		this.rksqlldm = rksqlldm;
	}
	public String getRksqmxlldm() {
		return rksqmxlldm;
	}
	public void setRksqmxlldm(String rksqmxlldm) {
		this.rksqmxlldm = rksqmxlldm;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
