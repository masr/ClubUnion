package cn.edu.nju.clubunion.sessionBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.daoRemote.UserDAORemote;
import cn.edu.nju.clubunion.entity.User;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.PPW;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Stateful
public class AccountService implements IAccountService {
	@EJB(beanName = "UserDAO")
	UserDAORemote userDAO;

	private String email;
	private String nickName;
	private String realName;
	private int id;
	private boolean isAdministrator;
	private List<Integer> managedClubIds;
	private List<Integer> joinedClubIds;

	@PostConstruct
	public void initialize() {

		nickName = "游客";

		id = -1;
		isAdministrator = false;
		this.managedClubIds = new ArrayList<Integer>();
		this.joinedClubIds = new ArrayList<Integer>();

	}

	public void update(int id) {
		try {
			InitialContext ctx1 = new InitialContext();
			UserDAORemote userDAO = (UserDAORemote) ctx1
					.lookup("UserDAO/remote");
			this.id = id;
			this.managedClubIds = userDAO.getManagedClubIds(id);
			this.joinedClubIds = userDAO.getJoinedClubIds(id);
			User user = userDAO.getUserByID(id);
			this.email = user.getEmail();
			this.realName = user.getRealName();
			this.nickName = user.getNickName();
			this.isAdministrator = userDAO.isAdmin(id);
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public String getEmail() {
		return email;
	}

	public String getNickName() {
		return nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void login(IAccountService user, String emailname, String password)
			throws ErrorException {

		try {
			InitialContext ctx1 = new InitialContext();
			UserDAORemote userDAO = (UserDAORemote) ctx1
					.lookup("UserDAO/remote");

			if (!userDAO.checkEmail(emailname))
				throw new ErrorException("用户不存在");

			User u = userDAO.getUserByEmailAndPassword(emailname, password);

			if (u == null)
				throw new ErrorException("密码错误");

			update(u.getId());

		} catch (NamingException e) {
			e.printStackTrace();
			throw new ErrorException("连接UserDAO/remote失误");
		}

	}

	public void logout(IAccountService user) throws ErrorException {
		if (!loggedIn())
			throw new ErrorException("您未登录不能登出");
		id = -1;
		this.nickName = null;
		this.managedClubIds =new ArrayList<Integer>();
		this.joinedClubIds = new ArrayList<Integer>();
		this.isAdministrator = false;
		this.email = null;
	}

	public void regist(IAccountService user, AUser userinfo)
			throws ErrorException {

		try {
			InitialContext ctx1 = new InitialContext();
			UserDAORemote userDAO = (UserDAORemote) ctx1
					.lookup("UserDAO/remote");
 
			if (userDAO.checkEmail(userinfo.getEmail())) {
				throw new ErrorException("用户已存在");

			}

			User u = (User) userinfo;

			userDAO.insertUser(u);

		} catch (NamingException e) {
			e.printStackTrace();
			throw new ErrorException("连接UserDAO/remote失误");

		}

	}

	private boolean listContainsNum(List<Integer> list, int cid) {

		for (Integer a : list) {
			if (a.intValue() == cid)
				return true;
		}
		return false;
	}

	public int getPermissionInClub(int clubid) {

		if (this.listContainsNum(managedClubIds, clubid)) {
			return PPW.CLUB_MANAGER;
		}
		if (this.listContainsNum(joinedClubIds, clubid)) {
			return PPW.CLUB_MEMBER;
		}

		return PPW.VISITOR;

	}

	public boolean isAdmin() {

		return isAdministrator;

	}

	public int getId() {
		return this.id;
	}

	public boolean loggedIn() {

		return (this.getId() != -1);
	}

	public void specifyDatabase(String s) {
		userDAO.specifyDatabase(s);
	}

}
