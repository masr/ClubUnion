package cn.edu.nju.clubunion.testEJB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import cn.edu.nju.clubunion.abstractEntity.AClub;
import cn.edu.nju.clubunion.abstractEntity.AClubCreateRequest;
import cn.edu.nju.clubunion.abstractEntity.AClubInviteRequest;
import cn.edu.nju.clubunion.abstractEntity.AFriendRequest;
import cn.edu.nju.clubunion.abstractEntity.AJoinClubRequest;
import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubService;
import cn.edu.nju.clubunion.businessLogicClient.IRequestService;
import cn.edu.nju.clubunion.businessLogicClient.ISupService;
import cn.edu.nju.clubunion.businessLogicClient.IUserService;
import cn.edu.nju.clubunion.daoRemote.UserDAORemote;
import cn.edu.nju.clubunion.entity.ClubCreateRequest;
import cn.edu.nju.clubunion.entity.ClubInviteRequest;
import cn.edu.nju.clubunion.entity.FriendRequest;
import cn.edu.nju.clubunion.entity.JoinClubRequest;
import cn.edu.nju.clubunion.entity.User;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.PPW;

public class InitialAssociationServiceTest extends TestCase{
	private IAccountService accountService;
	private IUserService userService;
	private IClubService clubService;
	private IRequestService requestService;

	private ISupService supService;
	

	private AUser msh;
	private AUser jxl;
	private AUser gs;
	private AUser lcc;
	private AUser gzy;
	private AUser lh;
    
	
	public InitialAssociationServiceTest(String name)
	{
		super(name);
	}
	
	public void setUp() throws Exception
	{
	super.setUp();
	Context ctx=new InitialContext();
	accountService =(IAccountService) ctx.lookup("AccountService/remote");
	userService=(IUserService)ctx.lookup("UserService/remote");
	clubService=(IClubService)ctx.lookup("ClubService/remote");
	requestService=(IRequestService)ctx.lookup("RequestService/remote");

	supService=(ISupService)ctx.lookup("SupService/remote");
        this.specifyDatabse("test");

	msh=new User();
	msh.setDescription("大家好，我是万人迷");
	msh.setEmail("maosuhan@yahoo.com.cn");
	msh.setPassword("msheric");
	msh.setNickName("风动我不动");
	msh.setRealName("毛苏晗");
	
	jxl=new User();
	jxl.setDescription("大家好，我是傻x");
	jxl.setEmail("jinxiaolong@shax.com.cn");
	jxl.setPassword("shax");
	jxl.setNickName("傻叉");
	jxl.setRealName("金晓龙");
	
	gs=new User();
	gs.setDescription("大家好，我是谁");
	gs.setEmail("gs@yahoo.com.cn");
	gs.setPassword("123"); 
	gs.setNickName("BeanMan");
	gs.setRealName("高晟");
	
	lcc=new User();
	lcc.setDescription("我是小春子");
	lcc.setEmail("lcc@qq.com");
	lcc.setNickName("小春子");
	lcc.setPassword("lcclcc");
	lcc.setRealName("刘春晨");
	
	gzy=new User();
	gzy.setDescription("大家好，我是小飞人");
	gzy.setEmail("gzy@qq.com");
	gzy.setNickName("飞人");
	gzy.setRealName("高哲远");
	gzy.setPassword("gzy");
	
    lh=new User();
    lh.setDescription("我是李辉");
    lh.setEmail("lh@qq.com");
    lh.setNickName("逍遥me");
    lh.setRealName("李辉");
    lh.setPassword("222");
	}
	
	private void specifyDatabse(String s)
	{
		accountService.specifyDatabase(s);
		userService.specifyDatabase(s);
		clubService.specifyDatabase(s);
		requestService.specifyDatabase(s);

		supService.specifyDatabase(s);
	}
	
	
	
