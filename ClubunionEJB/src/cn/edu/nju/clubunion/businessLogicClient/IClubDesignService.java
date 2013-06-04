package cn.edu.nju.clubunion.businessLogicClient;

import java.util.List;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.abstractEntity.ABlockContent;
import cn.edu.nju.clubunion.abstractEntity.AGalleryImg;
import cn.edu.nju.clubunion.abstractEntity.AClubStyle;
import cn.edu.nju.clubunion.abstractEntity.AContainerBlock;
import cn.edu.nju.clubunion.abstractEntity.AImgContent;
import cn.edu.nju.clubunion.abstractEntity.ATextContent;
import cn.edu.nju.clubunion.entity.ImgContent;
import cn.edu.nju.clubunion.entity.TextContent;

@Remote
public interface IClubDesignService {
	/**
	 * 将游离态的ContainerBlock存入数据库
	 */
	public int createContainerBlock(IAccountService user, int clubId,
			AContainerBlock block);
	/**
	 * 整合某一个块的样式
	 */
	public void updateContainerBlock(IAccountService user, int clubId,
			AContainerBlock block);
	/**
	 * 整合主页全局样式
	 */
	public void updateClubStyle(IAccountService user, int clubId,
			AClubStyle clubStyle);


	/**
	 * 取得某一个社团主页所有的块信息
	 */
	public List<AContainerBlock> getContainerBlocks(IAccountService user,
			int clubId);
	/**
	 * 新建一个图片内容，需要已经新建的块的主键
	 */
	public int createImgContent(IAccountService user,int clubId,int containerId,AImgContent content);
	/**
	 * 创建一个文本内容，需要已经新建的块的主键
	 */
	public int createTextContent(IAccountService user,int clubId,int containerId,ATextContent content);
	/**
	 * 创建一个文本内容，需要已经新建的块的主键
	 */
	public void mergeTextContent(IAccountService user,int clubId,ATextContent content);

	/**
	 * 根据社团的主键取得页面全局样式信息
	 */
	public AClubStyle getClubStyle(IAccountService user,int clubId);
	/**
	 * 根据块的逐渐获取改块信息
	 */
	public AContainerBlock getSingleContainerBlock(IAccountService user,int clubId,int containerId);


	/**
	 * 删除块，将与之有关的内容全部删掉
	 */
	public void deleteContainer(IAccountService user,int clubId,int containerId);
	
	public void createBlockImg(IAccountService user,int clubId,int containerId, List<AGalleryImg> blockImgs);
}
