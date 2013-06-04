package cn.edu.nju.clubunion.abstractEntity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class ADocument implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Lob
	@Column(name = "description")
	private String description;
	@Lob
	@Column(name = "content")
	private String content;
	@Column(name = "type")
	private Integer documentType;
	@Column(name = "activity_data")
	@Temporal(TemporalType.TIMESTAMP)
	private Date activityDate;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	@Column(name = "title")
	private String title;
	@Column(name = "user_id", insertable = false, updatable = false)
	private Integer userId;
	@Column(name = "club_id", insertable = false, updatable = false)
	private Integer clubId;




	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityData(Date activityDate) {
		this.activityDate = activityDate;
	}

	public Integer getDocumentType() {
		return documentType;
	}

	public void setDocumentType(Integer documentType) {
		this.documentType = documentType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	

	public Integer getUserId() {
		return userId;
	}

	public Integer getClubId() {
		return clubId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public abstract String getClubName();

	public abstract String getUserName();

	public Date getCreatedAt() {
		return createdAt;
	}

	public ADocument() {
		super();
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}




	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	public abstract String getPosterURL();

}
