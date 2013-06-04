package cn.edu.nju.clubunion.sessionBean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import cn.edu.nju.clubunion.abstractEntity.ABlockContent;
import cn.edu.nju.clubunion.abstractEntity.AGalleryImg;
import cn.edu.nju.clubunion.abstractEntity.AClubStyle;
import cn.edu.nju.clubunion.abstractEntity.AContainerBlock;
import cn.edu.nju.clubunion.abstractEntity.AImgContent;
import cn.edu.nju.clubunion.abstractEntity.ATextContent;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubDesignService;
import cn.edu.nju.clubunion.daoRemote.ClubDesignDAORemote;
import cn.edu.nju.clubunion.entity.GalleryImg;
import cn.edu.nju.clubunion.entity.ClubStyle;
import cn.edu.nju.clubunion.entity.ContainerBlock;
import cn.edu.nju.clubunion.entity.ImgContent;
import cn.edu.nju.clubunion.entity.TextContent;
import cn.edu.nju.clubunion.helper.PPW;

@Stateless
public class ClubDesignService implements IClubDesignService {
	
	@EJB(beanName = "ClubDesignDAO")
	ClubDesignDAORemote designDAO;
	


	public int createContainerBlock(IAccountService user,int clubId,
			AContainerBlock block) {
		ClubStyle c=designDAO.getClubStyleByClubId(clubId);
		if (c==null)
			return -1;
		int styleId=c.getId();
		block.setClubStyleId(styleId);

		int id=designDAO.createContainerBlock((ContainerBlock)block);
		return id;
	}
	



	@SuppressWarnings("unchecked")
	public List<AContainerBlock> getContainerBlocks(IAccountService user,int clubId) {
         
		ClubStyle style=designDAO.getClubStyleByClubId(clubId);
		if (style==null)
			return null;
		
        return designDAO.getContainerBlocks(style.getId());
      
	}


	public void updateClubStyle(IAccountService user, int clubId,
			AClubStyle clubStyle) {

          designDAO.mergeClubStyle((ClubStyle)clubStyle);

	}
	
	
	public void updateContainerBlock(IAccountService user, int clubId,
			AContainerBlock block) {

              designDAO.mergeContainerBlock((ContainerBlock)block);
  }
	

	public AClubStyle getClubStyle(IAccountService user, int clubId) {
		 return designDAO.getClubStyleByClubId(clubId);
	}



	public AContainerBlock getSingleContainerBlock(IAccountService user,
			int clubId, int containerId) {
		return designDAO.getSingleContainer(containerId);

	}

	public int createImgContent(IAccountService user,int clubId,int containerId,AImgContent content)
	{
		content.setContainerId(containerId);
		return designDAO.createImgContent((ImgContent)content);
	}




	public void deleteContainer(IAccountService user, int clubId,
			int containerId) {

        ContainerBlock container=designDAO.getSingleContainer(containerId);
        if (container==null)
        	return ;
        designDAO.deleteContainer(containerId);
       
	}




	public int createTextContent(IAccountService user, int clubId,
			int containerId, ATextContent content) {
		content.setContainerId(containerId);
		return designDAO.createTextContent((TextContent)content);
	}









	public void mergeTextContent(IAccountService user, int clubId, ATextContent content) {
		 designDAO.mergeTextContent((TextContent)content);
	}




	public void createBlockImg(IAccountService user, int clubId,
			int containerId, List<AGalleryImg> blockImgs) {


           
         designDAO.removeAllGallery(containerId);
			for(int i=0;i<blockImgs.size();i++)
			{
			     blockImgs.get(i).setContainerId(containerId);	
			    designDAO.insertBlockImg((GalleryImg)blockImgs.get(i));
			}
		
	}
	
	

}
