package cn.edu.nju.clubunion.InsertData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import cn.edu.nju.clubunion.abstractEntity.AClub;
import cn.edu.nju.clubunion.abstractEntity.AClubCreateRequest;
import cn.edu.nju.clubunion.abstractEntity.AClubInviteRequest;
import cn.edu.nju.clubunion.abstractEntity.AComment;
import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.abstractEntity.AFriendRequest;
import cn.edu.nju.clubunion.abstractEntity.AJoinClubRequest;
import cn.edu.nju.clubunion.abstractEntity.ALetter;
import cn.edu.nju.clubunion.abstractEntity.AMessage;
import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubService;
import cn.edu.nju.clubunion.businessLogicClient.IDocumentService;
import cn.edu.nju.clubunion.businessLogicClient.ILetterService;
import cn.edu.nju.clubunion.businessLogicClient.IMessageService;
import cn.edu.nju.clubunion.businessLogicClient.IRequestService;
import cn.edu.nju.clubunion.businessLogicClient.ISupService;
import cn.edu.nju.clubunion.businessLogicClient.IUserService;
import cn.edu.nju.clubunion.daoRemote.UserDAORemote;
import cn.edu.nju.clubunion.entity.ClubCreateRequest;
import cn.edu.nju.clubunion.entity.ClubInviteRequest;
import cn.edu.nju.clubunion.entity.Comment;
import cn.edu.nju.clubunion.entity.Document;
import cn.edu.nju.clubunion.entity.FriendRequest;
import cn.edu.nju.clubunion.entity.JoinClubRequest;
import cn.edu.nju.clubunion.entity.Letter;
import cn.edu.nju.clubunion.entity.Message;
import cn.edu.nju.clubunion.entity.User;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.PPW;

public class InsertStaticData implements IInsertStaticData {
	private IAccountService accountService;
	private IUserService userService;
	private IClubService clubService;
	private ILetterService letterService;
	private IDocumentService documentService;
	private IMessageService messageService;
	private IRequestService requestService;

	private ISupService supService;
	private UserDAORemote userDAO;

	private int clubNum;

	public InsertStaticData() throws Exception {

		try {
			Context ctx = new InitialContext();
			accountService = (IAccountService) ctx
					.lookup("AccountService/remote");
			userService = (IUserService) ctx.lookup("UserService/remote");
			clubService = (IClubService) ctx.lookup("ClubService/remote");
			requestService = (IRequestService) ctx
					.lookup("RequestService/remote");
			letterService = (ILetterService) ctx.lookup("LetterService/remote");
			documentService = (IDocumentService) ctx
					.lookup("DocumentService/remote");
			messageService = (IMessageService) ctx
					.lookup("MessageService/remote");

			supService = (ISupService) ctx.lookup("SupService/remote");
		} catch (NamingException e) {
			System.err.print("Service remote error!");
		}
	}

	// 添加用户
	public void InsertUser(AUser u) throws Exception {
		try {
			accountService.regist(accountService, u);
		} catch (ErrorException e) {
			System.err.print(e.getReason());
		}
	}

	// 创建文件
	public void creatDocument(AUser u, String content, String description,
			String tagString, int documentType, String title, int managedClubNum)
			throws ErrorException {
		accountService.login(accountService, u.getEmail(), u.getPassword());

		AClub club = userService.getManagedClubs(accountService,
				accountService.getId()).get(managedClubNum);

		ADocument document = new Document();
		document.setContent(content);
		document.setDescription(description);
		document.setDocumentType(documentType);
		document.setTitle(title);
		document.setUserId(accountService.getId());
		document.setClubId(club.getId());
		document.setActivityData(new Date());
		document.setCreatedAt(new Date());
		documentService.createDocument(accountService, document);
	}

	// 用户对某一个通知发表评论
	public void makeComment(AUser u, int documentNum, String content,
			int documentType) throws ErrorException {
		accountService.login(accountService, u.getEmail(), u.getPassword());
		ADocument d;
		switch (documentType) {
		case 0:
			d = documentService.getAllNotices(accountService).get(
					documentNum);
			break;
		case 1:
			d = documentService.getAllPublicDocuments(accountService).get(
					documentNum);
			break;
		case 2:
			d = documentService.getAllPrivateDocuments(accountService).get(
					documentNum);
			break;
		default:
			d = documentService.getAllNotices(accountService).get(
				documentNum);
			break;
		}
		AComment comment = new Comment();
		comment.setContent(content);
		comment.setDocumentId(d.getId());
		comment.setUserId(accountService.getId());
		comment.setCreatedAt(new Date());
		documentService.makeComment(accountService, comment);
	}

	// 一个用户向一个用户发送站内信
	public void sendLetter(AUser u, int friendsNum, String content, String title)
			throws ErrorException {
		accountService.login(accountService, u.getEmail(), u.getPassword());
		List<AUser> users = userService.getFriends(accountService,
				accountService.getId());
		AUser user = users.get(friendsNum);
		ALetter letter = new Letter();
		letter.setContent(content);
		letter.setTitle(title);
		letter.setReceiverId(user.getId());
		letter.setSenderId(accountService.getId());
		letterService.sendLetter(accountService, letter);
	}

	// 一个用户向某一个社团留言
	public void leaveMessage(AUser u, int clubNum, String content)
			throws ErrorException {
		accountService.login(accountService, u.getEmail(), u.getPassword());
		AClub club = clubService.getAllClubs(accountService).get(clubNum);
		AMessage m = new Message();
		m.setContent(content);
		m.setClubId(club.getId());
		m.setUserId(accountService.getId());
		messageService.leaveMessage(accountService, m);
	}

