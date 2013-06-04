package cn.edu.nju.clubunion.mBean.sup;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IDocumentService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

public class SuperDocument {
	private List<ADocument> notices;
	private List<ADocument> publicDocuments;
	private List<ADocument> privateDocuments;

	public List<ADocument> getNotices() {
		return notices;
	}

	public void setNotices(List<ADocument> notices) {
		this.notices = notices;
	}

	public List<ADocument> getPublicDocuments() {
		return publicDocuments;
	}

	public void setPublicDocuments(List<ADocument> publicDocuments) {
		this.publicDocuments = publicDocuments;
	}

	public List<ADocument> getPrivateDocuments() {
		return privateDocuments;
	}

	public void setPrivateDocuments(List<ADocument> privateDocuments) {
		this.privateDocuments = privateDocuments;
	}

	public SuperDocument() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IDocumentService service;

		service = (IDocumentService) CUH
				.getRemoteService("DocumentService/remote");
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return;
		}
		try {
			notices = service.getAllNotices(user);
			publicDocuments = service.getAllPublicDocuments(user);
			privateDocuments = service.getAllPrivateDocuments(user);

		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));

		}

	}
}
