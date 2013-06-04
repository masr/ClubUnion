package cn.edu.nju.clubunion.mBean.search;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.ISearchService;
import cn.edu.nju.clubunion.entity.User;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

public class UserSearch {

	private String nickName = null;
	private String realName = null;
	private Integer userId = null;
	private String email = null;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String search() {
		AUser userInfo = new User();
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		ISearchService searchService = (ISearchService) CUH
				.getRemoteService("SearchService/remote");
		if (searchService == null) {
			CUH.addServiceExceptionMessage();
			return "fail";
		}
		userInfo.setId(userId);
		userInfo.setEmail(email);
		userInfo.setRealName(realName);
		userInfo.setNickName(nickName);

		List<AUser> users;

		try {
			users = searchService.searchUser(user, userInfo);
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			return "fail";
		}

		UserSearchResult usr = (UserSearchResult) CUH.getValueBinding(
				"#{UserSearchResultBacking}", UserSearchResult.class);
		usr.setUsers(users);

		return "success";
	}

}
