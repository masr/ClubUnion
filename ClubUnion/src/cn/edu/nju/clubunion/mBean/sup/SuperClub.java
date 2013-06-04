package cn.edu.nju.clubunion.mBean.sup;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.AClub;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

public class SuperClub {
	private List<AClub> clubs;

	public List<AClub> getClubs() {
		return clubs;
	}

	public void setClubs(List<AClub> clubs) {
		this.clubs = clubs;
	}

	public SuperClub() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IClubService service;

		service = (IClubService) CUH.getRemoteService("UserService/remote");
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return;
		}

		try {
			clubs = service.getAllClubs(user);
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
		}

	}
}
