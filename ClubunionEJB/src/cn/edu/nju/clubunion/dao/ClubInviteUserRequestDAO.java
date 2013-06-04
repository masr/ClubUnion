package cn.edu.nju.clubunion.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Query;

import cn.edu.nju.clubunion.abstractEntity.AClubInviteRequest;
import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.daoRemote.RequestDAORemote;
import cn.edu.nju.clubunion.entity.Club;
import cn.edu.nju.clubunion.entity.ClubInviteRequest;
import cn.edu.nju.clubunion.entity.User;


@Stateless

public class ClubInviteUserRequestDAO extends AbstractDAO implements
		RequestDAORemote {

	public void approveRequest(int requestId) {
		ClubInviteRequest r = this.getRequest(requestId);
		if (r == null)
			return;
		r.setApproved(true);
		r.setProcessed(true);
		this.mergeRequest(r);

		r.getSender().getMembers().add(r.getReceiver());
		r.getSender().getCarers().add(r.getReceiver());
		r.getReceiver().getJoinedClubs().add(r.getSender());
		r.getReceiver().getCaredClubs().add(r.getSender());

	}

	public void deleteRequest(int requestId) {
		ClubInviteRequest request = this.getRequest(requestId);
		if (request == null)
			return;
		entitymanager.remove(request);

	}

	public void insertRequest(ARequest request) {
		AClubInviteRequest r = (AClubInviteRequest) request;
		ClubInviteRequest requestEntity = new ClubInviteRequest();
		requestEntity.setDescription(r.getDescription());
		Club club = entitymanager.find(Club.class, r.getSenderId());
		User user = entitymanager.find(User.class, r.getReceiverId());
		requestEntity.setSender(club);
		requestEntity.setReceiver(user);
		entitymanager.persist(requestEntity);
	}

	public boolean isRequestReceiver(int requestId, int roleId) {
		ClubInviteRequest request = this.getRequest(requestId);
		User user = entitymanager.find(User.class, roleId);
		if (request == null)
			return false;
		if (request.getReceiver().equals(user))
			return true;
		else
			return false;
	}

	private void mergeRequest(ClubInviteRequest request) {
		entitymanager.merge(request);

	}

	public void rejectRequest(int requestId) {
		ClubInviteRequest request = this.getRequest(requestId);
		if (request == null)
			return;
		request.setProcessed(true);
		request.setApproved(false);
		this.mergeRequest(request);
	}

	@SuppressWarnings("unchecked")
	public List<ARequest> getAllReceivedRequests(int roleId) {
		String jpql = "SELECT r FROM ClubInviteUserRequest r where userId="
				+ roleId;
		Query query = entitymanager.createQuery(jpql);
		List<ARequest> result = query.getResultList();
		return result;
	}

	public ARequest getRequestById(int roleId) {
		return this.getRequest(roleId);
	}

	private ClubInviteRequest getRequest(int id) {
		ClubInviteRequest request = entitymanager.find(ClubInviteRequest.class,
				id);
		return request;
	}
}
