package cn.edu.nju.clubunion.mBean.document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.AComment;
import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IDocumentService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

/**
 * @author godfrey
 * 
 */
public class DocumentShow {
	private String title = null;
	private String description = null;
	private String content = null;

	private Integer documentType = null;
	private String clubName = null;
	private String userName = null;
	private Date createdAt;
	private Integer userId;
	private Integer clubId;
	private Integer id;
	private List<AComment> comments = new ArrayList<AComment>();
	private String posterURL = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getTitle() {
		return title;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setTitle(String title) {
		this.title = title;
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



	public Integer getDocumentType() {
		return documentType;
	}

	public void setDocumentType(Integer documentType) {
		this.documentType = documentType;
	}

	public List<AComment> getComments() {
		return comments;
	}

	public void setComments(List<AComment> comments) {
		this.comments = comments;
	}

	public String getPosterURL() {
		return posterURL;
	}

	public void setPosterURL(String posterURL) {
		this.posterURL = posterURL;
	}

	public DocumentShow() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IDocumentService documentService = null;
		int documentId = Integer.parseInt(fctx.getExternalContext()
				.getRequestParameterMap().get("document_id"));

		documentService = (IDocumentService) CUH
				.getRemoteService("DocumentService/remote");
		if (documentService == null) {
			CUH.addServiceExceptionMessage();
			return;
		}

		try {
			ADocument document = documentService.getDocumentInfo(user,
					documentId);
			title = document.getTitle();
			content = document.getContent();
			description = document.getDescription();
			documentType = document.getDocumentType();
			userName = document.getUserName();
			createdAt = document.getCreatedAt();
			clubName = document.getClubName();

			userId = document.getUserId();
			clubId = document.getClubId();
			id = document.getId();
			posterURL = document.getPosterURL();
			comments = documentService.getComments(user, documentId);
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
		}

	}
}
