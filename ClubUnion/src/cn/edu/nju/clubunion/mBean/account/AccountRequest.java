package cn.edu.nju.clubunion.mBean.account;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IRequestService;
import cn.edu.nju.clubunion.businessLogicClient.IUserService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;
import cn.edu.nju.clubunion.helper.PPW;
import cn.edu.nju.clubunion.sessionBean.RequestService;

/**
 * @author godfrey
 * 
 */
public class AccountRequest {
	private List<ARequest> clubInvitedRequests = null;
	private List<ARequest> friendApplyRequests = null;
	private IAccountService user = CUH.getAccountService();

	public AccountRequest() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IUserService service = null;
		service = (IUserService) CUH.getRemoteService("UserService/remote");
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return;
		}
		try {
			clubInvitedRequests = service
					.getAllReceivedClubInviteRequests(user);
			;
			friendApplyRequests = service.getAllReceivedFriendRequsets(user);
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
		}
	}

	public List<ARequest> getClubInvitedRequests() {
		return clubInvitedRequests;
	}

	public void setClubInvitedRequests(List<ARequest> clubInvitedRequests) {
		this.clubInvitedRequests = clubInvitedRequests;
	}

	public List<ARequest> getFriendApplyRequests() {
		return friendApplyRequests;
	}

	public void setFriendApplyRequests(List<ARequest> friendApplyRequests) {
		this.friendApplyRequests = friendApplyRequests;
	}

}
