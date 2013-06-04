package cn.edu.nju.clubunion.abstractEntity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ATextContent  extends ABlockContent implements Serializable{
	private static final long serialVersionUID = 1L;
	



	@Column(name="text")
	private String text;


	


	


	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	

}
