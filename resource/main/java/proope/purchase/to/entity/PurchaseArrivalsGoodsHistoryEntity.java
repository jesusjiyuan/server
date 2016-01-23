package proope.purchase.to.entity;

import common.hibernate.BaseEntity;

/**
 * 到货历史
 * @author 
 *
 */
public class PurchaseArrivalsGoodsHistoryEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dm;  //  代码

	public PurchaseArrivalsGoodsHistoryEntity() {
		super();
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	
}
