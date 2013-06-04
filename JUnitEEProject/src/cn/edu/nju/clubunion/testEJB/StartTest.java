package cn.edu.nju.clubunion.testEJB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import junit.framework.TestCase;

public class StartTest extends TestCase{

	public void setUp(){
		try {
			super.setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tearDown(){
		try {
			super.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void testStartTest(){
		 try{
			  Class.forName("com.mysql.jdbc.Driver");
		  }
		  catch(ClassNotFoundException e)
		  {
			  System.out.print("类找不到");
		  }
		  Connection con;
		  try
		  {
		     con=DriverManager.getConnection("jdbc:mysql://localhost:3306/clubunion_test","maosuhan","msheric");
		   
		     String[] sqls={
		    		  "delete from friend_apply_requests",
		 		     "delete from club_invite_user_requests",
		 		     "delete from club_create_apply_requests", 
		 		     "delete from user_apply_to_club_requests",
		 		    "delete from carer_clubs",
				     "delete from manager_clubs",
				     "delete from member_clubs",
				     "delete from user_friends",
				     "delete from tag_documents",
				     "delete from comments",
				     "delete from poster",
				     "delete from documents",
				     "delete from tags",
		     "delete from letters",
		     "delete from supervisors",
		     "delete from messages",
    		 "delete from clubs",
		     "delete from users"
		   };
		     
			Statement st=con.createStatement();
			for(String s:sqls)
			st.execute(s);
			
		  }catch(SQLException e)
			  {
			       e.printStackTrace();
				  System.out.println("数据库连接错误");
			  }
		  }

		
	
}
