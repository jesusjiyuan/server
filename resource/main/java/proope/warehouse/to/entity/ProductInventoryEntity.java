package proope.warehouse.to.entity;

import java.math.BigDecimal;
import common.hibernate.BaseEntity;

/**
 * 产品库存表
 * @author 
 *
 */
public class ProductInventoryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cpdm;  //  产品代码
	private BigDecimal sl;  //  数量
	
	public ProductInventoryEntity() {
		super();
	}
	
	public String getCpdm() {
		return cpdm;
	}
	public void setCpdm(String cpdm) {
		this.cpdm = cpdm;
	}
	public BigDecimal getSl() {
		return sl;
	}
	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}
	
}
