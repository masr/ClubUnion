package cn.edu.nju.clubunion.daoRemote;

import java.util.List;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.entity.Club;

@Remote
public interface ClubDAORemote {

	public int insertClub(Club club);

	public void mergeClub(Club club);

	public void deleteClub(int clubid);

	public Club getClubByID(int clubid);

	public void addMember(int userid, int clubid);

	public List getMembers(int clubid);

	public List getManagers(int clubid);

	public List getAllClubs();

	public boolean isMemberInClub(int clubid, int userid);

	public boolean isManagerInClub(int clubid, int userid);

	public void deleteMember(int clubid, int userid);

	public void addManager(int clubid, int userid);

	public void deleteManager(int clubId, int userId);

	public Club getClubByName(String clubname);

	public List getPrivateDocuments(int clubId);

	public List getPublicDocuments(int clubId);

	public List getNotices(int clubId);

	public List getMessages(int clubId);

	public List getAllUserApplyRequests(int clubId);

	public void specifyDatabase(String s);

	public List getCarers(int clubId);


}
