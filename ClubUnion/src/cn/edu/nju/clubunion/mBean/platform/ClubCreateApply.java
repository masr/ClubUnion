package cn.edu.nju.clubunion.mBean.platform;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.AClubCreateRequest;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IRequestService;
import cn.edu.nju.clubunion.entity.ClubCreateRequest;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;
import cn.edu.nju.clubunion.helper.PPW;

public class ClubCreateApply {

	private String clubName = null;
	private String description = null;
	private String institute = null;
	private String realName = null;
	private String grade = null;

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String apply() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IRequestService requestService;

		requestService = (IRequestService) CUH
				.getRemoteService("RequestService/remote");
		if (requestService == null) {
			CUH.addServiceExceptionMessage();
			return "fail";
		}
		try {
			requestService.setRequestService(PPW.CREATE_CLUB_REQUEST_TYPE);
		} catch (ErrorException e1) {
			fctx.addMessage(null, new FacesMessage(e1.getReason()));
			e1.printStackTrace();
		}

		AClubCreateRequest request = new ClubCreateRequest();
		request.setClubName(clubName);
		request.setDescription(description);
		request.setInstitute(institute);
		request.setGrade(grade);
		request.setSenderId(user.getId());
		request.setCreatedAt(new Date());
		try {
			requestService.submitRequest(user, request);
			return "success";
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			return "fail";
		}
	}
}