	public void tearDown() throws Exception{
		super.tearDown();
		
		msh=null;
		gs=null;
		jxl=null;
		accountService=null;
	    
	}
	
	
	//测试是否可以注册
	public void testRegist() throws ErrorException
	{

			accountService.regist(accountService,msh);
			accountService.regist(accountService,gs);
			accountService.regist(accountService,jxl);
			accountService.regist(accountService,lcc);	
			accountService.regist(accountService,gzy);
	}
	
	//测试数据库添加某一个人为管理员
	public void testAddMaoSuhanToAdmin() throws Exception
	{
	
		List<AUser> userList=userService.getAllUsers(accountService);
		AUser user=userList.get(0);
		int id=user.getId();
		
		 try{
			  Class.forName("com.mysql.jdbc.Driver");
		  }
		  catch(ClassNotFoundException e)
		  {
			  System.out.print("类找不到");
		  }
		
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/clubunion_test","maosuhan","msheric");
		     String sql="insert into supervisors (user_id) values("+id+")";
			Statement st=con.createStatement();
			st.execute(sql);
		
	}//毛苏晗用户已是究极管理员
	
	
	
	
	//测试是否可以成功登陆
	public void testLogin() throws ErrorException
	{
		accountService.login(accountService,msh.getEmail(), msh.getPassword());
	   assertNotSame(-1, accountService.getId());//断言id
	
	}
	
	//测试AccountService中的isAdmin 方法
	public void testIsAdmin() throws ErrorException{
		accountService.login(accountService,msh.getEmail(), msh.getPassword());
		assertTrue(accountService.isAdmin());//断言是管理员
	}
	
	//测试发出申请创建一个社团的请求
	public void testCreateClubRequest() throws ErrorException{
		
		accountService.login(accountService,gs.getEmail(), gs.getPassword());
		AClubCreateRequest info=new ClubCreateRequest();
		info.setClubName("死亡诗社");
		info.setDescription("你不批准我就砍了你");
		info.setGrade(2);
		info.setInstitute("软院");
		info.setSenderId(accountService.getId());
		requestService.setRequestService(PPW.CREATE_CLUB_REQUEST_TYPE);
		requestService.submitRequest(accountService, info);

		
		
		
		accountService.login(accountService,jxl.getEmail(), jxl.getPassword());
		 info=new ClubCreateRequest();
			info.setClubName("傻x诗社");
			info.setDescription("你不批准我就x了你");
			info.setGrade(2);
			info.setInstitute("软件学院");
			info.setSenderId(accountService.getId());
				requestService.setRequestService(PPW.CREATE_CLUB_REQUEST_TYPE);
				requestService.submitRequest(accountService, info);
	}//高晟，金晓龙各申请了一个社团
	

	
	//测试管理员同意请求
	public void testApproveCreateClubRequest() throws ErrorException{
		accountService.login(accountService,msh.getEmail(), msh.getPassword());
		
        List<ARequest> requests=supService.getAllClubCreateRequests(accountService);
        assertEquals(2,requests.size());//断言有两个申请
        List<ARequest> requests2=supService.getUnsolvedClubCreateRequests(accountService);
        assertEquals(2,requests2.size());//断言有两条未处理
        
        for(int i=0;i<requests.size();i++)
        {
        	assertTrue(requests.get(i).getSenderName()!=null);//断言存入了以下信息
            assertTrue(((AClubCreateRequest)requests.get(i)).getGrade()!=null);
            assertTrue(((AClubCreateRequest)requests.get(i)).getInstitute()!=null);

        if (requests.get(i).getSenderName().equals("高晟"))
        {
        	requestService.setRequestService(PPW.CREATE_CLUB_REQUEST_TYPE);
        	requestService.approveRequset(accountService, requests.get(i).getId());
        	ARequest request=requestService.getRequsetInfo(accountService, requests.get(i).getId());
        	System.out.println(request.isApproved());
        	assertTrue(request.isApproved());//断言被批准
        	assertTrue(request.isProcessed());//断言已处理
        	assertTrue(supService.getUnsolvedClubCreateRequests(accountService).size()==1);//断言只剩下一个未处理请求
        }

        }
        
      
	}//毛苏晗接收高晟，死亡诗社成立，高晟成为了死亡诗社的管理员
	
