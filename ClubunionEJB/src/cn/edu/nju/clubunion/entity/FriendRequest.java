package cn.edu.nju.clubunion.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.edu.nju.clubunion.abstractEntity.AFriendRequest;

@Entity
@Table(name = "friend_apply_requests")
public class FriendRequest extends AFriendRequest {
	/**
	 * 
	 */

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User sender;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "friend_id")
	private User receiver;

	public String getReceiverName() {
		return receiver.getRealName();
	}

	public String getSenderName() {
		return sender.getRealName();
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	@Override
	public String getClubName() {
		// TODO Auto-generated method stub
		return null;
	}

}
