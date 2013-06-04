package cn.edu.nju.clubunion.businessLogicClient;

import java.util.List;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.exception.ErrorException;

@Remote
public interface ISearchService {

	public List<AUser> searchUser(IAccountService user, AUser userInfo)
			throws ErrorException;// 根据搜索关键字（封装在userInfo里），返回相关用户列表

	public void specifyDatabase(String s);

}
