package cn.edu.nju.clubunion.mBean.document;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IDocumentService;
import cn.edu.nju.clubunion.entity.Document;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

/**
 * @author godfrey
 * 
 */
public class DocumentEdit {
	private String title = null;
	private String description = null;
	private String content = null;
	private String tagString = null;
	private Integer documentType = null;

	public DocumentEdit() {

	}

	public String getTitle() {
		return title;
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

	public String getTagString() {
		return tagString;
	}

	public void setTagString(String tagString) {
		this.tagString = tagString;
	}

	public void setDocumentType(Integer documentType) {
		this.documentType = documentType;
	}

	public int getDocumentType() {
		return documentType;
	}

	public String edit() {

		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IDocumentService documentService;

		Integer clubId = CUH.getFromRequest("club_id");
		if (clubId == null) {
			fctx.addMessage(null, new FacesMessage("参数异常"));
			return "fail";
		}

		documentService = (IDocumentService) CUH
				.getRemoteService("DocumentService/remote");
		if (documentService == null) {
			CUH.addServiceExceptionMessage();
			return "fail";
		}
		ADocument document = new Document();

		document.setContent(content);
		document.setDescription(description);

		document.setTitle(title);
		document.setDocumentType(documentType);
		document.setUpdatedAt(new Date());

		try {
			documentService.editDocument(user, document);
			return "success";
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			return "fail";
		}
	}
}
