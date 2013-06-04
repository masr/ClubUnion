package cn.edu.nju.clubunion.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.edu.nju.clubunion.abstractEntity.AContainerBlock;
import cn.edu.nju.clubunion.abstractEntity.AGalleryImg;

@Entity
@Table(name = "container_blocks")
public class ContainerBlock extends AContainerBlock {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "club_style_id")
	private ClubStyle clubStyle;
	@OneToOne(fetch = FetchType.EAGER,mappedBy="container",cascade = {CascadeType.REMOVE})
	private ImgContent imgContent;
	@OneToOne(fetch = FetchType.EAGER,mappedBy="container",cascade = {CascadeType.REMOVE})
	private TextContent textContent;
	@OneToMany(fetch = FetchType.EAGER,mappedBy="container",cascade = {CascadeType.REMOVE})
	private Set<GalleryImg> galleryImgs=new HashSet<GalleryImg>();
    

	public ImgContent getImgContent() {
		return imgContent;
	}

	public void setImgContent(ImgContent imgContent) {
		this.imgContent = imgContent;
	}

	public TextContent getTextContent() {
		return textContent;
	}

	public void setTextContent(TextContent textContent) {
		this.textContent = textContent;
	}

	








	

	public List<AGalleryImg> getGalleryImgs() {
		List<AGalleryImg> imgs=new ArrayList<AGalleryImg>();
		imgs.addAll(galleryImgs);
	
		return imgs;
	}

	public void setBlockImgs(Set<GalleryImg> galleryImgs) {
		this.galleryImgs = galleryImgs;
	}

	public ClubStyle getClubStyle() {
		return clubStyle;
	}

	public void setClubStyle(ClubStyle clubStyle) {
		this.clubStyle = clubStyle;
	}



	


}
