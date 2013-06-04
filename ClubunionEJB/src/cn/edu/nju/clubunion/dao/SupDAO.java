package cn.edu.nju.clubunion.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Query;

import cn.edu.nju.clubunion.daoRemote.SupDAORemote;
import cn.edu.nju.clubunion.entity.ClubCreateRequest;


@Stateless

public class SupDAO extends AbstractDAO implements SupDAORemote {

	@SuppressWarnings("unchecked")
	public List<ClubCreateRequest> getAllReceivedClubCreateRequests() {
		Query query = entitymanager
				.createQuery("select r from ClubCreateRequest r ");
		List<ClubCreateRequest> requests = query.getResultList();
		return requests;
	}

}
