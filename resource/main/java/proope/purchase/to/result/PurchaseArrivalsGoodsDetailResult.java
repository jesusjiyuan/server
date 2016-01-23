package proope.purchase.to.result;

import java.math.BigDecimal;

import proope.purchase.to.entity.PurchaseArrivalsGoodsDetailEntity;

/**
 * 到货明细Result
 * @author 
 *
 */
public class PurchaseArrivalsGoodsDetailResult extends PurchaseArrivalsGoodsDetailEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String wlmc;  //  物料名称
	private BigDecimal ydhsl;  //  已到货数量
	private String gysmc;

	public PurchaseArrivalsGoodsDetailResult() {
		super();
	}

	public String getWlmc() {
		return wlmc;
	}

	public void setWlmc(String wlmc) {
		this.wlmc = wlmc;
	}

	public BigDecimal getYdhsl() {
		return ydhsl;
	}

	public void setYdhsl(BigDecimal ydhsl) {
		this.ydhsl = ydhsl;
	}

	public String getGysmc() {
		return gysmc;
	}

	public void setGysmc(String gysmc) {
		this.gysmc = gysmc;
	}
	
}
