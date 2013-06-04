package cn.edu.nju.clubunion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Query;

import cn.edu.nju.clubunion.daoRemote.ClubDAORemote;
import cn.edu.nju.clubunion.entity.Club;
import cn.edu.nju.clubunion.entity.Document;
import cn.edu.nju.clubunion.entity.JoinClubRequest;
import cn.edu.nju.clubunion.entity.Message;
import cn.edu.nju.clubunion.entity.User;
import cn.edu.nju.clubunion.helper.EJBH;
import cn.edu.nju.clubunion.helper.PPW;


@Stateless

public class ClubDAO extends AbstractDAO implements ClubDAORemote {

	public int insertClub(Club club) {
		entitymanager.persist(club);
          return club.getId();
	}

	public void mergeClub(Club club) {
		entitymanager.merge(club);

	}

	public void deleteClub(int clubid) {
		Club club = entitymanager.find(Club.class, clubid);
		if (club != null)
			entitymanager.remove(club);

	}

	public Club getClubByID(int clubid) {
		return entitymanager.find(Club.class, clubid);

	}

	/**
	 * 只是加关系，没有考虑MANAGER加的问题
	 * 
	 * @param userid
	 * @param clubid
	 */

	public void addMember(int userid, int clubid) {
		User user = entitymanager.find(User.class, userid);
		Club club = entitymanager.find(Club.class, clubid);
		if (user != null && club != null) {
			List<Club> clist = user.getJoinedClubs();
			List<User> ulist = club.getMembers();
			if (!clist.contains(club)) {
				clist.add(club);
			}
			if (!ulist.contains(user)) {
				ulist.add(user);
			}
			user.setJoinedClubs(clist);
			club.setMembers(ulist);

		}
	}

	public void deleteMember(int clubid, int userid) {
		Club club = this.getClubByID(clubid);
		List<User> members = club.getMembers();
		for (User u : members) {
			if (u.getId() == userid) {
				members.remove(u);
				club.setMembers(members);
				return;
			}
		}
	}

	public void addManager(int clubid, int userid) {
		Club club = this.getClubByID(clubid);
		User u = entitymanager.find(User.class, userid);
		if (club == null || u == null)
			return;

		List<User> managers = club.getManagers();
		if (!club.getManagers().contains(u)) {
			managers.add(u);
			club.setManagers(managers);
			this.mergeClub(club);
		}
	}

	@SuppressWarnings("unchecked")
	public Club getClubByName(String clubname) {
		Query query = entitymanager
				.createQuery("select c from Club c where c.name=:name");
		query.setParameter("name", clubname);
		List<Club> cl = query.getResultList();
		if (cl.size() >= 1) {

			return cl.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Club> getAllClubs() {
		Query query = entitymanager.createQuery("select c from Club c ");
		List<Club> clubs = query.getResultList();
		return clubs;

	}

	public List<JoinClubRequest> getAllUserApplyRequests(int clubId) {
		Club club = this.getClubByID(clubId);
		List<JoinClubRequest> r = new ArrayList<JoinClubRequest>();
		if (club == null)
			return r;


		r=EJBH.getListOneByOne(club.getUserApplyRequests());

		return r;
	}

	public List<Message> getMessages(int clubId) {
		Club club = this.getClubByID(clubId);
		List<Message> m = new ArrayList<Message>();
		if (club == null)
			return m;
		
		m=EJBH.getListOneByOne(club.getMessages());
		return m;
	}

	public List<Document> getNotices(int clubId) {
		Club club = this.getClubByID(clubId);
		List<Document> d = new ArrayList<Document>();

		if (club == null)
			return d;
		List<Document> documents = club.getDocuments();
		for (int i = 0; i < documents.size(); i++) {
			if (documents.get(i).getDocumentType() != PPW.NOTICE_TYPE) {
				documents.remove(documents.get(i));
				i--;
			}
		}
		d=EJBH.getListOneByOne(documents);
		return d;
	}

	public List<Document> getPrivateDocuments(int clubId) {
		Club club = this.getClubByID(clubId);
		List<Document> d = new ArrayList<Document>();
		if (club == null)
			return d;
		List<Document> documents = club.getDocuments();
		for (int i = 0; i < documents.size(); i++) {
			if (documents.get(i).getDocumentType() != PPW.PRIVATE_DOCUMENT_TYPE) {
				documents.remove(documents.get(i));
				i--;
			}
		}

		d=EJBH.getListOneByOne(documents);
		return d;

	}

	public List<Document> getPublicDocuments(int clubId) {
		Club club = this.getClubByID(clubId);
		List<Document> d = new ArrayList<Document>();
		if (club == null)
			return d;
		List<Document> documents = club.getDocuments();
		for (int i = 0; i < documents.size(); i++) {
			if (documents.get(i).getDocumentType() != PPW.PUBLIC_DOCUMENT_TYPE) {
				documents.remove(documents.get(i));
				i--;
			}
		}

		d=EJBH.getListOneByOne(documents);
		return d;
	}

	public List<User> getManagers(int clubid) {
		Club club = this.getClubByID(clubid);
		List<User> m = new ArrayList<User>();
		if (club == null)
			return m;


		m=EJBH.getListOneByOne(club.getManagers());
		return m;
	}

	public List<User> getMembers(int clubid) {
		Club club = this.getClubByID(clubid);
		List<User> m = new ArrayList<User>();
		if (club == null)
			return m;


		m=EJBH.getListOneByOne(club.getMembers());
		return m;
	}

	public boolean isManagerInClub(int clubid, int userid) {
		Club club = this.getClubByID(clubid);
		User user = entitymanager.find(User.class, userid);
		if (club == null || user == null)
			return false;

		if (club.getManagers().contains(user))
			return true;
		else
			return false;
	}

	public boolean isMemberInClub(int clubid, int userid) {
		Club club = this.getClubByID(clubid);
		User user = entitymanager.find(User.class, userid);
		if (club == null || user == null)
			return false;
		if (club.getMembers().contains(user))
			return true;
		else
			return false;
	}

	public void deleteManager(int clubId, int userId) {
		Club club = this.getClubByID(clubId);
		List<User> managers = club.getManagers();
		for (User u : managers) {
			if (u.getId() == userId) {
				managers.remove(u);
				club.setMembers(managers);
				return;
			}
		}

	}

	public List<User> getCarers(int clubId) {
		Club club = this.getClubByID(clubId);
		List<User> m = new ArrayList<User>();
		if (club == null)
			return m;

		m=EJBH.getListOneByOne( club.getCarers());

		
		return m;
	}



}
