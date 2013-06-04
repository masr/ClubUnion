package cn.edu.nju.clubunion.mBean.account;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.AClub;
import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IUserService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

public class UserShow {
	private String email;
	private String description;
	private String nickName;
	private String realName;
	private List<AClub> managedClubs;
	private List<AClub> joinedClubs;
	private List<ADocument> currentDocuments;

	public UserShow() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IUserService userService;
		userService = (IUserService) CUH.getRemoteService("UserService/remote");
		String userIdString = fctx.getExternalContext()
				.getRequestParameterMap().get("user_id");
		int userId = -1;
		try {
			userId = (userIdString.length() == 0 ? user.getId() : Integer
					.parseInt(userIdString));
		} catch (NullPointerException e) {
			e.printStackTrace();
			return;
		}
		if (userService == null) {
			CUH.addServiceExceptionMessage();
			return;
		}

		AUser userInfo;

		try {
			userInfo = userService.getPersonalInfo(user, userId);
			this.email = userInfo.getEmail();
			this.description = userInfo.getDescription();
			this.nickName = userInfo.getNickName();
			this.realName = userInfo.getRealName();
			this.managedClubs = userService.getManagedClubs(user, userId);
			this.joinedClubs = userService.getJoinedClubs(user, userId);
			joinedClubs.removeAll(managedClubs);

			this.currentDocuments = new ArrayList<ADocument>();
			this.currentDocuments.addAll(userService.getNotices(user, userId));
			this.currentDocuments.addAll(userService.getPublicDocuments(user,
					userId));
			this.currentDocuments.addAll(userService.getPrivateDocuments(user,
					userId));
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			e.printStackTrace();
			return;
		}

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

	public List<AClub> getManagedClubs() {
		return managedClubs;
	}

	public void setManagedClubs(List<AClub> managedClubs) {
		this.managedClubs = managedClubs;
	}

	public List<AClub> getJoinedClubs() {
		return joinedClubs;
	}

	public void setJoinedClubs(List<AClub> joinedClubs) {
		this.joinedClubs = joinedClubs;
	}

	public List<ADocument> getCurrentDocuments() {
		return currentDocuments;
	}

	public void setCurrentDocuments(List<ADocument> currentDocuments) {
		this.currentDocuments = currentDocuments;
	}

}
