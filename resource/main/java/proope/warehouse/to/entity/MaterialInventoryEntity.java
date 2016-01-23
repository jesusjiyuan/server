package proope.warehouse.to.entity;

import java.math.BigDecimal;

import common.hibernate.BaseEntity;

/**
 * 物料库存
 * @author 
 *
 */
public class MaterialInventoryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String wldm;  //  物料代码
	private BigDecimal sl;  //  数量
	
	public MaterialInventoryEntity() {
		super();
	}
	
	public String getWldm() {
		return wldm;
	}
	public void setWldm(String wldm) {
		this.wldm = wldm;
	}
	public BigDecimal getSl() {
		return sl;
	}
	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}
	
}
