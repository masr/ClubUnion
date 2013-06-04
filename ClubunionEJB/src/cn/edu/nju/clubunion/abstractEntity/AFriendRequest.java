package cn.edu.nju.clubunion.abstractEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

import cn.edu.nju.clubunion.helper.PPW;

@MappedSuperclass
public abstract class AFriendRequest extends ARequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "user_id", insertable = false, updatable = false)
	private Integer receiverId;
	@Column(name = "friend_id", insertable = false, updatable = false)
	private Integer senderId;

	protected int requestType = PPW.FRIEND_REQUEST_TYPE;

	public AFriendRequest() {
		super();
	}

	public int getRequestType() {
		return this.requestType;
	}

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
	
	public abstract String getSenderName();
	public abstract String getReceiverName();

}
