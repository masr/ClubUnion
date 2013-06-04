package cn.edu.nju.clubunion.mBean.account;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IDocumentService;
import cn.edu.nju.clubunion.businessLogicClient.IUserService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

/**
 * @author godfrey
 * 
 */
public class AccountDocument {
	private List<ADocument> documents;
	private IAccountService user = CUH.getAccountService();

	public AccountDocument() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IUserService userService = null;
		userService = (IUserService) CUH.getRemoteService("UserService/remote");
		if (userService == null) {
			CUH.addServiceExceptionMessage();
			return;
		}
		try {
			documents = userService.getNotices(user, user.getId());
			documents
					.addAll(userService.getPublicDocuments(user, user.getId()));
			documents.addAll(userService
					.getPrivateDocuments(user, user.getId()));
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
		}
	}

	public List<ADocument> getDocuments() {
		return documents;
	}

	public void setDocuments(List<ADocument> documents) {
		this.documents = documents;
	}

	public String deleteDocument() {
		String idstr = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("docid");
		Integer id = Integer.parseInt(idstr);
		System.out.print(id);
		IDocumentService service = (IDocumentService) CUH
				.getRemoteService("DocumentService/remote");
		try {
			service.deleteDocument(user, id);
		} catch (ErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("delete");
		return "success";
	}

	public String editDocument() {
		String idstr = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("docid");
		Integer id = Integer.parseInt(idstr);
		System.out.print(id);
		System.out.print("edit");
		return "success";
	}
}
