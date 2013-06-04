package cn.edu.nju.clubunion.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Query;

import cn.edu.nju.clubunion.abstractEntity.AClubCreateRequest;
import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.daoRemote.ClubDAORemote;
import cn.edu.nju.clubunion.daoRemote.ClubDesignDAORemote;
import cn.edu.nju.clubunion.daoRemote.RequestDAORemote;
import cn.edu.nju.clubunion.daoRemote.UserDAORemote;
import cn.edu.nju.clubunion.entity.Club;
import cn.edu.nju.clubunion.entity.ClubCreateRequest;
import cn.edu.nju.clubunion.entity.User;

@Stateless

public class ClubCreateRequestDAO extends AbstractDAO implements
		RequestDAORemote {

	
	@EJB(beanName = "ClubDAO")
	private ClubDAORemote clubDAO;
	@EJB(beanName = "ClubDesignDAO")
	private ClubDesignDAORemote designDAO;
	



	public void approveRequest(int requestId) {
		ClubCreateRequest r = this.getRequest(requestId);
		if (r == null)
			return;
		r.setApproved(true);
		r.setProcessed(true);
		this.mergeRequest(r);

		Club club = new Club();
		club.setDescription(r.getDescription());
		club.setName(r.getClubName());

		club.getManagers().add(r.getSender());
		club.getMembers().add(r.getSender());
		club.getCarers().add(r.getSender());
		int clubId=clubDAO.insertClub(club);
		designDAO.createClubStyle(clubId);
	}

	public void deleteRequest(int requestId) {
		ClubCreateRequest request = this.getRequest(requestId);
		if (request == null)
			return;
		entitymanager.remove(request);
	}

	public void insertRequest(ARequest request) {
		AClubCreateRequest r = (AClubCreateRequest) request;

		ClubCreateRequest requestEntity = new ClubCreateRequest();
		requestEntity.setClubName(r.getClubName());
		requestEntity.setDescription(r.getDescription());
		requestEntity.setInstitute(r.getInstitute());
		requestEntity.setGrade(r.getGrade());

		int userId = request.getSenderId();
		User user = entitymanager.find(User.class, userId);
		requestEntity.setSender(user);
		entitymanager.persist(requestEntity);
	}

	@ManyToOne
	@EJB(beanName = "UserDAO")
	UserDAORemote userDAO;

	public boolean isRequestReceiver(int requestId, int roleId) {
		return userDAO.isAdmin(roleId);
	}

	private void mergeRequest(ClubCreateRequest request) {
		entitymanager.merge(request);

	}

	public void rejectRequest(int requestId) {
		ClubCreateRequest request = this.getRequest(requestId);
		if (request == null)
			return;
		request.setApproved(false);
		request.setProcessed(true);
		this.mergeRequest(request);
	}

	@SuppressWarnings("unchecked")
	public List<ARequest> getAllReceivedRequests(int roleId) {
		String jpql = "SELECT r FROM ClubCreateApplyRequest r ";
		Query query = entitymanager.createQuery(jpql);
		List<ARequest> result = query.getResultList();
		return result;
	}

	public ARequest getRequestById(int requestId) {
		return this.getRequest(requestId);
	}

	private ClubCreateRequest getRequest(int id) {
		ClubCreateRequest request = entitymanager.find(ClubCreateRequest.class,
				id);
		return request;
	}

}
