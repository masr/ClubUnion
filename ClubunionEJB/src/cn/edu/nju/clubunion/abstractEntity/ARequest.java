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
public abstract class ARequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4656485685537590505L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Column(name = "approved", columnDefinition = "bool default false")
	private boolean approved;
	@Column(name = "processed", columnDefinition = "bool default false")
	private boolean processed;
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Lob
	@Column(name = "description")
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public abstract Integer getSenderId();

	public abstract Integer getReceiverId();

	public abstract void setReceiverId(Integer receiverId);

	public abstract void setSenderId(Integer senderId);

	public ARequest() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public abstract int getRequestType();

	public abstract String getReceiverName();

	public abstract String getSenderName();
	
	public abstract String getClubName();

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ARequest other = (ARequest) obj;
		if (other.getId().equals(this.getId()))
			return true;

		return false;

	}
}