	//测试管理员拒绝请求
	public void testRejectCreateClubRequest() throws ErrorException{
		
		accountService.login(accountService,msh.getEmail(), msh.getPassword());
		 List<ARequest> requests=supService.getAllClubCreateRequests(accountService);
	        assertTrue(requests.size()==2);//断言有两个申请
	        List<ARequest> requests2=supService.getUnsolvedClubCreateRequests(accountService);
	        assertTrue(requests2.size()==1);//断言有一条未处理
		  for(ARequest r:requests)
	        {
	        
	        if (r.getSenderName().equals("金晓龙"))
	        {
	        	requestService.rejectRequset(accountService, r.getId());
	        	ARequest request=requestService.getRequsetInfo(accountService, r.getId());
	        	assertFalse(request.isApproved());//断言被拒绝
	        	assertTrue(request.isProcessed());//断言已处理

	        }
	        }
	}//毛苏晗拒绝了金晓龙
	
	
	//测试一系列关系
	public void testClubManagerAssociation() throws ErrorException{
		accountService.login(accountService,gs.getEmail(), gs.getPassword());
		  List<AClub> clubs=clubService.getAllClubs(accountService);
	        assertTrue(clubs.size()==1);//断言存在一个社团
	        assertTrue(clubs.get(0).getName().equals("死亡诗社"));//断言叫死亡诗社
	        List<AUser> managers=clubService.getManagers(accountService, clubs.get(0).getId());
	        assertTrue(managers.size()==1);//断言有一个管理员
	        assertTrue(managers.get(0).getNickName().equals("BeanMan"));//断言叫BeanMan
	        
	        List<AUser> members=clubService.getMembers(accountService, clubs.get(0).getId());
	        
	        assertTrue(members.size()==1);//断言有一个成员
	        assertTrue(members.get(0).getNickName().equals("BeanMan"));//断言叫BeanMan
	        
	        List<AUser> carers=clubService.getCarers(accountService, clubs.get(0).getId());
	        assertTrue(carers.size()==1);//断言有一个关注者
	        assertTrue(carers.get(0).getNickName().equals("BeanMan"));//断言叫BeanMan
	        
	        clubs=userService.getManagedClubs(accountService, accountService.getId());
	        assertTrue(clubs.size()==1);
	        clubs=userService.getJoinedClubs(accountService, accountService.getId());
	        assertTrue(clubs.size()==1);
	        clubs=userService.getCaredClubs(accountService, accountService.getId());
	        assertTrue(clubs.size()==1);
	        clubs=userService.getNormalClubs(accountService, accountService.getId());
	        assertEquals(0,clubs.size());

	}//验证外键关系
	
	
	//测试用户发出申请加入社团请求
	public void testUserApplyToClub() throws ErrorException
	{
		List<AClub> clubs=clubService.getAllClubs(accountService);
		int clubId=clubs.get(0).getId();
		
		accountService.login(accountService,jxl.getEmail(), jxl.getPassword());
		AJoinClubRequest info=new JoinClubRequest();
		info.setDescription("加个我");
		info.setGrade(2);
		info.setInstitute("软件学院");
		info.setReceiverId(clubId);
		info.setSenderId(accountService.getId());
		requestService.setRequestService(PPW.USER_APPLY_TO_CLUB_REQUEST);
		requestService.submitRequest(accountService, info);
		
		accountService.login(accountService,lcc.getEmail(), lcc.getPassword());
		info=new JoinClubRequest();
		info.setDescription("加个我");
		info.setGrade(2);
		info.setReceiverId(clubId);
		info.setInstitute("软件学院");
		info.setSenderId(accountService.getId());
		
		requestService.setRequestService(PPW.USER_APPLY_TO_CLUB_REQUEST);
		requestService.submitRequest(accountService, info);
	}//金晓龙、刘春晨申请加入死亡诗社
	
	
	

