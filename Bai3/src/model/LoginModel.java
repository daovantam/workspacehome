package model;

import bean.LoginBean;

public class LoginModel {

	public LoginModel() {

	}

	public boolean checkAccount(LoginBean loginBean) {
		if ("vantam".equals(loginBean.getUserName()) && "1234".equals(loginBean.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

}
