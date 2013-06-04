package cn.edu.nju.clubunion.daoRemote;

import java.util.List;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.entity.GalleryImg;
import cn.edu.nju.clubunion.entity.ClubStyle;
import cn.edu.nju.clubunion.entity.ContainerBlock;
import cn.edu.nju.clubunion.entity.ImgContent;
import cn.edu.nju.clubunion.entity.TextContent;
@Remote
public interface ClubDesignDAORemote {
 
   public int createClubStyle(int clubId);
 
 
   public ClubStyle getClubStyleById(int clubStyleId);
   public void createDefaultClubStyle(ClubStyle style);
   public void autoCreateContainers(int clubStyleId);
   
   
   public void mergeContainerBlock(ContainerBlock container);
   public ContainerBlock getSingleContainer(int containerId);
   public List getContainerBlocks(int clubStyleId);
   public int createContainerBlock(ContainerBlock container);
   public void mergeClubStyle(ClubStyle clubStyle);
   public ClubStyle getClubStyleByClubId(int clubId);
   public int insertBlockImg(GalleryImg img);
   public int createImgContent(ImgContent content);
   public int createTextContent(TextContent content);
   public void deleteTextContent(int id);
   public void deleteImgContent(int id);
   public void deleteContainer(int id);
   public void mergeTextContent(TextContent content);
public void  removeAllGallery(int containerId);   
}
