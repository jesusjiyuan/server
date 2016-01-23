package proope.business.to.result;

import java.math.BigDecimal;
import proope.business.to.entity.SalesOrderOutGoodsDetailHistoryEntity;

/**
 * 销售订单出货历史Result
 * @author 
 *
 */
public class SalesOrderOutGoodsDetailHistoryResult extends SalesOrderOutGoodsDetailHistoryEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal sl;  //  数量
	private BigDecimal yjfsl;  //  已交付数量
	
	public SalesOrderOutGoodsDetailHistoryResult() {
		super();
	}
	public BigDecimal getSl() {
		return sl;
	}
	public void setSl(BigDecimal sl) {
		this.sl = sl;
	}
	public BigDecimal getYjfsl() {
		return yjfsl;
	}
	public void setYjfsl(BigDecimal yjfsl) {
		this.yjfsl = yjfsl;
	}
	
}
