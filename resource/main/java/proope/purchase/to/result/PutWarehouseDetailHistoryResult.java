package proope.purchase.to.result;

import java.math.BigDecimal;
import proope.purchase.to.entity.PutWarehouseDetailHistoryEntity;

/**
 * 到货历史明细Result
 * @author 
 *
 */
public class PutWarehouseDetailHistoryResult extends PutWarehouseDetailHistoryEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal yrksl;  //  已入库数量

	public PutWarehouseDetailHistoryResult() {
		super();
	}

	public BigDecimal getYrksl() {
		return yrksl;
	}

	public void setYrksl(BigDecimal yrksl) {
		this.yrksl = yrksl;
	}
	
}
