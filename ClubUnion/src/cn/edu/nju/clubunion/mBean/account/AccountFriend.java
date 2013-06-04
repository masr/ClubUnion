package cn.edu.nju.clubunion.mBean.account;

import java.util.List;

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
public class AccountFriend {
	private List<AUser> friends = null;
	private IAccountService user = CUH.getAccountService();
	private String test;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public AccountFriend() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IUserService service = null;

		service = (IUserService) CUH.getRemoteService("UserService/remote");
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return;
		}

		try {
			friends = service.getFriends(user, user.getId());
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
		}
	}

	public List<AUser> getFriends() {
		return friends;
	}

	public void setFriends(List<AUser> friends) {
		this.friends = friends;
	}

	public String deleteFriend() {
		IUserService service = null;
		service = (IUserService) CUH.getRemoteService("UserService/remote");
		String idstr = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("id");
		Integer id = Integer.parseInt(idstr);
		System.out.print(idstr);
		this.test = "vadsd";
		try {
			service.deleteFriend(user, id);
		} catch (ErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

}
