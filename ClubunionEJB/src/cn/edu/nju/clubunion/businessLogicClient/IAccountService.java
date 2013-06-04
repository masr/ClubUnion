package cn.edu.nju.clubunion.businessLogicClient;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.exception.ErrorException;

@Remote
public interface IAccountService {

	public void regist(IAccountService user, AUser userInfo)
			throws ErrorException;

	public void login(IAccountService user, String email, String password)
			throws ErrorException;

	public void logout(IAccountService user) throws ErrorException;

	public int getId(); 

	public String getRealName();

	public String getNickName();

	public String getEmail();

	public boolean loggedIn();

	public int getPermissionInClub(int clubId);

	public boolean isAdmin();

	public void specifyDatabase(String s);

	public void update(int id);
}
