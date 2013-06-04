package cn.edu.nju.clubunion.mBean.platform;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.AClub;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IUserService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

public class AllClub {
	private List<AClub> managedClubs;
	private List<AClub> joinedClubs;
	private List<AClub> caredClubs;
	private List<AClub> normalClubs;

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

	public List<AClub> getNormalClubs() {
		return normalClubs;
	}

	public void setNormalClubs(List<AClub> normalClubs) {
		this.normalClubs = normalClubs;
	}

	public AllClub() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IUserService service;

		service = (IUserService) CUH.getRemoteService("UserService/remote");
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return;
		}
		try {
			if (user.loggedIn()) {
				managedClubs = service.getManagedClubs(user, user.getId());
				joinedClubs = service.getJoinedClubs(user, user.getId());
				caredClubs = service.getCaredClubs(user, user.getId());
				caredClubs.removeAll(joinedClubs);
				joinedClubs.removeAll(managedClubs);
			}
			normalClubs = service.getNormalClubs(user, user.getId());

		} catch (ErrorException e) {

			fctx.addMessage(null, new FacesMessage(e.getReason()));
		}

	}
}
