package cn.edu.nju.clubunion.sessionBean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import cn.edu.nju.clubunion.abstractEntity.AClub;
import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.abstractEntity.ALetter;
import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IUserService;
import cn.edu.nju.clubunion.daoRemote.ClubDAORemote;
import cn.edu.nju.clubunion.daoRemote.UserDAORemote;
import cn.edu.nju.clubunion.entity.ClubInviteRequest;
import cn.edu.nju.clubunion.entity.FriendRequest;
import cn.edu.nju.clubunion.entity.User;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.PPW;


@Stateless
public class UserService implements IUserService {
	
	@EJB(beanName = "UserDAO")
	private UserDAORemote userDAO;
	
	
	@EJB(beanName = "ClubDAO")
	private ClubDAORemote clubDAO;

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	public void changePassword(IAccountService user, String oldpassword,
			String newpassword) throws ErrorException {

		User u = userDAO.getUserByID(user.getId());
		if (!(u.getPassword().equals(oldpassword)))
			throw new ErrorException("旧密码错误");

		u.setPassword(newpassword);
		userDAO.mergeUser(u);

	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	public void deleteFriend(IAccountService user, int friendid)
			throws ErrorException {

		if (!(userDAO.isFriend(user.getId(), friendid)))
			throw new ErrorException("未加为好友或用户不存在");

		userDAO.deleteFriend(user.getId(), friendid);

	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	public void editInfo(IAccountService user, AUser info)
			throws ErrorException {

		User uinfo = userDAO.getUserByID(user.getId());
		uinfo.setNickName(info.getNickName());
		uinfo.setRealName(info.getRealName());
		uinfo.setDescription(info.getDescription());
		userDAO.mergeUser(uinfo);
		user.update(uinfo.getId());

	}

	public AUser getPersonalInfo(IAccountService user, int userid)
			throws ErrorException {

		User u = userDAO.getUserByID(userid);
		if (u == null)
			throw new ErrorException("该用户不存在");
		return u;

	}

	@Interceptors( { cn.edu.nju.clubunion.interceptors.LogginInterceptor.class,
			cn.edu.nju.clubunion.interceptors.AdminInterceptor.class })
	public void deleteUser(IAccountService user, int userId)
			throws ErrorException {

		userDAO.deleteUser(userId);

	}

	@SuppressWarnings("unchecked")
	public List<AUser> getAllUsers(IAccountService user) throws ErrorException {

		return userDAO.getAllUsers();
		
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	@SuppressWarnings("unchecked")
	public List<AUser> getFriends(IAccountService user, int userId)
			throws ErrorException {
		return userDAO.getFriends(userId);
		
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	@SuppressWarnings("unchecked")
	public List<AClub> getJoinedClubs(IAccountService user, int userId)
			throws ErrorException {
		return userDAO.getJoinedClubs(userId);
		
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	@SuppressWarnings("unchecked")
	public List<AClub> getManagedClubs(IAccountService user, int userId)
			throws ErrorException {
		return userDAO.getManagedClubs(userId);
		

	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	@SuppressWarnings("unchecked")
	public List<AClub> getCaredClubs(IAccountService user, int userId)
			throws ErrorException {
		return userDAO.getCaredClubs(userId);
		
	}

	@SuppressWarnings("unchecked")
	public List<AClub> getNormalClubs(IAccountService user, int userId)
			throws ErrorException {
		if (userId==-1)
			return clubDAO.getAllClubs();
		
		return userDAO.getNormalClubs(userId);



	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	@SuppressWarnings("unchecked")
	public List<ALetter> getReceivedLetters(IAccountService user)
			throws ErrorException {
		return userDAO.getReceivedLetters(user.getId());
		
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	@SuppressWarnings("unchecked")
	public List<ALetter> getSentLetters(IAccountService user)
			throws ErrorException {
		return userDAO.getSentLetters(user.getId());
		
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	@SuppressWarnings("unchecked")
	public List<ADocument> getNotices(IAccountService user, int userId)
			throws ErrorException {
		List<ADocument> allDocuments = userDAO.getDocuments(userId);
		for (int i = 0; i < allDocuments.size(); i++) {
			ADocument d = allDocuments.get(i);
			if (d.getDocumentType() != PPW.NOTICE_TYPE) {
				allDocuments.remove(d);
				i--;
			}
		}
		
		return allDocuments;
		
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	@SuppressWarnings("unchecked")
	public List<ADocument> getPrivateDocuments(IAccountService user, int userId)
			throws ErrorException {

		List<ADocument> allDocuments = userDAO.getDocuments(userId);
		for (int i = 0; i < allDocuments.size(); i++) {
			ADocument d = allDocuments.get(i);
			if (d.getDocumentType() != PPW.PRIVATE_DOCUMENT_TYPE) {
				allDocuments.remove(d);
				i--;
				continue;
			}
			if (user.getPermissionInClub(d.getClubId()) == PPW.VISITOR) {
				allDocuments.remove(d);
				i--;
			}
		}

		return allDocuments;
	

	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	@SuppressWarnings("unchecked")
	public List<ADocument> getPublicDocuments(IAccountService user, int userId)
			throws ErrorException {
		List<ADocument> allDocuments = userDAO.getDocuments(userId);
		for (int i = 0; i < allDocuments.size(); i++) {
			ADocument d = allDocuments.get(i);
			if (d.getDocumentType() != PPW.PUBLIC_DOCUMENT_TYPE) {
				allDocuments.remove(d);
				i--;
			}
		}
		return allDocuments;
		
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	@SuppressWarnings("unchecked")
	public List<ARequest> getAllReceivedClubInviteRequests(IAccountService user)
			throws ErrorException {

		return userDAO
				.getAllClubInviteUserRequests(user.getId());
		

	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	@SuppressWarnings("unchecked")
	public List<ARequest> getUnsolvedReceivedClubInviteRequests(
			IAccountService user) throws ErrorException {
		List<? super ClubInviteRequest> requests;
		List<ARequest> allRequests = userDAO
				.getAllClubInviteUserRequests(user.getId());
		for (ARequest r : allRequests) {
			if (r.isProcessed())
				allRequests.remove(r);
		}
		return allRequests;
	
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	@SuppressWarnings("unchecked")
	public List<ARequest> getAllReceivedFriendRequsets(IAccountService user)
			throws ErrorException {
		return userDAO
				.getAllReceivedFriendRequests(user.getId());
		
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	@SuppressWarnings("unchecked")
	public List<ARequest> getUnsolvedReceivedFriendRequsets(IAccountService user)
			throws ErrorException {
		List<? super FriendRequest> requests;
		List<ARequest> allRequests = userDAO
				.getAllReceivedFriendRequests(user.getId());
		for	(int i=0;i<allRequests.size();i++){
			ARequest r=allRequests.get(i);
			if (r.isProcessed())
				allRequests.remove(r);
		}
		return allRequests;
	
	}

	public void specifyDatabase(String s) {
		userDAO.specifyDatabase(s);
	}

}
