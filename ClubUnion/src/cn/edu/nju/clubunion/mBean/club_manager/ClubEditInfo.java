package cn.edu.nju.clubunion.mBean.club_manager;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.AClub;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubService;
import cn.edu.nju.clubunion.entity.Club;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

public class ClubEditInfo {
	private String clubName;
	private String description;

	public ClubEditInfo() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IClubService clubService;
		Integer clubId = CUH.getFromRequest("club_id");
		if (clubId == null) {
			fctx.addMessage(null, new FacesMessage("参数异常"));
			return;
		}

		clubService = (IClubService) CUH.getRemoteService("ClubService/remote");
		AClub club = new Club();
		try {
			club = clubService.getClubInfo(user, clubId);
		} catch (ErrorException e1) {
			fctx.addMessage(null, new FacesMessage(e1.getReason()));
			e1.printStackTrace();
		}

		this.description = club.getDescription();
		this.clubName = club.getName();
	}

	public String edit() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IClubService clubService;
		Integer clubId = CUH.getFromRequest("club_id");
		if (clubId == null) {
			fctx.addMessage(null, new FacesMessage("参数异常"));
			return "fail";
		}

		clubService = (IClubService) CUH.getRemoteService("ClubService/remote");

		if (clubService == null) {
			CUH.addServiceExceptionMessage();
			return "fail";
		}

		AClub club;
		try {
			club = clubService.getClubInfo(user, clubId);
		} catch (ErrorException e1) {
			fctx.addMessage(null, new FacesMessage(e1.getReason()));
			e1.printStackTrace();
			return "fail";
		}
		club.setDescription(club.getDescription());
		club.setName(club.getName());

		try {
			clubService.editClubInfo(user, clubId, club);
			return "success";
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			return "fail";
		}

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

}
