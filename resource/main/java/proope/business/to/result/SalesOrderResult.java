package proope.business.to.result;

import proope.business.to.entity.SalesOrderEntity;

/**
 * 销售订单result
 * @author 
 *
 */
public class SalesOrderResult extends SalesOrderEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String khmc;  //  客户名称
	private String khlxdh;  //  客户联系电话

	public SalesOrderResult() {
		super();
	}

	public String getKhmc() {
		return khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}

	public String getKhlxdh() {
		return khlxdh;
	}

	public void setKhlxdh(String khlxdh) {
		this.khlxdh = khlxdh;
	}
	
}
