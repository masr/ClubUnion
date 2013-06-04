package cn.edu.nju.clubunion.sessionBean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.ISearchService;
import cn.edu.nju.clubunion.daoRemote.UserDAORemote;
import cn.edu.nju.clubunion.entity.User;
import cn.edu.nju.clubunion.exception.ErrorException;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Stateless
public class SearchService implements ISearchService {
	
	@EJB(beanName = "UserDAO")
	UserDAORemote userDAO;

	@SuppressWarnings("unchecked")
	// 传过来的userInfo中的字段会被初始化为0或“”，可能是页面的问题
	public List<AUser> searchUser(IAccountService user, AUser userInfo)
			throws ErrorException {
		List<AUser> userList = new ArrayList<AUser>();

		if (userInfo.getId() != null && userInfo.getId() != 0) {
			User u = userDAO.getUserByID(userInfo.getId());
			if (u == null)
				return userList;
			else {
				userList.add(u);
				return  userList;
			}
		}

		if (userInfo.getEmail() != null & !userInfo.getEmail().isEmpty()) {
			User u = userDAO.findUserByEmail(userInfo.getEmail());
			if (u == null)
				return  userList;
			else {
				userList.add(u);
				return  userList;
			}
		}

		if (userInfo.getRealName() != null & !userInfo.getRealName().isEmpty()) {
			List<User> users = userDAO.getUsersByRealName(userInfo
					.getRealName());
			boolean flag = true;
			for (int i = 0; i < users.size(); i++) {
				flag = true;
				for (int j = 0; j < userList.size(); j++) {
					if ((((User) userList.get(j)).getId() == users.get(i).getId())) {
						flag = false;
					}
				}
				if (flag)	userList.add(users.get(i));
			}
		}

		return  userList;

	}

	public void specifyDatabase(String s) {
		userDAO.specifyDatabase(s);
	}

}
