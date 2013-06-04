package cn.edu.nju.clubunion.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.edu.nju.clubunion.abstractEntity.APoster;

@Entity
@Table(name = "poster")
public class Poster extends APoster {
	@OneToOne
	@JoinColumn(name = "document_id")
	private Document notice;

	public Document getNotice() {
		return notice;
	}

	public void setNotice(Document notice) {
		this.notice = notice;
	}



}