	//测试社团管理员同意加入社团请求
	public void testClubApproveUserApplyRequest() throws ErrorException{
		accountService.login(accountService,gs.getEmail(), gs.getPassword());
		List<AClub> clubs=userService.getManagedClubs(accountService, accountService.getId());
		AClub club=clubs.get(0);
       List<ARequest> requests=clubService.getAllReceivedUserApplyRequests(accountService, club.getId());
	  assertTrue(requests.size()==2);//断言有两个请求
	  List<ARequest> requests2=clubService.getUnsolvedReceivedUserApplyRequsets(accountService, club.getId());
	  assertTrue(requests2.size()==2);//断言有两个请求

	  for(ARequest r:requests)
	  {
		  if (((AJoinClubRequest)r).getSenderName().equals(jxl.getRealName()))
			  {
			  requestService.setRequestService(PPW.USER_APPLY_TO_CLUB_REQUEST);
		      requestService.approveRequset(accountService, r.getId());
		  }
	  }
	  
	
	}//高晟接收金晓龙，金晓龙成为死亡诗社会员
	
	
	//测试社团管理员拒绝加入社团请求
	public void testClubRejectUserApplyRequest() throws ErrorException{
		accountService.login(accountService,gs.getEmail(), gs.getPassword());
		List<AClub> clubs=userService.getManagedClubs(accountService, accountService.getId());
		AClub club=clubs.get(0);
       List<ARequest> requests=clubService.getAllReceivedUserApplyRequests(accountService, club.getId());
	  assertTrue(requests.size()==2);//断言有两个请求
	  List<ARequest> requests2=clubService.getUnsolvedReceivedUserApplyRequsets(accountService, club.getId());
	  assertTrue(requests2.size()==1);//断言有一个未处理请求

	  for(ARequest r:requests)
	  {
		  if (((AJoinClubRequest)r).getSenderName().equals(lcc.getRealName()))
			  {
			  requestService.setRequestService(PPW.USER_APPLY_TO_CLUB_REQUEST);
		      requestService.rejectRequset(accountService, r.getId());
		  }
	  }
	}//高晟拒绝刘春晨
	
	
	//测试关系
	public void testClubMemberAssociation() throws ErrorException{
		accountService.login(accountService,jxl.getEmail(), jxl.getPassword());
		List<AClub> clubs=userService.getManagedClubs(accountService, accountService.getId());
		assertTrue(clubs==null|clubs.size()==0);
		clubs=userService.getJoinedClubs(accountService, accountService.getId());
		assertTrue(clubs.size()==1);
		clubs=userService.getCaredClubs(accountService, accountService.getId());
		assertTrue(clubs.size()==1);
		
		int clubId=clubs.get(0).getId();
        
		List<AUser> users=clubService.getManagers(accountService, clubId);
		assertTrue(users.size()==1);
		users=clubService.getMembers(accountService, clubId);
		assertTrue(users.size()==2);
	}//测试组织成员外键
	
	
	//测试发出好友申请
	public void testUserApplyFriendRequest() throws ErrorException{
		accountService.login(accountService,gs.getEmail(), gs.getPassword());
		int gsId=accountService.getId();
		accountService.login(accountService,jxl.getEmail(), jxl.getPassword());
         AFriendRequest request=new FriendRequest();
         request.setDescription("你好，我是晓龙金");
         request.setReceiverId(gsId);
         request.setSenderId(accountService.getId());
         requestService.setRequestService(PPW.FRIEND_REQUEST_TYPE);
         requestService.submitRequest(accountService, request);
         
     	accountService.login(accountService,lcc.getEmail(), lcc.getPassword());
        request=new FriendRequest();
        request.setDescription("你好，我是晓龙金");
        request.setReceiverId(gsId);
        request.setSenderId(accountService.getId());
        requestService.setRequestService(PPW.FRIEND_REQUEST_TYPE);
        requestService.submitRequest(accountService, request);
        
         }//金晓龙与刘春晨向高晟提交好友申请
	
	
	//测试用户拒绝一个人的好友申请
	public void testUserRejectFriend() throws ErrorException{
		accountService.login(accountService,gs.getEmail(), gs.getPassword());
       List<ARequest> requests=userService.getAllReceivedFriendRequsets(accountService);
       List<ARequest> requests2=userService.getUnsolvedReceivedFriendRequsets(accountService);
	  assertEquals(2,requests.size());//断言有两个请求
	  assertEquals(2,requests2.size());//断言有两个未处理请求

	  for(ARequest r:requests)
	  {
		  if (((AFriendRequest)r).getSenderName().equals(jxl.getRealName()))
			  {
			  requestService.setRequestService(PPW.FRIEND_REQUEST_TYPE);
		      requestService.rejectRequset(accountService, r.getId());
		  }
	  }
	}//高晟拒绝金晓龙
	
	
	//测试一个人同意一个人的好友申请
public void testUserApproveFriend() throws ErrorException{
	accountService.login(accountService,gs.getEmail(), gs.getPassword());
    List<ARequest> requests=userService.getAllReceivedFriendRequsets(accountService);
    List<ARequest> requests2=userService.getUnsolvedReceivedFriendRequsets(accountService);
	  assertTrue(requests.size()==2);//断言有两个请求
	  assertTrue(requests2.size()==1);//断言有一个未处理请求

	  for(ARequest r:requests)
	  {
		  if (((AFriendRequest)r).getSenderName().equals(lcc.getRealName()))
			  {
			  requestService.setRequestService(PPW.FRIEND_REQUEST_TYPE);
		      requestService.approveRequset(accountService, r.getId());
		  }
	  }
	}//高晟接收刘春晨，高晟与刘春晨成为了好友


//测试好友关系
public void testFriendAssociation() throws ErrorException{
	accountService.login(accountService,gs.getEmail(), gs.getPassword());
    List<AUser> users=userService.getFriends(accountService, accountService.getId());
    assertEquals(1,users.size());
    assertTrue(users.get(0).getRealName().equals(lcc.getRealName()));
    
	accountService.login(accountService,lcc.getEmail(), lcc.getPassword());
    users=userService.getFriends(accountService, accountService.getId());
    assertTrue(users.size()==1);
    assertTrue(users.get(0).getRealName().equals(gs.getRealName()));
    
    
}//检测好友外键


//测试社团向用户发出加入申请的请求
 public void testClubInviteUserRequest() throws ErrorException{
	 accountService.login(accountService,gs.getEmail(), gs.getPassword());
	 AClub club=userService.getManagedClubs(accountService, accountService.getId()).get(0);
	 AClubInviteRequest info=new ClubInviteRequest();
	 List<AUser> users=userService.getAllUsers(accountService);
	 for(AUser u:users)
	 {
		 if (u.getRealName().equals(gzy.getRealName()))
			 gzy.setId(u.getId());
		 if (u.getRealName().equals(msh.getRealName()))
			 msh.setId(u.getId());
	 }
	 info.setDescription("加入我们吧");
	 info.setReceiverId(gzy.getId());
	 info.setSenderId(club.getId());
	 requestService.setRequestService(PPW.CLUB_INVITE_USER_REQUEST);
	 requestService.submitRequest(accountService, info);
	 
	 info=new ClubInviteRequest();
	 info.setDescription("加入我们吧");
	 info.setReceiverId(msh.getId());
	 info.setSenderId(club.getId());
	 requestService.setRequestService(PPW.CLUB_INVITE_USER_REQUEST);
	 requestService.submitRequest(accountService, info);
 }//高晟向毛苏晗和高哲远发出加入社团的邀请
 
 
 //测试用户拒绝社团的邀请
 public void testRejectClubInvite() throws ErrorException{
	 accountService.login(accountService,msh.getEmail(), msh.getPassword());
	 List<ARequest> requests=userService.getAllReceivedClubInviteRequests(accountService);
	 assertEquals(1,requests.size());
	 assertEquals(PPW.CLUB_INVITE_USER_REQUEST,(int)requests.get(0).getRequestType());
	 requestService.setRequestService(PPW.CLUB_INVITE_USER_REQUEST);
	 requestService.rejectRequset(accountService, requests.get(0).getId());
 }//毛苏晗拒绝了高晟邀请加入社团的邀请
 
