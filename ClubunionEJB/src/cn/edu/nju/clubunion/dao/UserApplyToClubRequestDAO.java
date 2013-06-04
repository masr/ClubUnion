package cn.edu.nju.clubunion.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Query;

import cn.edu.nju.clubunion.abstractEntity.AJoinClubRequest;
import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.daoRemote.RequestDAORemote;
import cn.edu.nju.clubunion.entity.Club;
import cn.edu.nju.clubunion.entity.JoinClubRequest;
import cn.edu.nju.clubunion.entity.User;


@Stateless

public class UserApplyToClubRequestDAO extends AbstractDAO implements
		RequestDAORemote {

	public void approveRequest(int requestId) {
		JoinClubRequest r = this.getRequest(requestId);
		if (r == null)
			return;
		r.setApproved(true);
		r.setProcessed(true);
		this.mergeRequest(r);

		r.getReceiver().getMembers().add(r.getSender());
		r.getReceiver().getCarers().add(r.getSender());
		r.getSender().getJoinedClubs().add(r.getReceiver());
		r.getSender().getCaredClubs().add(r.getReceiver());
	}

	public void deleteRequest(int requestId) { 
		JoinClubRequest request = this.getRequest(requestId);
		if (request == null)
			return;
		entitymanager.remove(request);

	}

	public void insertRequest(ARequest request) {
		AJoinClubRequest r = (AJoinClubRequest) request;

		JoinClubRequest requestEntity = new JoinClubRequest();
		requestEntity.setDescription(r.getDescription());
		requestEntity.setGrade(r.getGrade());
		requestEntity.setInstitute(r.getInstitute());
		User sender = entitymanager.find(User.class, r.getSenderId());
		Club receiver = entitymanager.find(Club.class, r.getReceiverId());
		requestEntity.setSender(sender);
		requestEntity.setReceiver(receiver);
		entitymanager.persist(requestEntity);
	}

	public boolean isRequestReceiver(int requestId, int roleId) {
		JoinClubRequest request = this.getRequest(requestId);
		User user = entitymanager.find(User.class, roleId);
		if (request == null)
			return false;

		if (request.getReceiver().getManagers().contains(user))
			return true;
		else
			return false;
	}

	private void mergeRequest(JoinClubRequest request) {
		entitymanager.merge(request);

	}

	public void rejectRequest(int requestId) {
		JoinClubRequest request = this.getRequest(requestId);
		if (request == null)
			return;
		request.setProcessed(true);
		request.setApproved(false);
		this.mergeRequest(request);
	}

	@SuppressWarnings("unchecked")
	public List<ARequest> getAllReceivedRequests(int roleId) {
		String jpql = "SELECT r FROM UserApplyToClubRequest r where r.clubId="
				+ roleId;
		Query query = entitymanager.createQuery(jpql);
		List<ARequest> result = query.getResultList();
		return result;
	}

	public ARequest getRequestById(int roleId) {
		return this.getRequest(roleId);
	}

	private JoinClubRequest getRequest(int id) {
		JoinClubRequest request = entitymanager.find(JoinClubRequest.class, id);
		return request;
	}
}
