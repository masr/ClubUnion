package cn.edu.nju.clubunion.mBean.sup;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IUserService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

public class SuperUser {
	public List<AUser> getUsers() {
		return users;
	}

	public void setUsers(List<AUser> users) {
		this.users = users;
	}

	private List<AUser> users;

	public SuperUser() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IUserService service;
		IAccountService user = CUH.getAccountService();

		service = (IUserService) CUH.getRemoteService("UserService/remote");
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return;
		}
		try {
			users = service.getAllUsers(user);
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
		}
	}

}
