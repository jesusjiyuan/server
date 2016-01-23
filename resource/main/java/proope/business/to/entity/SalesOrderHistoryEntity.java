package proope.business.to.entity;

import common.hibernate.BaseEntity;

/**
 * 销售订单历史
 * @author 
 * */
public class SalesOrderHistoryEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dm;  //  代码
	private String khdm;  //  客户代码
	private String khmc;  //  客户名称
	private String khlxdh;  //  客户联系电话
	private String bz;  //  备注
	
	public SalesOrderHistoryEntity() {
		super();
	}
	public String getDm() {
		return dm;
	}
	public void setDm(String dm) {
		this.dm = dm;
	}
	public String getKhdm() {
		return khdm;
	}
	public void setKhdm(String khdm) {
		this.khdm = khdm;
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
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	
}
