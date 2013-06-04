package cn.edu.nju.clubunion.testEJB;

import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import cn.edu.nju.clubunion.abstractEntity.AClub;
import cn.edu.nju.clubunion.abstractEntity.AComment;
import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.abstractEntity.ALetter;
import cn.edu.nju.clubunion.abstractEntity.AMessage;
import cn.edu.nju.clubunion.abstractEntity.APoster;
import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.ICalendarService;
import cn.edu.nju.clubunion.businessLogicClient.IClubService;
import cn.edu.nju.clubunion.businessLogicClient.IDocumentService;
import cn.edu.nju.clubunion.businessLogicClient.ILetterService;
import cn.edu.nju.clubunion.businessLogicClient.IMessageService;
import cn.edu.nju.clubunion.businessLogicClient.IPosterService;
import cn.edu.nju.clubunion.businessLogicClient.ISearchService;
import cn.edu.nju.clubunion.businessLogicClient.IUserService;
import cn.edu.nju.clubunion.entity.Comment;
import cn.edu.nju.clubunion.entity.Document;
import cn.edu.nju.clubunion.entity.Letter;
import cn.edu.nju.clubunion.entity.Message;
import cn.edu.nju.clubunion.entity.Poster;
import cn.edu.nju.clubunion.entity.User;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.PPW;



//在这之前，毛苏晗是究极管理员，高晟是死亡诗社管理员，金晓龙和高哲远是社团成员。高晟和刘春晨是好友关系
public class ActionServiceTest extends TestCase{
	private IAccountService accountService;
	private IUserService userService;
	private IClubService clubService;
	private ILetterService letterService;
	private IDocumentService documentService;
	private IMessageService messageService;
	private ISearchService searchService;
	private IPosterService posterService;
	private ICalendarService calendarService;
	
