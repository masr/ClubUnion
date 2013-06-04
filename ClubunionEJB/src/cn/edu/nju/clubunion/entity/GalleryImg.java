package cn.edu.nju.clubunion.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.edu.nju.clubunion.abstractEntity.AGalleryImg;
@Entity 
@Table(name = "gallery_imgs")
public class GalleryImg extends AGalleryImg{
	@ManyToOne
	@JoinColumn(name="container_id")
    private ContainerBlock container;

	public ContainerBlock getContainer() {
		return container;
	}

	public void setContainer(ContainerBlock container) {
		this.container = container;
	}
	
	
}
