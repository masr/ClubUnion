package cn.edu.nju.clubunion.sessionBean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import cn.edu.nju.clubunion.abstractEntity.AClub;
import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.abstractEntity.AMessage;
import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubService;
import cn.edu.nju.clubunion.daoRemote.ClubDAORemote;
import cn.edu.nju.clubunion.daoRemote.DocumentDAORemote;
import cn.edu.nju.clubunion.entity.Club;
import cn.edu.nju.clubunion.entity.JoinClubRequest;
import cn.edu.nju.clubunion.exception.ErrorException;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Stateless
public class ClubService implements IClubService {
	
	@EJB(beanName = "ClubDAO")
	ClubDAORemote clubDAO;
	
	@EJB(beanName = "DocumentDAO")
	DocumentDAORemote documentDAO;

	public AClub getClubInfo(IAccountService user, int clubid)
			throws ErrorException {
		Club club = clubDAO.getClubByID(clubid);
		if (club == null)
			throw new ErrorException("组织不存在");
		return club;

	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.AdminInterceptor.class)
	public void deleteClub(IAccountService user, int clubId)
			throws ErrorException {

		clubDAO.deleteClub(clubId);
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.ManagerInterceptor.class)
	public void deleteUserFromClub(IAccountService user, int clubId, int userId)
			throws ErrorException {

		if (!clubDAO.isMemberInClub(clubId, userId))
			throw new ErrorException("该成员不在社团中");

		clubDAO.deleteMember(clubId, userId);

	}

	@SuppressWarnings("unchecked")
	@Interceptors(cn.edu.nju.clubunion.interceptors.ClubManagerInterceptor.class)
	public List<ARequest> getAllReceivedUserApplyRequests(IAccountService user,
			int clubId) throws ErrorException {

		List<? super JoinClubRequest> requests = clubDAO
				.getAllUserApplyRequests(clubId);
		return (List<ARequest>) requests;

	}

	@SuppressWarnings("unchecked")
	public List<AClub> getAllClubs(IAccountService user) throws ErrorException {

		return clubDAO.getAllClubs();
		
	}

	@SuppressWarnings("unchecked")
	public List<AUser> getManagers(IAccountService user, int clubId)
			throws ErrorException {

		return clubDAO.getManagers(clubId);
	

	}

	@SuppressWarnings("unchecked")
	public List<AUser> getMembers(IAccountService user, int clubId)
			throws ErrorException {

		return clubDAO.getMembers(clubId);
		
	}

	@SuppressWarnings("unchecked")
	public List<AMessage> getMessages(IAccountService user, int clubId)
			throws ErrorException {

		return clubDAO.getMessages(clubId);
	
	}

	@SuppressWarnings("unchecked")
	public List<ADocument> getNotices(IAccountService user, int clubId)
			throws ErrorException {

		return clubDAO.getNotices(clubId);

		
	}

	@SuppressWarnings("unchecked")
	@Interceptors(cn.edu.nju.clubunion.interceptors.ClubMemberInterceptor.class)
	public List<ADocument> getPrivateDocuments(IAccountService user, int clubId)
			throws ErrorException {

		List<ADocument> allDocuments = clubDAO.getPrivateDocuments(clubId);
		for (int i = 0; i < allDocuments.size(); i++) {
			if (!documentDAO.canAccessDocument(allDocuments.get(i).getId(),
					user.getId())) {
				allDocuments.remove(allDocuments.get(i));
				i--;
			}
		}
		return  allDocuments;
		
	}

	@SuppressWarnings("unchecked")
	public List<ADocument> getPublicDocuments(IAccountService user, int clubId)
			throws ErrorException {

		return clubDAO.getPublicDocuments(clubId);

	
	}

	@SuppressWarnings("unchecked")
	@Interceptors(cn.edu.nju.clubunion.interceptors.ClubManagerInterceptor.class)
	public List<ARequest> getUnsolvedReceivedUserApplyRequsets(
			IAccountService user, int clubId) throws ErrorException {

		List<ARequest> allRequests = clubDAO
				.getAllUserApplyRequests(clubId);
		for (ARequest r : allRequests)
			if (r.isProcessed())
				allRequests.remove(r);

		return allRequests;

	
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.ClubManagerInterceptor.class)
	public void editClubInfo(IAccountService user, int clubId, AClub clubInfo)
			throws ErrorException {

		Club club = clubDAO.getClubByID(clubId);
		if (club == null)
			throw new ErrorException("该组织不存在");

		club = (Club) clubInfo;

		clubDAO.mergeClub(club);

	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.ManagerInterceptor.class)
	public void addManager(IAccountService user, int clubId, int userId)
			throws ErrorException {
		if (clubDAO.isManagerInClub(clubId, userId))
			throw new ErrorException("该用户已是管理员");

		clubDAO.addManager(clubId, userId);
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.ManagerInterceptor.class)
	public void addMember(IAccountService user, int clubId, int userId)
			throws ErrorException {
		if (clubDAO.isMemberInClub(clubId, userId))
			throw new ErrorException("该用户已在组织中");

		clubDAO.addMember(userId, clubId);
	}

	public void specifyDatabase(String s) {
		clubDAO.specifyDatabase(s);
		documentDAO.specifyDatabase(s);
	}

	@SuppressWarnings("unchecked")
	public List<AUser> getCarers(IAccountService user, int clubId)
			throws ErrorException {
		return clubDAO.getCarers(clubId);
	
	}

}
