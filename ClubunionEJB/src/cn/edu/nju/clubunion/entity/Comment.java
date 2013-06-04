/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.edu.nju.clubunion.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.edu.nju.clubunion.abstractEntity.AComment;

/**
 * 
 * @author maosuhan
 */
@Entity
@Table(name = "comments")
public class Comment extends AComment {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "document_id")
	private Document document;

	public Comment() {

	}

	public String getDocumentName() {
		return this.document.getTitle();
	}

	public String getUserName() {
		return  user.getRealName();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Document getDocument() {
		return document;
	} 

	public void setDocument(Document document) {
		this.document = document;
	} 

}
