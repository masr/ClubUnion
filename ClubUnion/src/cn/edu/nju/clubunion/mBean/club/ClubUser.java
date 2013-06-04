package cn.edu.nju.clubunion.mBean.club;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.AUser;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

public class ClubUser {
	private List<AUser> managers = null;
	private List<AUser> members = null;

	public List<AUser> getManagers() {
		return managers;
	}

	public void setManagers(List<AUser> managers) {
		this.managers = managers;
	}

	public List<AUser> getMembers() {
		return members;
	}

	public void setMembers(List<AUser> members) {
		this.members = members;
	}

	public ClubUser() {
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
			managers = service.getManagers(user, clubId);
			members = service.getMembers(user, clubId);
			members.removeAll(managers);
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
		}
	}

}
