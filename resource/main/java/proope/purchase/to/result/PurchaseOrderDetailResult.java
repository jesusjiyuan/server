package proope.purchase.to.result;

import java.math.BigDecimal;

import proope.purchase.to.entity.PurchaseOrderDetailEntity;

/**
 * 采购订单明细
 * @author 
 *
 */
public class PurchaseOrderDetailResult extends PurchaseOrderDetailEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String wlmc;  //  物料名称
	private String wlgg;  //  规格
	private String wldw;  //  单位
	private String gysmc;  //  供应商名称
	
	private BigDecimal ydgsl;  //  已订购数量
	
	public PurchaseOrderDetailResult() {
		super();
	}
	public String getWlmc() {
		return wlmc;
	}
	public void setWlmc(String wlmc) {
		this.wlmc = wlmc;
	}
	public String getGysmc() {
		return gysmc;
	}
	public void setGysmc(String gysmc) {
		this.gysmc = gysmc;
	}
	public String getWlgg() {
		return wlgg;
	}
	public void setWlgg(String wlgg) {
		this.wlgg = wlgg;
	}
	public String getWldw() {
		return wldw;
	}
	public void setWldw(String wldw) {
		this.wldw = wldw;
	}
	public BigDecimal getYdgsl() {
		return ydgsl;
	}
	public void setYdgsl(BigDecimal ydgsl) {
		this.ydgsl = ydgsl;
	}
	
}
