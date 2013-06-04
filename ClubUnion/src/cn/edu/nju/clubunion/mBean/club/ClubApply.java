package cn.edu.nju.clubunion.mBean.club;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.AJoinClubRequest;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IRequestService;
import cn.edu.nju.clubunion.entity.JoinClubRequest;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;
import cn.edu.nju.clubunion.helper.PPW;

/**
 * @author godfrey
 * 
 */
public class ClubApply {
	private String realName = null;
	private Integer grade = null;
	private String institute = null;
	private String description = null;

	public ClubApply() {

	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
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
		IRequestService service;
		Integer clubId = null;
		try {
			clubId = CUH.getFromRequest("club_id");
		} catch (NumberFormatException e) {
			e.getStackTrace();
		}
		if (clubId == null) {
			fctx.addMessage(null, new FacesMessage("参数异常"));
			return "fail";
		}

		service = (IRequestService) CUH
				.getRemoteService("RequestService/remote");
		try {
			service.setRequestService(PPW.USER_APPLY_TO_CLUB_REQUEST);
		} catch (ErrorException e1) {
			e1.printStackTrace();
		}
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return "fail";
		}

		AJoinClubRequest request = new JoinClubRequest();
		request.setGrade(grade);
		request.setInstitute(institute);
		request.setDescription(description);
		request.setReceiverId(clubId);
		request.setSenderId(user.getId());

		try {
			service.submitRequest(user, request);
			return "success";
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			return "fail";
		}
	}
}
