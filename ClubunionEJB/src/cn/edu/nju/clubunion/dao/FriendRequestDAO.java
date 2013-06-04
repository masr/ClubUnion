package cn.edu.nju.clubunion.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Query;

import cn.edu.nju.clubunion.abstractEntity.AFriendRequest;
import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.daoRemote.RequestDAORemote;
import cn.edu.nju.clubunion.entity.FriendRequest;
import cn.edu.nju.clubunion.entity.User;


@Stateless

public class FriendRequestDAO extends AbstractDAO implements RequestDAORemote {

	public void approveRequest(int requestId) {
		FriendRequest r = this.getRequest(requestId);
		if (r == null)
			return;
		r.setApproved(true);
		r.setProcessed(true);
		this.mergeRequest(r);
		r.getSender().getFriends().add(r.getReceiver());
		r.getReceiver().getFriends().add(r.getSender());
	}

	public void deleteRequest(int requestId) {
		FriendRequest request = this.getRequest(requestId);
		if (request == null) 
			return;
		entitymanager.remove(request);

	}

	public void insertRequest(ARequest request) {
		AFriendRequest r = (AFriendRequest) request;
		FriendRequest requestEntity = new FriendRequest();
		requestEntity.setDescription(r.getDescription());
		User u1 = entitymanager.find(User.class, request.getSenderId());
		User u2 = entitymanager.find(User.class, request.getReceiverId());
		requestEntity.setSender(u1);
		requestEntity.setReceiver(u2);
		entitymanager.persist(requestEntity);

	}

	public boolean isRequestReceiver(int requestId, int roleId) {
		FriendRequest request = this.getRequest(requestId);
		User user = entitymanager.find(User.class, roleId);
		if (request == null)
			return false;

		if (request.getReceiver().equals(user))
			return true;
		else
			return false;
	}

	private void mergeRequest(FriendRequest request) {
		entitymanager.merge(request);
	}

	public void rejectRequest(int requestId) {
		FriendRequest request = this.getRequest(requestId);
		if (request == null)
			return;
		request.setProcessed(true);
		request.setApproved(false);
		this.mergeRequest(request);
	}

	@SuppressWarnings("unchecked")
	public List<ARequest> getAllReceivedRequests(int roleId) {
		String jpql = "SELECT r FROM FriendApplyRequest r ";
		Query query = entitymanager.createQuery(jpql);
		List<ARequest> result = query.getResultList();
		return result;
	}

	public ARequest getRequestById(int roleId) {
		return this.getRequest(roleId);
	}

	private FriendRequest getRequest(int id) {
		FriendRequest request = entitymanager.find(FriendRequest.class, id);
		return request;
	}

}
