package proope.produce.to.result;

import java.math.BigDecimal;

import proope.produce.to.entity.ProductPutWarehouseDetailHistoryEntity;

/**
 * 产品入库历史明细Result
 * @author 
 *
 */
public class ProductPutWarehouseDetailHistoryResult extends	ProductPutWarehouseDetailHistoryEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal yrksl;  // 已入库数量
	private BigDecimal sqsl;  //  申请数量

	public ProductPutWarehouseDetailHistoryResult() {
		super();
	}

	public BigDecimal getYrksl() {
		return yrksl;
	}

	public void setYrksl(BigDecimal yrksl) {
		this.yrksl = yrksl;
	}

	public BigDecimal getSqsl() {
		return sqsl;
	}

	public void setSqsl(BigDecimal sqsl) {
		this.sqsl = sqsl;
	}
	
}
