package proope.business.to.result;

import common.hibernate.BaseEntity;

/**
 * 订单物料表，通过订单和BOM表，查询出的当前订单所需使用的物料
 * @author 
 *
 */
public class OrderMaterialResult extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String xsdddm;  //  销售订单代码
	private String ddmxdm;  //  订单明细代码
	private String wldm;  //  物料代码
	private String wlmc;  //  物料名称
	private String wlsl;  //  物料数量
	private String cpdm;  //  产品代码
	private String cpmc;  //  产品名称
	private String cpsl;  //  产品数量
	private String cpwlsl;  //  产品物料数量
	private String khmc;  //  客户名称
	
	public OrderMaterialResult() {
		super();
	}

	public String getXsdddm() {
		return xsdddm;
	}
	public void setXsdddm(String xsdddm) {
		this.xsdddm = xsdddm;
	}
	public String getDdmxdm() {
		return ddmxdm;
	}
	public void setDdmxdm(String ddmxdm) {
		this.ddmxdm = ddmxdm;
	}
	public String getWldm() {
		return wldm;
	}
	public void setWldm(String wldm) {
		this.wldm = wldm;
	}
	public String getWlmc() {
		return wlmc;
	}
	public void setWlmc(String wlmc) {
		this.wlmc = wlmc;
	}
	public String getWlsl() {
		return wlsl;
	}
	public void setWlsl(String wlsl) {
		this.wlsl = wlsl;
	}
	public String getCpdm() {
		return cpdm;
	}
	public void setCpdm(String cpdm) {
		this.cpdm = cpdm;
	}
	public String getCpmc() {
		return cpmc;
	}
	public void setCpmc(String cpmc) {
		this.cpmc = cpmc;
	}
	public String getCpsl() {
		return cpsl;
	}
	public void setCpsl(String cpsl) {
		this.cpsl = cpsl;
	}
	public String getCpwlsl() {
		return cpwlsl;
	}
	public void setCpwlsl(String cpwlsl) {
		this.cpwlsl = cpwlsl;
	}
	public String getKhmc() {
		return khmc;
	}
	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}
	
}
