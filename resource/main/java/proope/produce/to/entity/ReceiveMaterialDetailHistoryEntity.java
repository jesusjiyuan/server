package proope.produce.to.entity;

import java.math.BigDecimal;

import common.hibernate.BaseEntity;

/**
 * 领料历史明细
 * @author 
 *
 */
public class ReceiveMaterialDetailHistoryEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String lllsdm;  //  领料历史代码
	private String wldm;  //  物料代码
	private String wlmc;  //  物料名称
	private BigDecimal sl;  //  数量
	
	public ReceiveMaterialDetailHistoryEntity() {
		super();
	}
	
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getLllsdm() {
		return lllsdm;
	}
	public void setLllsdm(String lllsdm) {
		this.lllsdm = lllsdm;
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
	public BigDecimal getSl() {
		return sl;
	}
	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}
	
}
