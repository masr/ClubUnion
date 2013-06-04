package cn.edu.nju.clubunion.mBean.sup;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.AClubCreateRequest;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IRequestService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

public class SingleClubCreateRequest {
	private AClubCreateRequest request;
	private int id;

	public SingleClubCreateRequest() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IRequestService service;
		IAccountService user = CUH.getAccountService();

		service = (IRequestService) CUH
				.getRemoteService("RequestService/remote");
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return;
		}

		id = (Integer) fctx.getExternalContext().getRequestMap().get("id");
		try {
			request = (AClubCreateRequest) service.getRequsetInfo(user, id);
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
		}

	}

	public String agree() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IRequestService service;
		IAccountService user = CUH.getAccountService();

		service = (IRequestService) CUH
				.getRemoteService("RequestService/remote");
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return "fail";
		}

		try {
			service.approveRequset(user, id);
			return "success";
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			return "fail";
		}
	}

	public String reject() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IRequestService service;
		IAccountService user = CUH.getAccountService();

		service = (IRequestService) CUH
				.getRemoteService("RequestService/remote");
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return "fail";
		}

		try {
			service.rejectRequset(user, id);
			return "success";
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			return "fail";
		}
	}

}
