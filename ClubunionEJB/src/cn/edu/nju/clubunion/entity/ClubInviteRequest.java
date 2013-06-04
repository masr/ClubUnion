package cn.edu.nju.clubunion.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.edu.nju.clubunion.abstractEntity.AClubInviteRequest;

@Entity
@Table(name = "club_invite_user_requests")
public class ClubInviteRequest extends AClubInviteRequest {
	/**
	 * 
	 */

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "club_id")
	private Club sender;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private User receiver;

	public ClubInviteRequest() {

	}

	public String getReceiverName() {
		return receiver.getRealName();
	}

	public Club getSender() {
		return sender;
	}

	public void setSender(Club sender) {
		this.sender = sender;
	}

	public String getSenderName() {
		return sender.getName();
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
