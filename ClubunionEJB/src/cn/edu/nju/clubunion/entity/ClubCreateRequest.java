package cn.edu.nju.clubunion.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.edu.nju.clubunion.abstractEntity.AClubCreateRequest;

@Entity
@Table(name = "club_create_apply_requests")
public class ClubCreateRequest extends AClubCreateRequest {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender_id")
	private User sender;

	public String getReceiverName() {
		return "校级管理员";
	}

	public String getSenderName() {
		return sender.getRealName();
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User user) {
		this.sender = user;
	}
	

}
