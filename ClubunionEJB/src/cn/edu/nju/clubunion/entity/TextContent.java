package cn.edu.nju.clubunion.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.edu.nju.clubunion.abstractEntity.AImgContent;
import cn.edu.nju.clubunion.abstractEntity.ATextContent;
import cn.edu.nju.clubunion.helper.PPW;
@Entity
@Table(name = "text_contents")
public class TextContent extends ATextContent{

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