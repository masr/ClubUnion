package cn.edu.nju.clubunion.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.edu.nju.clubunion.abstractEntity.AImgContent;
import cn.edu.nju.clubunion.helper.PPW;
@Entity
@Table(name = "img_contents")
public class ImgContent extends AImgContent{

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	@OneToOne
	@JoinColumn(name = "container_id")
	private ContainerBlock container;
	
	
	
	public ContainerBlock getContainer() {
		return container;
	}



	public void setContainer(ContainerBlock container) {
		this.container = container;
	}





}
