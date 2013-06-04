package cn.edu.nju.clubunion.abstractEntity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import cn.edu.nju.clubunion.entity.ContainerBlock;

@MappedSuperclass
public abstract class ABlockContent  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name="container_id",insertable=false, updatable=false)
	private Integer containerId;



public Integer getContainerId() {
		return containerId;
	}

	public void setContainerId(Integer containerId) {
		this.containerId = containerId;
	}

  



public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}

