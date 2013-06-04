package cn.edu.nju.clubunion.mBean.account;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

public class UserBean {
	private String nickName;
	private String realName;

	private IAccountService accountService;

	public UserBean() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		try {
			Context ctx = new InitialContext();
			accountService = (IAccountService) ctx
					.lookup("AccountService/remote");
			fctx.getExternalContext().getSessionMap().put("user",
					accountService);
			realName = accountService.getRealName();
			nickName = accountService.getNickName();
		} catch (NamingException e) {
			e.printStackTrace();

			fctx.addMessage(null, new FacesMessage("找不到服务"));
		}

	}

	public void update() {
		realName = accountService.getRealName();
		nickName = accountService.getNickName();

	}

	public boolean getLoggedIn() {
		return accountService.loggedIn();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String logout() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();

		try {

			user.logout(user);
			return "success";
		} catch (ErrorException e) {

			fctx.addMessage(null, new FacesMessage(e.getReason()));
			return "fail";
		}
	}

	public boolean getIsAdmin() {
		return accountService.isAdmin();
	}

	public IAccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(IAccountService accountService) {
		this.accountService = accountService;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRealName() {
		return realName;
	}

}
