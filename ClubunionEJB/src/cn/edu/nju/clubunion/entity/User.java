/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cn.edu.nju.clubunion.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.edu.nju.clubunion.abstractEntity.AUser;

/**
 * 
 * @author maosuhan
 */
@Entity
@Table(name = "users")
public class User extends AUser {

	@ManyToMany(mappedBy = "members")
	private List<Club> joinedClubs = new ArrayList<Club>();

	@ManyToMany(mappedBy = "managers")
	private List<Club> managedClubs = new ArrayList<Club>();

	@ManyToMany(mappedBy = "carers")
	private List<Club> caredClubs = new ArrayList<Club>();

	@OneToMany(mappedBy = "receiver")
	private List<Letter> receivedLetters = new ArrayList<Letter>();

	@OneToMany(mappedBy = "sender")
	private List<Letter> sendedLetters = new ArrayList<Letter>();

	@OneToMany(mappedBy = "user")
	private List<Comment> comments = new ArrayList<Comment>();

	@OneToMany(mappedBy = "user")
	private List<Message> messages = new ArrayList<Message>();

	@OneToMany(mappedBy = "user")
	private List<Document> documents = new ArrayList<Document>();

	@ManyToMany
	@JoinTable(name = "user_friends", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "friend_id", referencedColumnName = "id") })
	private List<User> friends = new ArrayList<User>();

	@OneToMany(mappedBy = "sender")
	private List<FriendRequest> applyingFriendsRequests = new ArrayList<FriendRequest>();
	@OneToMany(mappedBy = "receiver")
	private List<FriendRequest> receivedFriendsRequests = new ArrayList<FriendRequest>();
	@OneToMany(mappedBy = "sender")
	private List<JoinClubRequest> applyingClubRequests = new ArrayList<JoinClubRequest>();
	@OneToMany(mappedBy = "receiver")
	private List<ClubInviteRequest> clubInviteRequests = new ArrayList<ClubInviteRequest>();
	@OneToMany(mappedBy = "sender")
	private List<ClubCreateRequest> clubCreateRequests = new ArrayList<ClubCreateRequest>();

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public List<ClubCreateRequest> getClubCreateRequests() {
		return clubCreateRequests;
	}

	public void setClubCreateRequests(List<ClubCreateRequest> clubCreateRequests) {
		this.clubCreateRequests = clubCreateRequests;
	}

	public List<FriendRequest> getApplyingFriendsRequests() {
		return applyingFriendsRequests;
	}

	public void setApplyingFriendsRequests(
			List<FriendRequest> applyingFriendsRequests) {
		this.applyingFriendsRequests = applyingFriendsRequests;
	}

	public List<FriendRequest> getReceivedFriendsRequests() {
		return receivedFriendsRequests;
	}

	public void setReceivedFriendsRequests(
			List<FriendRequest> receivedFriendsRequests) {
		this.receivedFriendsRequests = receivedFriendsRequests;
	}

	public List<JoinClubRequest> getApplyingClubRequests() {
		return applyingClubRequests;
	}

	public void setApplyingClubRequests(
			List<JoinClubRequest> applyingClubRequests) {
		this.applyingClubRequests = applyingClubRequests;
	}

	public List<ClubInviteRequest> getClubInviteRequests() {
		return clubInviteRequests;
	}

	public void setClubInviteRequests(List<ClubInviteRequest> clubInviteRequests) {
		this.clubInviteRequests = clubInviteRequests;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public List<Letter> getReceivedLetters() {
		return receivedLetters;
	}

	public void setReceivedLetters(List<Letter> receivedLetters) {
		this.receivedLetters = receivedLetters;
	}

	public List<Letter> getSendedLetters() {
		return sendedLetters;
	}

	public void setSendedLetters(List<Letter> sendedLetters) {
		this.sendedLetters = sendedLetters;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Club> getCaredClubs() {
		return caredClubs;
	}

	public void setCaredClubs(List<Club> caredClubs) {
		this.caredClubs = caredClubs;
	}

	public List<Club> getJoinedClubs() {
		return joinedClubs;
	}

	public void setJoinedClubs(List<Club> joinedClubs) {
		this.joinedClubs = joinedClubs;
	}

	public List<Club> getManagedClubs() {
		return managedClubs;
	}

	public void setManagedClubs(List<Club> managedClubs) {
		this.managedClubs = managedClubs;
	}

	public User() {
	}

}
