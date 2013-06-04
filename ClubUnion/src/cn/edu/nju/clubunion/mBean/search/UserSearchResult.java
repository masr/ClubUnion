package cn.edu.nju.clubunion.mBean.search;

import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.clubunion.abstractEntity.AUser;

public class UserSearchResult {

	private List<AUser> users = new ArrayList<AUser>();

	public List<AUser> getUsers() {
		return users;
	}

	public void setUsers(List<AUser> users) {
		this.users = users;
	}

}
