package proope.test;

import common.Config;
import common.struts.BaseAction;
import common.to.SessionUserTo;

public class TestAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String userLogin(){
		SessionUserTo sessionUserTo = new SessionUserTo();
		sessionUserTo.setUserId("admin");
		sessionUserTo.setUserName("系统管理员");
		this.getHttpSession().setAttribute(Config.LOGINUSERSESSIONKEY, sessionUserTo);
		return returnResponseWrite(true, "");
	}

}