	// 添加某一个人为管理员
	public void addAdmin() throws Exception {
		Context ctx = new InitialContext();
		userDAO = (UserDAORemote) ctx.lookup("UserDAO/remote");
		List<User> userList = userDAO.getAllUsers();
		User user = userList.get(0);
		int id = user.getId();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.print("类找不到");
		}

		Connection con = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/clubunion_development",
				"maosuhan", "msheric");
		String sql = "insert into supervisors (user_id) values(" + id + ")";
		Statement st = con.createStatement();
		st.execute(sql);

	}// 毛苏晗用户已是究极管理员

	// 发出申请创建一个社团的请求
	public void createClubRequest(AUser u, String clubName, String description,
			int grade, String institute, AUser admin) throws ErrorException {

		accountService.login(accountService, u.getEmail(), u.getPassword());
		AClubCreateRequest info = new ClubCreateRequest();
		info.setClubName(clubName);
		info.setDescription(description);
		info.setGrade("2");
		info.setInstitute(institute);
		info.setSenderId(accountService.getId());
		requestService.setRequestService(PPW.CREATE_CLUB_REQUEST_TYPE);
		requestService.submitRequest(accountService, info);
		accountService.logout(accountService);
		clubNum++;
	}

	// 同意除了最后两个社团外的其他社团的申请请求
	public void passClubRequest(AUser admin) throws ErrorException {
		accountService.login(accountService, admin.getEmail(), admin
				.getPassword());
		List<ARequest> requests = supService
		.getAllClubCreateRequests(accountService);
		requestService.setRequestService(PPW.CREATE_CLUB_REQUEST_TYPE);
		for (int i = 0; i < (clubNum - 2); i++) {
			requestService.approveRequset(accountService, requests.get(i)
					.getId());
			ARequest request = requestService.getRequsetInfo(accountService,
					requests.get(i).getId());
		}
	}

	// 用户发出申请加入社团请求
	public void userApplyToClub(AUser u, String description, int grade,
			String institute, int clubNum) throws ErrorException {
		List<AClub> clubs = clubService.getAllClubs(accountService);
		int clubId = clubs.get(clubNum).getId();

		accountService.login(accountService, u.getEmail(), u.getPassword());
		AJoinClubRequest info = new JoinClubRequest();
		info.setDescription(description);
		info.setGrade(grade);
		info.setInstitute(institute);
		info.setReceiverId(clubId);
		info.setSenderId(accountService.getId());
		requestService.setRequestService(PPW.USER_APPLY_TO_CLUB_REQUEST);
		requestService.submitRequest(accountService, info);
	}

	// 社团管理员同意请求号为偶数加入社团请求
	public void clubApproveUserApplyRequest(AUser u) throws ErrorException {
		accountService.login(accountService, u.getEmail(), u.getPassword());
		List<AClub> clubs = userService.getManagedClubs(accountService,
				accountService.getId());
		for( AClub club:clubs){	
			List<ARequest> requests = clubService.getAllReceivedUserApplyRequests(
					accountService, club.getId());
			int i = 0;
			for (ARequest r : requests) {
				if (i % 2 == 0) {
					requestService
							.setRequestService(PPW.USER_APPLY_TO_CLUB_REQUEST);
					requestService.approveRequset(accountService, r.getId());
				}
				i++;
			}
		}
	}

	// 发出好友申请
	public void userApplyFriendRequest(AUser sender, int receiverID,
			String description) throws ErrorException {

		accountService.login(accountService, sender.getEmail(), sender
				.getPassword());
		AFriendRequest request = new FriendRequest();
		request.setDescription(description);
		request.setReceiverId(receiverID);
		request.setSenderId(accountService.getId());
		requestService.setRequestService(PPW.FRIEND_REQUEST_TYPE);
		requestService.submitRequest(accountService, request);

	}

	// 一个人同意请求号为偶数的好友申请
	public void userApproveFriend(AUser u) throws ErrorException {
		accountService.login(accountService, u.getEmail(), u.getPassword());
		List<ARequest> requests = userService
				.getAllReceivedFriendRequsets(accountService);
		int i = 0;
		for (ARequest r : requests) {
			if (i % 2 == 0) {
				requestService.setRequestService(PPW.FRIEND_REQUEST_TYPE);
				requestService.approveRequset(accountService, r.getId());
			}
			i++;
		}
	}

	// 社团向编号为奇数的用户发出加入社团的请求并同意加入
	public void clubInviteUserRequest(AUser cAdmin, String description)
			throws ErrorException {
		accountService.login(accountService, cAdmin.getEmail(), cAdmin
				.getPassword());
		AClubInviteRequest info = new ClubInviteRequest();
		List<AUser> users = userService.getAllUsers(accountService);
		
		List<AClub> clubs = userService.getManagedClubs(accountService,
				accountService.getId());
		for (AClub club: clubs){
			int i = 0;
			for (AUser u : users) {
				if (i % 2 != 0) {
					u.setId(u.getId());
					info.setDescription(description);
					info.setReceiverId(u.getId());
					info.setSenderId(club.getId());
					requestService.setRequestService(PPW.CLUB_INVITE_USER_REQUEST);
					requestService.submitRequest(accountService, info);
	
	//				accountService.login(accountService, u.getEmail(), u
	//						.getPassword());
	//				List<ARequest> requests = userService
	//						.getAllReceivedClubInviteRequests(accountService);
	//				requestService.setRequestService(PPW.CLUB_INVITE_USER_REQUEST);
	//				requestService.approveRequset(accountService, requests.get(0)
	//						.getId());
				}
				i++;
			}
		}
	}

}