 public void testApproveClubInvite() throws ErrorException{
	 
	 accountService.login(accountService,gzy.getEmail(), gzy.getPassword());
	 List<ARequest> requests=userService.getAllReceivedClubInviteRequests(accountService);
	 assertEquals(1,requests.size());
	 assertEquals(PPW.CLUB_INVITE_USER_REQUEST,requests.get(0).getRequestType());
	 requestService.setRequestService(PPW.CLUB_INVITE_USER_REQUEST);
	 requestService.approveRequset(accountService, requests.get(0).getId());
     
 }//高哲远同意了高晟邀请加入社团的邀请
 
 
 //测试关系
 public void testClubMemberAssociationAfterInvite() throws ErrorException{
		accountService.login(accountService,gzy.getEmail(), gzy.getPassword());
		List<AClub> clubs=userService.getManagedClubs(accountService, accountService.getId());
		assertTrue(clubs==null|clubs.size()==0);
		clubs=userService.getJoinedClubs(accountService, accountService.getId());
		assertTrue(clubs.size()==1);
		clubs=userService.getCaredClubs(accountService, accountService.getId());
		assertTrue(clubs.size()==1);
		
		int clubId=clubs.get(0).getId();
        
		List<AUser> users=clubService.getManagers(accountService, clubId);
		assertTrue(users.size()==1);
		users=clubService.getMembers(accountService, clubId);
		assertTrue(users.size()==3);
		users=clubService.getCarers(accountService, clubId);
		assertTrue(users.size()==3);
 }//测试外键关系


	
	public void testLogout(){
		try {
	
		accountService.logout(accountService);
		} catch (ErrorException e) {
			e.printStackTrace();
		}
		assertEquals(true, accountService==null);
	}
	
	
	
