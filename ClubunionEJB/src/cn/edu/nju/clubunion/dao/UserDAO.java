package cn.edu.nju.clubunion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.daoRemote.ClubDAORemote;
import cn.edu.nju.clubunion.daoRemote.UserDAORemote;
import cn.edu.nju.clubunion.entity.Club;
import cn.edu.nju.clubunion.entity.ClubInviteRequest;
import cn.edu.nju.clubunion.entity.Document;
import cn.edu.nju.clubunion.entity.FriendRequest;
import cn.edu.nju.clubunion.entity.Letter;
import cn.edu.nju.clubunion.entity.User;
import cn.edu.nju.clubunion.helper.EJBH;


@Stateless

public class UserDAO extends AbstractDAO implements UserDAORemote {

	
	@EJB(beanName = "ClubDAO")
	private ClubDAORemote clubDAO;

	public int insertUser(User user) {
		entitymanager.persist(user);
		return user.getId();
	}

	public void addAdmin(int userId) {
		String sql = "insert into supervisors(user_id) values(:id)";
		Query query = entitymanager.createNativeQuery(sql);
		query.setParameter("id", userId);
		query.executeUpdate();
	}

	public void mergeUser(User user) {
		entitymanager.merge(user);
	}

	public void deleteUser(int userid) {
		User user = entitymanager.find(User.class, userid);
		if (user != null)
			entitymanager.remove(user);
	}

	public User getUserByID(int userid) {
		return entitymanager.find(User.class, userid);

	}

	public void refreshUser(User user) {
		entitymanager.refresh(user);
	}

	public boolean checkEmail(String email) {
		Query query = entitymanager
				.createQuery("select c from User c where c.email=:email");
		query.setParameter("email", email);
		return (query.getResultList().size() > 0);
	}

	@SuppressWarnings("unchecked")
	public User getUserByEmailAndPassword(String email, String password) {

		String jpql = "SELECT c FROM User c WHERE c.email=:email AND c.password=:password";
		Query query = entitymanager.createQuery(jpql);
		query.setParameter("email", email);
		query.setParameter("password", password);
		List<User> result = query.getResultList();
		if (result.size() >= 1) {

			return result.get(0);
		}
		return null;
	}

	public boolean isFriend(int uid, int fid) {
		User u = this.getUserByID(uid);
		User f = this.getUserByID(fid);

		if (u != null && f != null) {
			if (u.getFriends().contains(f))
				return true;

		}

		return false;
	}

	public void deleteFriend(int uid, int fid) {

		User u = this.getUserByID(uid);
		User f = this.getUserByID(fid);
		if (u != null && f != null) {
			List<User> ulist = u.getFriends();
			List<User> flist = f.getFriends();
			if (ulist.contains(f)) {
				ulist.remove(f);
			}
			if (flist.contains(u)) {
				flist.remove(u);
			}
			u.setFriends(ulist);
			f.setFriends(flist);
		}

	}

	public void addFriend(int uid, int fid) {
		User u = this.getUserByID(uid);
		User f = this.getUserByID(fid);
		if (u != null && f != null) {
			List<User> ulist = u.getFriends();
			List<User> flist = f.getFriends();
			if (!ulist.contains(f)) {
				ulist.add(f);
			}
			if (!flist.contains(u)) {
				flist.add(u);
			}
			u.setFriends(ulist);
			f.setFriends(flist);
		}
	}

	public List<ClubInviteRequest> getAllClubInviteUserRequests(int userId) {
		User u = this.getUserByID(userId);
		if (u == null)
			return null;
		return EJBH.getListOneByOne( u.getClubInviteRequests());
	
	}

	public List<FriendRequest> getAllReceivedFriendRequests(int userId) {
		User u = this.getUserByID(userId);
		if (u == null)
			return null;
		return EJBH.getListOneByOne( u.getReceivedFriendsRequests());
	

	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		String jpql = "SELECT c FROM User c";
		Query query = entitymanager.createQuery(jpql);
		List<User> result = query.getResultList();
		return result;
	}

	public List<Integer> getCaredClubIds(int userid) {
		User u = getUserByID(userid);
		List<Integer> cidl = new ArrayList<Integer>();

		if (u == null)
			return cidl;
		
		List<Club> cl = u.getJoinedClubs();
		for (Club c : cl) {
			cidl.add(c.getId());
		}
		return cidl;
	}

	public List<Club> getCaredClubs(int userId) {
		User u = this.getUserByID(userId);
		List<Club> clubs=new ArrayList<Club>();
		if (u == null)
			return clubs;
		clubs=EJBH.getListOneByOne( u.getCaredClubs());
		return clubs;
		
	}
 
