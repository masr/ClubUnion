package cn.edu.nju.clubunion.abstractEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

import cn.edu.nju.clubunion.helper.PPW;

@MappedSuperclass
public abstract class AClubInviteRequest extends ARequest implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Column(name = "user_id", insertable = false, updatable = false)
	private Integer receiverId;
	@Column(name = "club_id", insertable = false, updatable = false)
	private Integer senderId;
	private int requestType = PPW.CLUB_INVITE_USER_REQUEST;

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public int getRequestType() {
		return requestType;
	}

}
