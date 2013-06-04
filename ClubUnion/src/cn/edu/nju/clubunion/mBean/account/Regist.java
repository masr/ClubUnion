package cn.edu.nju.clubunion.mBean.account;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.entity.User;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

/**
 * @author godfrey
 * 
 */
public class Regist {
	private String nickName = null;
	private String realName = null;
	private String email = null;
	private String password = null;
	private String passwordAgain = null;
	private String description = null;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordAgain() {
		return passwordAgain;
	}

	public void setPasswordAgain(String passwordAgain) {
		this.passwordAgain = passwordAgain;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String regist() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();

		AUser userInfo = new User();
		userInfo.setNickName(nickName);
		userInfo.setRealName(realName);
		userInfo.setEmail(email);
		userInfo.setPassword(password);
		userInfo.setDescription(description);

		try {
			user.regist(user, userInfo);

		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			return "fail";
		}
		return "success";
	}
}
