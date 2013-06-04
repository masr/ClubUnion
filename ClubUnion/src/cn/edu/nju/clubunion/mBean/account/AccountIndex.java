package cn.edu.nju.clubunion.mBean.account;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.AClub;
import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IDocumentService;
import cn.edu.nju.clubunion.businessLogicClient.IUserService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

/**
 * @author godfrey
 * 
 */
public class AccountIndex {
	private List<AClub> managedClubs = null;
	private List<AClub> joinedClubs = null;
	private List<AClub> caredClubs = null;
	private List<ARequest> unsovledRequests = null;
	private List<ADocument> currentDocuments = null;

	public AccountIndex() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IUserService service = null;
		IDocumentService documentService;

		service = (IUserService) CUH.getRemoteService("UserService/remote");
		documentService = (IDocumentService) CUH
				.getRemoteService("DocumentService/remote");
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return;
		}

		try {
			managedClubs = service.getManagedClubs(user, user.getId());
			joinedClubs = service.getJoinedClubs(user, user.getId());
			caredClubs = service.getCaredClubs(user, user.getId());

			caredClubs.removeAll(joinedClubs);
			joinedClubs.removeAll(managedClubs);

			unsovledRequests = service
					.getUnsolvedReceivedClubInviteRequests(user);
			unsovledRequests.addAll(service
					.getUnsolvedReceivedFriendRequsets(user));
			currentDocuments = documentService.getAllNotices(user);
			currentDocuments
					.addAll(documentService.getAllPublicDocuments(user));
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
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

	public List<ARequest> getUnsovledRequests() {
		return unsovledRequests;
	}

	public void setUnsovledRequests(List<ARequest> unsovledRequests) {
		this.unsovledRequests = unsovledRequests;
	}

	public List<ADocument> getCurrentDocuments() {
		return currentDocuments;
	}

	public void setCurrentDocuments(List<ADocument> currentDocuments) {
		this.currentDocuments = currentDocuments;
	}
}
