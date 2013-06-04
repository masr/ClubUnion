package cn.edu.nju.clubunion.mBean.account;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IUserService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

/**
 * @author godfrey
 * 
 */
public class AccountEdit {
	private String nickName = null;
	private String realName = null;
	private String email = null;
	private String description = null;

	public AccountEdit() {
		IUserService userService;
		IAccountService user;
		FacesContext fctx = FacesContext.getCurrentInstance();
		user = CUH.getAccountService();
		userService = (IUserService) CUH.getRemoteService("UserService/remote");
		if (userService == null) {
			CUH.addServiceExceptionMessage();
			return;
		}
		try {
			AUser userInfo = userService.getPersonalInfo(user, user.getId());
			this.description = userInfo.getDescription();
			this.email = userInfo.getEmail();
			this.nickName = userInfo.getNickName();
			this.realName = userInfo.getRealName();
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			e.printStackTrace();
		}
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String edit() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IUserService userService = (IUserService) CUH
				.getRemoteService("UserService/remote");
		if (userService == null) {
			CUH.addServiceExceptionMessage();
			return "fail";
		}

		AUser userInfo;
		try {
			userInfo = userService.getPersonalInfo(user, user.getId());
		} catch (ErrorException e1) {
			fctx.addMessage(null, new FacesMessage(e1.getReason()));
			e1.printStackTrace();
			return "fail";
		}

		userInfo.setNickName(nickName);
		userInfo.setRealName(realName);
		userInfo.setEmail(email);
		userInfo.setDescription(description);

		try {
			userService.editInfo(user, userInfo);
			return "success";
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			return "fail";
		}
	}
}
