package cn.edu.nju.clubunion.businessLogicClient;

import java.util.List;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.abstractEntity.AClub;
import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.abstractEntity.AMessage;
import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.exception.ErrorException;

@Remote
public interface IClubService {

	public List<AClub> getAllClubs(IAccountService user) throws ErrorException;// 任何用户可用

	public AClub getClubInfo(IAccountService user, int clubId)
			throws ErrorException;// 任何用户可用

	public void editClubInfo(IAccountService user, int clubId, AClub clubInfo)
			throws ErrorException;// 仅社团管理员可用

	public List<AUser> getManagers(IAccountService user, int clubId)
			throws ErrorException;// 得到社团管理员，依赖于某个特定社团，任何人可用

	public List<AUser> getMembers(IAccountService user, int clubId)
			throws ErrorException;// 得到社团成员,依赖某个特定社团，任何人可用

	public List<AUser> getCarers(IAccountService user, int clubId)
			throws ErrorException;// 得到社团关注者们

	public void deleteClub(IAccountService user, int clubId)
			throws ErrorException;// 删除社团，仅超级管理员可用

	public List<ADocument> getNotices(IAccountService user, int clubId)
			throws ErrorException;// 得到通知，依赖于某个特定社团，任何人可用

	public List<ADocument> getPublicDocuments(IAccountService user, int clubId)
			throws ErrorException;// 得到公开文件，依赖于某个特定社团，任何人可用

	public List<ADocument> getPrivateDocuments(IAccountService user, int clubId)
			throws ErrorException;// 得到私密文件，依赖于某个特定社团，仅社团内部和超级管理员可用

	public List<AMessage> getMessages(IAccountService user, int clubId)
			throws ErrorException;// 得到留言板的留言，依赖于某个特定社团，任何人可用

	public void deleteUserFromClub(IAccountService user, int clubId, int userId)
			throws ErrorException;// 从社团中删除用户，依赖于某个特定社团和用户，仅社团管理员可用

	public void addManager(IAccountService user, int clubId, int userId)
			throws ErrorException;

	public List<ARequest> getUnsolvedReceivedUserApplyRequsets(
			IAccountService user, int clubId) throws ErrorException;// 得到未解决的关于社团的请求，依赖于特定社团和请求类型

	public List<ARequest> getAllReceivedUserApplyRequests(IAccountService user,
			int clubId) throws ErrorException;// 得到所有请求，依赖于特定社团和请求类型

	public void specifyDatabase(String s);

}
