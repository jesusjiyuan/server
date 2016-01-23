package proope.purchase.to.entity;

import common.hibernate.BaseEntity;

/**
 * 采购到货表
 * @author 
 *
 */
public class PurchaseArrivalsGoodsEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String dm;

	public PurchaseArrivalsGoodsEntity() {
		super();
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}
	
}
