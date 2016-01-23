package proope.business.to.result;

import proope.business.to.entity.SalesOrderDetailEntity;

/**
 * 销售订单明细Result
 * @author 
 *
 */
public class SalesOrderDetailResult extends SalesOrderDetailEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cpmc;  //  产品名称

	public SalesOrderDetailResult() {
		super();
	}
	public String getCpmc() {
		return cpmc;
	}
	public void setCpmc(String cpmc) {
		this.cpmc = cpmc;
	}

	
}
