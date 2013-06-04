package cn.edu.nju.clubunion.abstractEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

import cn.edu.nju.clubunion.helper.PPW;

@MappedSuperclass
public abstract class AJoinClubRequest extends ARequest implements Serializable {
	/**
	 * 
	 */
	/**
	 * 
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "grade")
	private Integer grade;
	@Column(name = "institute")
	private String institute;
	@Column(name = "club_id", insertable = false, updatable = false)
	private Integer receiverId;
	@Column(name = "user_id", insertable = false, updatable = false)
	private Integer senderId;

	protected int requestType = PPW.USER_APPLY_TO_CLUB_REQUEST;

	public AJoinClubRequest() {
		super();
	}
	
	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
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
