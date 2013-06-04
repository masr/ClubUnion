package cn.edu.nju.clubunion.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.edu.nju.clubunion.abstractEntity.AJoinClubRequest;

@Entity
@Table(name = "user_apply_to_club_requests")
public class JoinClubRequest extends AJoinClubRequest {
	/**
	 * 
	 */

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User sender;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "club_id")
	private Club receiver;

	public String getReceiverName() {
		return receiver.getName();
	}

	public String getSenderName() {
		return sender.getRealName();
	}

	public JoinClubRequest() {

	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Club getReceiver() {
		return receiver;
	}

	public void setReceiver(Club receiver) {
		this.receiver = receiver;
	}

	@Override
	public String getClubName() {
		// TODO Auto-generated method stub
		return null;
	}

}
