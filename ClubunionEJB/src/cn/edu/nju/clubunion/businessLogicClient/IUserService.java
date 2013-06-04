package cn.edu.nju.clubunion.businessLogicClient;

import java.util.List;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.abstractEntity.AClub;
import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.abstractEntity.ALetter;
import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.exception.ErrorException;

@Remote
public interface IUserService {

	public AUser getPersonalInfo(IAccountService user, int userId)
			throws ErrorException; // 任何用户可用

	public void changePassword(IAccountService user, String oldPassword,
			String newPassword) throws ErrorException;// 更改密码，唯有本用户可用

	public void editInfo(IAccountService user, AUser userInfo)
			throws ErrorException;// 修改信息，唯有本用户可用

	public List<AClub> getManagedClubs(IAccountService user, int userId)
			throws ErrorException;// 得到掌管社团,任何人可用，依赖于某个特定用户

	public List<AClub> getJoinedClubs(IAccountService user, int userId)
			throws ErrorException;// 得到参加社团，任何人可用，依赖于某个特定用户

	public List<AClub> getCaredClubs(IAccountService user, int userId)
			throws ErrorException;// 得到关注社团，任何人可用，依赖于某个特定用户

	public List<AClub> getNormalClubs(IAccountService user, int userId)
			throws ErrorException;// 得到其他社团，登录用户可用，依赖于某个特定用户

	public List<ADocument> getNotices(IAccountService user, int userId)
			throws ErrorException;// 得到该用户发表的通知，任何人可用

	public List<ADocument> getPublicDocuments(IAccountService user, int userId)
			throws ErrorException;// 得到该用户发表的公开文章，任何人可用

	public List<ADocument> getPrivateDocuments(IAccountService user, int userId)
			throws ErrorException;// 得到私密文件，需要严格验证

	public List<ARequest> getUnsolvedReceivedFriendRequsets(IAccountService user)
			throws ErrorException;// 得到未解决的关于用户的请求，依赖于特定用户和请求类型

	public List<ARequest> getAllReceivedFriendRequsets(IAccountService user)
			throws ErrorException;// 得到未解决的关于用户的请求，依赖于特定用户和请求类型

	public List<ARequest> getUnsolvedReceivedClubInviteRequests(
			IAccountService user) throws ErrorException;// 得到未解决的关于用户的请求，依赖于特定用户和请求类型

	public List<ARequest> getAllReceivedClubInviteRequests(IAccountService user)
			throws ErrorException;// 得到未解决的关于用户的请求，依赖于特定用户和请求类型

	public List<AUser> getFriends(IAccountService user, int userId)
			throws ErrorException;// 得到好友列表，仅适用于登录用户

	public void deleteUser(IAccountService user, int userId)
			throws ErrorException;// 删除用户，仅超级管理员可用

	public List<ALetter> getSentLetters(IAccountService user)
			throws ErrorException; // 得到发件箱列表，登录用户可用，依赖于某个特定用户

	public List<ALetter> getReceivedLetters(IAccountService user)
			throws ErrorException;// 得到收件箱列表，登录用户可用，依赖于某个特定用户

	public void deleteFriend(IAccountService user, int friendId)
			throws ErrorException;// 删除好友，账户可用

	public List<AUser> getAllUsers(IAccountService user) throws ErrorException;// 查看所有用户列表，任何用户均可用

	public void specifyDatabase(String s);

}
