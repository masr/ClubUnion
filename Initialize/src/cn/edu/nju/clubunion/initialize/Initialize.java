package cn.edu.nju.clubunion.initialize;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import cn.edu.nju.clubunion.abstractEntity.AClubStyle;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubDesignService;
import cn.edu.nju.clubunion.dao.ClubDesignDAO;
import cn.edu.nju.clubunion.daoRemote.ClubDesignDAORemote;
import cn.edu.nju.clubunion.entity.ClubStyle;
import cn.edu.nju.clubunion.entity.ContainerBlock;

public class Initialize {
	private ClubDesignDAORemote designDAO;
	
	public Initialize() throws NamingException
	{
		Context ctx = new InitialContext();

	    designDAO = (ClubDesignDAORemote) ctx
		.lookup("ClubDesignDAO/remote");
	}
	
	public void statrInitializeDatabase()
	{
		ClubStyle style=designDAO.getClubStyleByClubId(1);
	    style.setMainBackColor("orange");
	    style.setBackColor("#0000ff");
	    style.setLinkColor("#0000ff");
	    designDAO.mergeClubStyle(style);
	    ContainerBlock c;
	    c=new ContainerBlock();
	    c.setClubStyleId(style.getId());
	    c.setContentType(3);
	    c.setY("900px");
	    c.setX("10px");
	    c.setH("100px");
	    c.setW("100px");
	    c.setBorderColor("#0000ff");
	    c.setBorderSolid("2px");
        designDAO.createContainerBlock(c);
	    c=new ContainerBlock();
	    c.setClubStyleId(style.getId());
	    c.setContentType(4);
	    c.setY("500px");
	    c.setX("40px");
	    c.setH("300px");
	    c.setW("200px");
	    c.setBorderColor("#0000ff");
	    c.setBorderSolid("2px");
        designDAO.createContainerBlock(c);
	    c=new ContainerBlock();
	    c.setClubStyleId(style.getId());
	    c.setContentType(2);
	    c.setY("10px");
	    c.setX("30px");
	    c.setH("60px");
	    c.setW("700px");
	    c.setBorderColor("#0000ff");
	    c.setBorderSolid("2px");
        designDAO.createContainerBlock(c);
	    c=new ContainerBlock();
	    c.setClubStyleId(style.getId());
	    c.setContentType(1);
	    c.setY("120px");
	    c.setX("300px");
	    c.setH("700px");
	    c.setW("400px");
	    c.setBorderColor("#0000ff");
	    c.setBorderSolid("2px");
        designDAO.createContainerBlock(c);
        
	    c=new ContainerBlock();
	    
	    c.setClubStyleId(style.getId());
	    c.setContentType(0);
	    c.setLinkColor("#ffffff");
	    c.setY("120px");
	    c.setX("30px");
	    c.setH("300px");
	    c.setW("130px");
	    c.setBorderColor("#0000ff");
	    c.setBackColor("#0000ff");
	    c.setBorderSolid("2px");
	    c.setFontColor("orange");
        designDAO.createContainerBlock(c);
    
	}

}
