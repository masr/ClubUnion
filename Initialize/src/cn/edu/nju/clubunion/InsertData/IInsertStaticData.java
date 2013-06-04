package cn.edu.nju.clubunion.InsertData;

import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.exception.ErrorException;

public interface IInsertStaticData {

	// 添加用户
	public void InsertUser(AUser u) throws Exception;

	// 创建文件
	public void creatDocument(AUser u, String content, String description,
			String tagString, int documentType, String title, int managedClubNum)
			throws ErrorException;

	// 用户对某一个文章发表评论
	public void makeComment(AUser u, int documentNum, String content,
			int documentType) throws ErrorException;
	
	// 一个用户向一个用户发送站内信
	public void sendLetter(AUser u, int friendsNum, String content, String title)
			throws ErrorException;

	// 一个用户向某一个社团留言
	public void leaveMessage(AUser u, int clubNum, String content)
			throws ErrorException;

	// 添加某一个人为管理员
	public void addAdmin() throws Exception;

	// 发出申请创建一个社团的请求
	public void createClubRequest(AUser u, String clubName, String description,
			int grade, String institute, AUser admin) throws ErrorException;
	
	// 同意除了最后两个个社团外的其他社团的申请请求
	public void passClubRequest(AUser admin) throws ErrorException;
	
	// 用户发出申请加入社团请求
	public void userApplyToClub(AUser u, String description, int grade,
			String institute, int clubNum) throws ErrorException;

	// 社团管理员同意请求号为偶数加入社团请求
	public void clubApproveUserApplyRequest(AUser u) throws ErrorException;

	// 发出好友申请
	public void userApplyFriendRequest(AUser sender, int receiverID,
			String description) throws ErrorException;

	// 一个人同意请求号为偶数的好友申请
	public void userApproveFriend(AUser u) throws ErrorException;

	// 社团向编号为奇数的用户发出加入社团的请求并同意加入
	public void clubInviteUserRequest(AUser cAdmin, String description)
			throws ErrorException;
}