	private AUser msh; 
	private AUser jxl;
	private AUser gs;
	private AUser lcc;
	private AUser gzy;
	private AUser lh;
   public void setUp() throws Exception {
	   super.setUp();
		Context ctx=new InitialContext();
		accountService =(IAccountService) ctx.lookup("AccountService/remote");
		userService=(IUserService)ctx.lookup("UserService/remote");

		clubService=(IClubService)ctx.lookup("ClubService/remote");

		documentService=(IDocumentService)ctx.lookup("DocumentService/remote");

		letterService=(ILetterService)ctx.lookup("LetterService/remote");

		messageService=(IMessageService)ctx.lookup("MessageService/remote");

		searchService=(ISearchService)ctx.lookup("SearchService/remote");
		
		posterService=(IPosterService)ctx.lookup("PosterService/remote");
		
		calendarService=(ICalendarService)ctx.lookup("CalendarService/remote");

        this.specifyDatabase("test");	
		
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
   
   private void specifyDatabase(String s)
   {
		accountService.specifyDatabase(s);
		userService.specifyDatabase(s);

		clubService.specifyDatabase(s);

		documentService.specifyDatabase(s);

		letterService.specifyDatabase(s);

		messageService.specifyDatabase(s);

		searchService.specifyDatabase(s);
		posterService.specifyDatabase(s);
		calendarService.specifyDatabase(s);
   }
   public void tearDown() throws Exception{
	   super.tearDown();
		msh=null;
		gs=null;
		jxl=null;
		accountService=null;
   }
   
   public ActionServiceTest(String name)
	{
		super(name);
	}
   
   
   //测试社团管理员写了三种类型的文章
   public void testCreateDocument() throws ErrorException{
	   accountService.login(accountService,gs.getEmail(), gs.getPassword());
	   AClub club=userService.getManagedClubs(accountService, accountService.getId()).get(0);
	   ADocument document=new Document();
	   document.setContent("这是内容");
	   document.setDescription("这是简介");
	   document.setTagString("2逼 3逼 4逼");
	   document.setDocumentType(PPW.NOTICE_TYPE);
	   document.setTitle("通知");
	   document.setUserId(accountService.getId());
	   document.setClubId(club.getId());
	   document.setActivityData(new Date());
	   int documentId=documentService.createDocument(accountService, document);
	   APoster poster=new Poster();
	   poster.setPosterURL("http://www.baidu.com");
	   poster.setDocumentId(documentId);
	   posterService.addNoticePoster(accountService, club.getId(),poster, documentId);
	   
	   document=new Document();
	   document.setContent("这是内容");
	   document.setDescription("这是简介");
	   document.setTagString("2逼 3逼 5逼");
	   document.setDocumentType(PPW.PRIVATE_DOCUMENT_TYPE);
	   document.setTitle("私密文件");
	   document.setUserId(accountService.getId());
	   document.setClubId(club.getId());
	   documentService.createDocument(accountService, document);

	   document=new Document();
	   document.setContent("这是内容");
	   document.setDescription("这是简介");
	   document.setDocumentType(PPW.PUBLIC_DOCUMENT_TYPE);
	   document.setTitle("公开文件");
	   document.setUserId(accountService.getId());
	   document.setClubId(club.getId());
	   documentService.createDocument(accountService, document);

   }//高晟发表了三篇不同类型的文章
   
   
   //关系
   public void testDocumentAssociation() throws ErrorException{
	   accountService.login(accountService,gs.getEmail(), gs.getPassword());
	   AClub club=userService.getManagedClubs(accountService, accountService.getId()).get(0);
        List<ADocument> documents=documentService.getAllNotices(accountService);
        assertEquals(1,documents.size());
         documents=documentService.getAllPublicDocuments(accountService);
        assertEquals(1,documents.size());
        documents=documentService.getAllPrivateDocuments(accountService);
        assertEquals(1,documents.size());
        documents=userService.getNotices(accountService, accountService.getId());
        assertEquals(1,documents.size());
        documents=userService.getPublicDocuments(accountService, accountService.getId());
        assertEquals(1,documents.size());
        documents=userService.getPrivateDocuments(accountService, accountService.getId());
        assertEquals(1,documents.size());
        
        documents= clubService.getNotices(accountService, club.getId());
        assertEquals(1,documents.size());
        documents= clubService.getPrivateDocuments(accountService, club.getId());
        assertEquals(1,documents.size());
        documents= clubService.getPublicDocuments(accountService, club.getId());
        assertEquals(1,documents.size());
        
        List<APoster> posters=posterService.getPosters(accountService, new Date());
        assertEquals(1,posters.size());
   
       ADocument d =documents.get(0);
       assertEquals(club.getId(),d.getClubId());
       assertEquals("死亡诗社",d.getClubName());
       assertEquals(accountService.getId(),(int)d.getUserId());
       
        
 	   accountService.login(accountService,lcc.getEmail(), lcc.getPassword());
       documents=documentService.getDocumentsByTagName(accountService, "2逼");
       assertEquals(1,documents.size());
       assertEquals(4,documentService.getAllTags(accountService).size());
   }//测试外键关系
   
   public void testCalendar() throws ErrorException
   {
	   accountService.login(accountService,gs.getEmail(), gs.getPassword());
	   List<ADocument> documents=calendarService.getNotices(new Date());
	   assertEquals(1,documents.size());

   }
   
   
   //测试用户对某一篇文章发表评论
   public void testMakeComment() throws ErrorException{
	   accountService.login(accountService,msh.getEmail(), msh.getPassword());
	   ADocument d=documentService.getAllNotices(accountService).get(0);
	   assertEquals("通知",d.getTitle());
	   AComment comment=new Comment();
	   comment.setContent("这是一个评论");
	   comment.setDocumentId(d.getId());
	   comment.setUserId(accountService.getId());
	   documentService.makeComment(accountService, comment);
   }//毛苏晗对高盛发表的通知做了评论
   
   
   //测试关系
   public void testCommentAssociation() throws ErrorException{
	   accountService.login(accountService,msh.getEmail(), msh.getPassword());
	   ADocument d=documentService.getAllNotices(accountService).get(0);
          int dId=d.getId();
         List<AComment>comments= documentService.getComments(accountService, dId);
          assertEquals(1,comments.size());
          assertEquals(accountService.getId(), (int)comments.get(0).getUserId());
          assertEquals(d.getId(),comments.get(0).getDocumentId());
   }//外键测试
   
   
   //测试一个用户向一个用户发送站内信
   public void testSendLetter() throws ErrorException{
	   accountService.login(accountService,lcc.getEmail(), lcc.getPassword());
	 List<AUser> users=  userService.getFriends(accountService, accountService.getId());
	 AUser u=users.get(0);
	 ALetter letter=new Letter();
	 letter.setContent("这是一封信");
	 letter.setTitle("标题");
	 letter.setReceiverId(u.getId());
	 letter.setSenderId(accountService.getId());
	 letterService.sendLetter(accountService, letter);
   }//刘春晨向高晟发了一份邮件
   
   
   //测试关系
   public void testLetterAssociation() throws ErrorException{
	   accountService.login(accountService,gs.getEmail(), gs.getPassword());
	   List<ALetter> letters=userService.getReceivedLetters(accountService);
	   assertEquals(1,letters.size());
	   assertEquals("标题",letters.get(0).getTitle());
	   
	   accountService.login(accountService,lcc.getEmail(), lcc.getPassword());
	   letters=userService.getSentLetters(accountService);
	   assertEquals(1,letters.size());
   }//测试外键关系
   
   
   //测试一个用户向某一个社团留言
   public void testLeaveMessage() throws ErrorException{
	   accountService.login(accountService,msh.getEmail(), msh.getPassword());
	   AClub club=clubService.getAllClubs(accountService).get(0);
	   AMessage m=new Message();
	   m.setContent("这是一个留言");
	   m.setClubId(club.getId());
	   m.setUserId(accountService.getId());
	   messageService.leaveMessage(accountService, m);
   }//毛苏晗向死亡诗社留了言
   
 
   
  
   // 测试关系
   public void testMessageAssociation() throws ErrorException{
	   accountService.login(accountService,msh.getEmail(), msh.getPassword());
	   AClub club=clubService.getAllClubs(accountService).get(0);
        List<AMessage> messages= clubService.getMessages(accountService, club.getId());
         assertEquals(1,messages.size());
         assertEquals("这是一个留言",messages.get(0).getContent());
         assertEquals("死亡诗社",messages.get(0).getClubName());
   }//测试外键关系
   
   //测试根据id或其他一些信息查找用户
   public void testSearchUser() throws ErrorException{
	   accountService.login(accountService,msh.getEmail(), msh.getPassword());
       List<AUser> users= searchService.searchUser(accountService, jxl);
       assertEquals(1,users.size());
       assertEquals(jxl.getRealName(),users.get(0).getRealName());
   }//毛苏晗查找金晓龙
   
   
   //测试修改个人信息
   public void testEditUserInfo() throws ErrorException{
	   accountService.login(accountService,msh.getEmail(), msh.getPassword());
       AUser user=userService.getPersonalInfo(accountService, accountService.getId());

        user.setDescription("大家好，我是万万人迷");
        user.setNickName("万人迷");
        userService.editInfo(accountService, user);
         user=userService.getPersonalInfo(accountService, accountService.getId());
        assertEquals("大家好，我是万万人迷",user.getDescription());
        assertEquals("万人迷",accountService.getNickName());
        user.setDescription("大家好，我是万人迷");
        user.setNickName("风动我不动");
        userService.editInfo(accountService, user);
   }//毛苏晗修改个人资料
   
   //测试社团管理员修改社团信息
   public void testEditClubInfo() throws ErrorException{
	   accountService.login(accountService,gs.getEmail(), gs.getPassword());
          AClub c=clubService.getAllClubs(accountService).get(0);
          c.setName("恐怖诗社");
         clubService.editClubInfo(accountService, c.getId(), c);
         c=clubService.getAllClubs(accountService).get(0);
         assertEquals("恐怖诗社",c.getName());
         
         c.setName("死亡诗社");
         clubService.editClubInfo(accountService, c.getId(), c);
   }//高晟修改社团资料
   
   
   //测试用户修改密码
   public void testChangPassword() throws ErrorException{
	   accountService.login(accountService,msh.getEmail(), msh.getPassword());
       userService.changePassword(accountService, "msheric", "123");
       accountService.login(accountService,msh.getEmail(),"123");
       userService.changePassword(accountService, "123", "msheric");

   }//毛苏晗修改密码又改了回去
   
   
   //测试究极管理员删除用户
   public void testDeleteUserBySuper() throws ErrorException{
	   accountService.regist(accountService,lh);
	   accountService.login(accountService,msh.getEmail(), msh.getPassword());
	   List<AUser> users=userService.getAllUsers(accountService);
	   assertEquals(6,users.size());
	   int userId=0;
	   for(AUser u:users)
	   {
		   if (u.getRealName().equals(lh.getRealName()))
			   userId=u.getId();
	   }
	   assertNotSame(0,userId);
       userService.deleteUser(accountService,userId );
	   users=userService.getAllUsers(accountService);
      assertEquals(5,users.size());
   }//毛苏晗删除李辉这个用户
   
   //测试究极管理员删除某一个文章
   public void testDeleteDocumentBySuper() throws ErrorException{
	   accountService.login(accountService,msh.getEmail(), msh.getPassword());
       List<ADocument> documents=documentService.getAllPublicDocuments(accountService);
       documentService.deleteDocument(accountService, documents.get(0).getId());
       documents=documentService.getAllPublicDocuments(accountService);
       assertEquals(0,documents.size());
   }//毛苏晗删除了公开文件
   
   

   
   public static Test suite() {
		TestSuite suite=new TestSuite();
		suite.addTest(new ActionServiceTest ("testCreateDocument"));
		suite.addTest(new ActionServiceTest ("testDocumentAssociation")); 
		suite.addTest(new ActionServiceTest ("testCalendar")); 

		suite.addTest(new ActionServiceTest ("testMakeComment")); 
		suite.addTest(new ActionServiceTest ("testCommentAssociation")); 
		suite.addTest(new ActionServiceTest ("testSendLetter"));
		suite.addTest(new ActionServiceTest ("testLetterAssociation"));
		suite.addTest(new ActionServiceTest ("testLeaveMessage"));
		suite.addTest(new ActionServiceTest ("testMessageAssociation"));
		suite.addTest(new ActionServiceTest ("testSearchUser"));
		suite.addTest(new ActionServiceTest ("testEditUserInfo"));
		suite.addTest(new ActionServiceTest ("testEditClubInfo"));
		suite.addTest(new ActionServiceTest ("testChangPassword"));
		suite.addTest(new ActionServiceTest ("testDeleteUserBySuper"));
		suite.addTest(new ActionServiceTest ("testDeleteDocumentBySuper"));
        


		return suite; 
		} 
}
