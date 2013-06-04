package cn.edu.nju.clubunion.mBean.account;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IUserService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

/**
 * @author godfrey
 * 
 */
public class PasswordEdit {
	private String originalPassword = null;
	private String newPassword = null;
	private String newPasswordAgain = null;

	public String getOriginalPassword() {
		return originalPassword;
	}

	public void setOriginalPassword(String originalPassword) {
		this.originalPassword = originalPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPasswordAgain() {
		return newPasswordAgain;
	}

	public void setNewPasswordAgain(String newPasswordAgain) {
		this.newPasswordAgain = newPasswordAgain;
	}

	public String edit() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IUserService service;

		service = (IUserService) CUH.getRemoteService("UserService/remote");
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return "fail";
		}

		try {
			if (newPassword.equals(newPasswordAgain)) {
				service.changePassword(user, originalPassword, newPassword);
				return "success";
			} else {
				fctx.addMessage(null, new FacesMessage("请重新输入密码"));
				return "fail";
			}
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			return "fail";
		}
	}
}
