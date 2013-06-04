/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.edu.nju.clubunion.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cn.edu.nju.clubunion.abstractEntity.AClub;

/**
 * 
 * @author maosuhan
 */
@Entity 
@Table(name = "clubs")
public class Club extends AClub {

	/** @Id
	 * 
	 */
	@ManyToMany
	@JoinTable(name = "member_clubs", joinColumns = { @JoinColumn(name = "club_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") })
	private List<User> members = new ArrayList<User>();

	@ManyToMany
	@JoinTable(name = "manager_clubs", joinColumns = { @JoinColumn(name = "club_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") })
	private List<User> managers = new ArrayList<User>();

	@ManyToMany
	@JoinTable(name = "carer_clubs", joinColumns = { @JoinColumn(name = "club_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") })
	private List<User> carers = new ArrayList<User>();

	@OneToMany(mappedBy = "club")
	private List<Message> messages = new ArrayList<Message>();

	@OneToMany(mappedBy = "club")
	private List<Document> documents = new ArrayList<Document>();
	@OneToMany(mappedBy = "receiver")
	private List<JoinClubRequest> userApplyRequests = new ArrayList<JoinClubRequest>();
	@OneToMany(mappedBy = "sender")
	private List<ClubInviteRequest> invitingUserRequests = new ArrayList<ClubInviteRequest>();
	@OneToOne(mappedBy = "club")
	private ClubStyle clubStyle;

	public ClubStyle getClubStyle() {
		return clubStyle;
	}

	public void setClubStyle(ClubStyle clubStyle) {
		this.clubStyle = clubStyle;
	}

	public List<JoinClubRequest> getUserApplyRequests() {
		return userApplyRequests;
	}

	public void setUserApplyRequests(List<JoinClubRequest> userApplyRequests) {
		this.userApplyRequests = userApplyRequests;
	}

	public List<ClubInviteRequest> getInvitingUserRequests() {
		return invitingUserRequests;
	}

	public void setInvitingUserRequests(
			List<ClubInviteRequest> invitingUserRequests) {
		this.invitingUserRequests = invitingUserRequests;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<User> getCarers() {
		return carers;
	}

	public void setCarers(List<User> carers) {
		this.carers = carers;
	}

	public List<User> getManagers() {
		return managers;
	}

	public void setManagers(List<User> managers) {
		this.managers = managers;
	}

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public Club() {
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

}
