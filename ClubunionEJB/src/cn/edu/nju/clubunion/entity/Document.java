/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.edu.nju.clubunion.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.edu.nju.clubunion.abstractEntity.ADocument;

/**
 * 
 * @author maosuhan
 */
@Entity
@Table(name = "documents")
public class Document extends ADocument {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "club_id")
	private Club club;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "document")
	private List<Comment> comments = new ArrayList<Comment>();

	@OneToOne(fetch = FetchType.EAGER, mappedBy = "notice")
	private Poster poster;

	public Document() {

	}

	public Poster getPoster() {
		return poster;
	}

	public void setPoster(Poster poster) {
		this.poster = poster;
	}

	public String getUserName() {
		return user.getRealName();
	}

	public String getClubName() {
		return this.club.getName();
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String getPosterURL() {
		if (poster == null)
			return "";
		return poster.getPosterURL();
	}

}
