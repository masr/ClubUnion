package cn.edu.nju.clubunion.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Query;

import cn.edu.nju.clubunion.daoRemote.ClubDesignDAORemote;
import cn.edu.nju.clubunion.entity.GalleryImg;
import cn.edu.nju.clubunion.entity.Club;
import cn.edu.nju.clubunion.entity.ClubStyle;
import cn.edu.nju.clubunion.entity.ContainerBlock;
import cn.edu.nju.clubunion.entity.ImgContent;

import cn.edu.nju.clubunion.entity.TextContent;
import cn.edu.nju.clubunion.helper.EJBH;

@Stateless

public class ClubDesignDAO extends AbstractDAO implements ClubDesignDAORemote{

	public int createClubStyle(int clubId) {
		Club club=entitymanager.find(Club.class, clubId);
        ClubStyle clubStyle=new ClubStyle();
        clubStyle.setClub(club);
          ClubStyle style=new ClubStyle();
        
          style.setClub(club);
          entitymanager.persist(style);

  	    style.setMainBackColor("orange");
  	    style.setBackColor("#0000ff");
  	    style.setLinkColor("#0000ff");
  	    mergeClubStyle(style);
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
          createContainerBlock(c);
  	    c=new ContainerBlock();
  	    c.setClubStyleId(style.getId());
  	    c.setContentType(4);
  	    c.setTitle("留言板");
  	    c.setY("500px");
  	    c.setX("40px");
  	    c.setH("300px");
  	    c.setW("200px");
  	    c.setBorderColor("#0000ff");
  	    c.setBorderSolid("2px");
          createContainerBlock(c);
  	    c=new ContainerBlock();
  	    c.setClubStyleId(style.getId());
  	    c.setContentType(2);
  	    
  	    c.setY("10px");
  	    c.setX("30px");
  	    c.setH("60px");
  	    c.setW("700px");
  	    c.setBorderColor("#0000ff");
  	    c.setBorderSolid("2px");
          createContainerBlock(c);
  	    c=new ContainerBlock();
  	    c.setClubStyleId(style.getId());
  	    c.setContentType(1);
  	    c.setTitle("文章列表");
  	    c.setY("120px");
  	    c.setX("300px");
  	    c.setH("700px");
  	    c.setW("400px");
  	    c.setBorderColor("#0000ff");
  	    c.setBorderSolid("2px");
          createContainerBlock(c);
          
  	    c=new ContainerBlock();
  	    
  	    c.setClubStyleId(style.getId());
  	    c.setContentType(0);
  	    c.setTitle("成员列表");
  	    c.setLinkColor("#0000ff");
  	    c.setY("120px");
  	    c.setX("30px");
  	    c.setH("300px");
  	    c.setW("130px");
  	    c.setBorderColor("#0000ff");
  	    c.setBackColor("#0000ff");
  	    c.setBorderSolid("2px");
  	    c.setFontColor("orange");
         createContainerBlock(c);
         
		return style.getId();
		
	}

	public int createContainerBlock(ContainerBlock container) {
		ClubStyle style=entitymanager.find(ClubStyle.class, container.getClubStyleId());
		container.setClubStyle(style);
		entitymanager.persist(container);
		return container.getId();
	}



	public ClubStyle getClubStyleById(int clubStyleId) {
		
		ClubStyle style=entitymanager.find(ClubStyle.class, clubStyleId);
		return style;
	}

	public List<ContainerBlock> getContainerBlocks(int clubStyleId) {
		ClubStyle style=entitymanager.find(ClubStyle.class, clubStyleId);
        return EJBH.getListOneByOne(style.getContainers());
     
	}

	public void mergeClubStyle(ClubStyle clubStyle) {
		entitymanager.merge(clubStyle);
	}

	public void mergeContainerBlock(ContainerBlock container) {
		entitymanager.merge(container);

	}

	public void createDefaultClubStyle(ClubStyle style) {

       entitymanager.persist(style);         		
	}

	public ContainerBlock getSingleContainer(int containerId) {
		return entitymanager.find(ContainerBlock.class, containerId);
	}

	public void autoCreateContainers(int clubStyleId) {
		// TODO Auto-generated method stub
		
	}

	public ClubStyle getClubStyleByClubId(int clubId) {
		Club club=entitymanager.find(Club.class, clubId);
		ClubStyle style=entitymanager.find(ClubStyle.class, club.getClubStyle().getId());
		return style;
	}






	public int createImgContent(ImgContent content) {
		ContainerBlock container=entitymanager.find(ContainerBlock.class,content.getContainerId());
		content.setContainer(container);
		entitymanager.persist(content);
		
		return content.getId();
	}

	public int createTextContent(TextContent content) {
		ContainerBlock container=entitymanager.find(ContainerBlock.class,content.getContainerId());
		content.setContainer(container);
		entitymanager.persist(content);
		return content.getId();
	}

	

	public void deleteImgContent(int id) {
	ImgContent content=	entitymanager.find(ImgContent.class, id);
	if (content!=null)
		entitymanager.remove(content);
	}

	public void deleteTextContent(int id) {
		TextContent content=entitymanager.find(TextContent.class, id);
		if (content!=null)
			entitymanager.remove(content);
	}

	public void deleteContainer(int id) {
		ContainerBlock c=entitymanager.find(ContainerBlock.class, id);
		if (c==null)
			return ;
		entitymanager.remove(c);
		
	}

	public int insertBlockImg(GalleryImg img) {
		ContainerBlock container=entitymanager.find(ContainerBlock.class, img.getContainerId());
		img.setContainer(container);
		entitymanager.persist(img);
		return img.getId();
	}
	
	public  void removeAllGallery(int containerId){
		ContainerBlock container=entitymanager.find(ContainerBlock.class, containerId);
		for(int i=0;i<container.getGalleryImgs().size();i++)
		{
		     entitymanager.remove(container.getGalleryImgs().get(i));
		   
		}

	}



	public void mergeTextContent(TextContent content) {
		entitymanager.merge(content);
		
	}


	


}
