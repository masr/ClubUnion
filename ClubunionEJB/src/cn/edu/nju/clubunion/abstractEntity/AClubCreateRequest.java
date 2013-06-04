package cn.edu.nju.clubunion.abstractEntity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

import cn.edu.nju.clubunion.helper.PPW;

@MappedSuperclass
public abstract class AClubCreateRequest extends ARequest implements
		Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = -4910063536686209574L;
	/**
	 * 
	 */

	@Column(name = "club_name")
	private String clubName;
	@Column(name = "grade")
	private String grade;
	@Column(name = "institute")
	private String institute;
	@Column(name = "sender_id", insertable = false, updatable = false)
	private Integer senderId;
	private int requestType = PPW.CREATE_CLUB_REQUEST_TYPE;

	public Integer getReceiverId() {
		return null;
	}

	public void setReceiverId(Integer id) {

	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public AClubCreateRequest() {
		super();
	}

	public int getRequestType() {
		return this.requestType;
	}



	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

}
