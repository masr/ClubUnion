package cn.edu.nju.clubunion.mBean.platform;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.AClub;
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
public class Index {
	private List<AClub> managedClubs = null;
	private List<AClub> joinedClubs = null;
	private List<AClub> caredClubs = null;
	private List<AClub> hotClubs = null;
	private List<ADocument> currentNotices = null;
	private List<ADocument> currentPublicDocuments = null;
	private List<ADocument> currentPrivateDocuments = null;

	public Index() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IUserService userService;
		IDocumentService documentService;

		userService = (IUserService) CUH.getRemoteService("UserService/remote");
		if (userService == null) {
			CUH.addServiceExceptionMessage();
			return;
		}
		documentService = (IDocumentService) CUH
				.getRemoteService("DocumentService/remote");
		if (documentService == null) {
			CUH.addServiceExceptionMessage();
			return;
		}

		try {
			this.currentNotices = CUH.reverse(documentService
					.getAllNotices(user));
			this.currentPublicDocuments = CUH.reverse(documentService
					.getAllPublicDocuments(user));
			this.currentPrivateDocuments = CUH.reverse(documentService
					.getAllPrivateDocuments(user));
			List<ADocument> allDocuments=new ArrayList<ADocument>();
		
			
		
			
			
		} catch (ErrorException e1) {
			fctx.addMessage(null, new FacesMessage(e1.getReason()));
		}
		if (user.loggedIn()) {
			try {
				managedClubs = userService.getManagedClubs(user, user.getId());
				joinedClubs = userService.getJoinedClubs(user, user.getId());
				caredClubs = userService.getCaredClubs(user, user.getId());

				caredClubs.removeAll(joinedClubs);
				joinedClubs.removeAll(managedClubs);

			} catch (ErrorException e) {
				fctx.addMessage(null, new FacesMessage(e.getReason()));
			}
		}
	}

	public List<AClub> getManagedClubs() {
		return managedClubs;
	}

	public void setManagedClubs(List<AClub> managedClubs) {
		this.managedClubs = managedClubs;
	}

	public List<AClub> getJoinedClubs() {
		return joinedClubs;
	}

	public void setJoinedClubs(List<AClub> joinedClubs) {
		this.joinedClubs = joinedClubs;
	}

	public List<AClub> getCaredClubs() {
		return caredClubs;
	}

	public void setCaredClubs(List<AClub> caredClubs) {
		this.caredClubs = caredClubs;
	}

	public List<AClub> getHotClubs() {
		return hotClubs;
	}

	public void setHotClubs(List<AClub> hotClubs) {
		this.hotClubs = hotClubs;
	}

	public List<ADocument> getCurrentNotices() {
		return currentNotices;
	}

	public void setCurrentNotices(List<ADocument> currentNotices) {
		this.currentNotices = currentNotices;
	}

	public List<ADocument> getCurrentPublicDocuments() {
		return currentPublicDocuments;
	}

	public void setCurrentPublicDocuments(List<ADocument> currentPublicDocuments) {
		this.currentPublicDocuments = currentPublicDocuments;
	}

	public List<ADocument> getCurrentPrivateDocuments() {
		return currentPrivateDocuments;
	}

	public void setCurrentPrivateDocuments(
			List<ADocument> currentPrivateDocuments) {
		this.currentPrivateDocuments = currentPrivateDocuments;
	}

}