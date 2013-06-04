package cn.edu.nju.clubunion.mBean.sup;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IRequestService;
import cn.edu.nju.clubunion.businessLogicClient.ISupService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

public class SuperRequest {

	private List<ARequest> unsolvedRequests = new ArrayList<ARequest>();
	private List<ARequest> solvedRequests = new ArrayList<ARequest>();

	public SuperRequest() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		ISupService service;

		service = (ISupService) CUH.getRemoteService("SupService/remote");
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return;
		}

		try {
			unsolvedRequests = service.getUnsolvedClubCreateRequests(user);
			solvedRequests = service.getAllClubCreateRequests(user);
			solvedRequests.removeAll(unsolvedRequests);
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
		}

	}

	public String deleteRequest() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		Integer requestId = CUH.getFromRequest("id");
		Integer type = CUH.getFromRequest("type");
		IRequestService service = (IRequestService) CUH
				.getRemoteService("RequestService/remote");
		try {
			service.setRequestService(type);
			service.deleteRequset(user, requestId);
			return "success";
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			e.printStackTrace();
			return "fail";
		}

	}

	public String refuseRequest() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		Integer requestId = CUH.getFromRequest("id");
		Integer type = CUH.getFromRequest("type");
		IRequestService service = (IRequestService) CUH
				.getRemoteService("RequestService/remote");
		try {
			service.setRequestService(type);
			service.rejectRequset(user, requestId);
			return "success";
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			e.printStackTrace();
			return "fail";
		}
	}

	public String approveRequest() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		Integer requestId = CUH.getFromRequest("id");
		Integer type = CUH.getFromRequest("type");
		IRequestService service = (IRequestService) CUH
				.getRemoteService("RequestService/remote");
		try {
			service.setRequestService(type);
			service.approveRequset(user, requestId);
			return "success";
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			e.printStackTrace();
			return "fail";
		}
	}

	public List<ARequest> getUnsolvedRequests() {
		return unsolvedRequests;
	}

	public void setUnsolvedRequests(List<ARequest> unsolvedRequests) {
		this.unsolvedRequests = unsolvedRequests;
	}

	public List<ARequest> getSolvedRequests() {
		return solvedRequests;
	}

	public void setSolvedRequests(List<ARequest> solvedRequests) {
		this.solvedRequests = solvedRequests;
	}

}
