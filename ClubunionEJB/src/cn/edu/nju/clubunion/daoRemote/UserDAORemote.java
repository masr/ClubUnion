package cn.edu.nju.clubunion.daoRemote;

import java.util.List;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.entity.User;

@Remote
public interface UserDAORemote {

	public void addAdmin(int userId);// t

	public int insertUser(User user);// t

	public void mergeUser(User user);

	public void deleteUser(int userid);// t

	public User getUserByID(int userid);

	public boolean checkEmail(String email);// t

	public void refreshUser(User user);

	public User getUserByEmailAndPassword(String email, String password);// t

	public boolean isFriend(int uid, int fid);

	public User findUserByEmail(String eamil);

	public void deleteFriend(int uid, int fid);

	public void addFriend(int uid, int fid);

	public List getFriends(int userid);

	public List<Integer> getManagedClubIds(int userid);

	public List<Integer> getJoinedClubIds(int userid);

	public List<Integer> getCaredClubIds(int userid);

	public List getManagedClubs(int userId);

	public List getJoinedClubs(int userId);

	public List getCaredClubs(int userId);

	public List getNormalClubs(int userId);

	public List getDocuments(int userId);

	public List getAllUsers();// t

	public List getAllClubInviteUserRequests(int userId);

	public List getAllReceivedFriendRequests(int userId);

	public List getReceivedLetters(int userId);

	public List getSentLetters(int userId);

	public boolean isAdmin(int userId);// t

	public List getUsersByNickName(String name);// t

	public List getUsersByRealName(String name);// t

	public void specifyDatabase(String s);

}