	public static Test suite() {
		TestSuite suite=new TestSuite();
		suite.addTest(new InitialAssociationServiceTest ("testRegist"));
		suite.addTest(new InitialAssociationServiceTest ("testAddMaoSuhanToAdmin")); 
		suite.addTest(new InitialAssociationServiceTest ("testLogin"));
		suite.addTest(new InitialAssociationServiceTest ("testIsAdmin")); 
		suite.addTest(new InitialAssociationServiceTest ("testCreateClubRequest")); 
		suite.addTest(new InitialAssociationServiceTest ("testApproveCreateClubRequest")); 
		suite.addTest(new InitialAssociationServiceTest ("testRejectCreateClubRequest")); 
		suite.addTest(new InitialAssociationServiceTest ("testClubManagerAssociation")); 
		suite.addTest(new InitialAssociationServiceTest ("testUserApplyToClub")); 
		suite.addTest(new InitialAssociationServiceTest ("testClubApproveUserApplyRequest")); 
		suite.addTest(new InitialAssociationServiceTest ("testClubRejectUserApplyRequest")); 
		suite.addTest(new InitialAssociationServiceTest ("testClubMemberAssociation")); 
		suite.addTest(new InitialAssociationServiceTest ("testUserApplyFriendRequest")); 
		suite.addTest(new InitialAssociationServiceTest ("testUserRejectFriend")); 
		suite.addTest(new InitialAssociationServiceTest ("testUserApproveFriend")); 
		suite.addTest(new InitialAssociationServiceTest ("testFriendAssociation")); 
		suite.addTest(new InitialAssociationServiceTest ("testClubInviteUserRequest")); 
		suite.addTest(new InitialAssociationServiceTest ("testRejectClubInvite")); 
		suite.addTest(new InitialAssociationServiceTest ("testApproveClubInvite")); 
		suite.addTest(new InitialAssociationServiceTest ("testClubMemberAssociationAfterInvite")); 



		return suite; 
		} 


}