	public List<Document> getDocuments(int userId) { 
		User u = this.getUserByID(userId);
		List<Document> documents=new ArrayList<Document>();
		if (u == null)
			return documents;
		documents=EJBH.getListOneByOne( u.getDocuments());
		return documents;
	
	}

	public List<User> getFriends(int userid) {
		User u = this.getUserByID(userid);
		List<User> users=new ArrayList<User>();
		if (u == null)
			return users;
		 users=EJBH.getListOneByOne( u.getFriends());
		 return users;
	}

	public List<Integer> getJoinedClubIds(int userid) {
		User u = getUserByID(userid);
		List<Integer> cidl = new ArrayList<Integer>();

		if (u == null)
			return cidl;
		List<Club> cl = u.getJoinedClubs();
		for (Club c : cl) {
			cidl.add(c.getId());
		}
		return cidl;
	}

	public List<Club> getJoinedClubs(int userId) {
		User u = this.getUserByID(userId);
		List<Club> clubs=new ArrayList<Club>();

		if (u == null)
			return clubs;
		clubs= EJBH.getListOneByOne( u.getJoinedClubs());
	    return clubs;
	}

	public List<Integer> getManagedClubIds(int userid) {
		User u = getUserByID(userid);
		List<Integer> cidl = new ArrayList<Integer>();

		if (u == null)
			return cidl;

		List<Club> cl =u.getManagedClubs();
	
		
		for (Club c : cl) {
			cidl.add(c.getId());
		}
		return cidl;
	}

	public List<Club> getManagedClubs(int userId) {
		User u = this.getUserByID(userId);
		List<Club> clubs=new ArrayList<Club>();
		if (u == null)
			return clubs;
		clubs=EJBH.getListOneByOne( u.getManagedClubs());
		return clubs;
	
	}

	public List<Club> getNormalClubs(int userId) {
		List<Club> allClubs = clubDAO.getAllClubs();
		List<Integer> managedIds = this.getManagedClubIds(userId);
		List<Integer> joinedIds = this.getJoinedClubIds(userId);
		List<Integer> caredIds = this.getCaredClubIds(userId);

		for (int i = 0; i < allClubs.size(); i++) {
			Club c = allClubs.get(i);
			boolean canBreak = false;
			for	(int x = 0;x<managedIds.size();x++){
				Integer id1=managedIds.get(x);
				if (c.getId() == id1) {
					allClubs.remove(c);
					i--;
					canBreak = true;
					break;
				}

			}

			if (canBreak)
				continue;
			for	(int x = 0;x<joinedIds.size();x++){
				Integer id2=joinedIds.get(x);
				if (c.getId() == id2) {
					allClubs.remove(c);
					i--;
					canBreak = true;
					break;
				}
			}
			if (canBreak)
				continue;
			for	(int x = 0;x<caredIds.size();x++){
				Integer id3=caredIds.get(x);
				if (c.getId() == id3) {
					allClubs.remove(c);
					i--;
					break;
				}
			}
		}

		
		return EJBH.getListOneByOne(allClubs);
	}

	public List<Letter> getReceivedLetters(int userId) {
		User u = this.getUserByID(userId);
		List<Letter> letters=new ArrayList<Letter>();
		if (u == null)
			return letters;
		letters=EJBH.getListOneByOne( u.getReceivedLetters());
	    return letters;
	}

	public List<Letter> getSentLetters(int userId) {
		User u = this.getUserByID(userId);
		List<Letter> letters=new ArrayList<Letter>();

		if (u == null)
			return letters;
		letters=EJBH.getListOneByOne( u.getSendedLetters());
	    return letters;
	
		
	}

	@SuppressWarnings("unchecked")
	public boolean isAdmin(int userId) {
		String sql = "SELECT * FROM supervisors where user_id=" + userId;
		Query query = entitymanager.createNativeQuery(sql);
		List<ARequest> result = query.getResultList();
		if (result.size() > 0)
			return true;
		else
			return false;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsersByNickName(String name) {
		String jpql = "select u from User u where u.nickName=:name";
		Query query = entitymanager.createQuery(jpql);
		query.setParameter("name", name);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsersByRealName(String name) {
		String jpql = "select u from User u where u.realName=:name";
		Query query = entitymanager.createQuery(jpql);
		query.setParameter("name", name);
		return query.getResultList();
	}

	public User findUserByEmail(String email) {
		String jpql = "select u from User u where u.email=:email";
		Query query = entitymanager.createQuery(jpql);
		query.setParameter("email", email);
		List<User> user = query.getResultList();
		if (user.size() == 0)
			return null;
		else
			return user.get(0);

	}

}
