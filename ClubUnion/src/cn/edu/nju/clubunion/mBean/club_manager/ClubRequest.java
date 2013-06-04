package cn.edu.nju.clubunion.mBean.club_manager;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

public class ClubRequest {

	private List<ARequest> applyRequests;

	public List<ARequest> getApplyRequests() {
		return applyRequests;
	}

	public void setApplyRequests(List<ARequest> applyRequests) {
		this.applyRequests = applyRequests;
	}

	public ClubRequest() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IClubService service;
		Integer clubId = CUH.getFromRequest("club_id");
		if (clubId == null) {
			fctx.addMessage(null, new FacesMessage("参数异常"));
			return;
		}

		service = (IClubService) CUH.getRemoteService("ClubService/remote");
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return;
		}

		try {
			applyRequests = service.getAllReceivedUserApplyRequests(user,
					clubId);
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
		}
	}

}
