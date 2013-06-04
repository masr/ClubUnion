package cn.edu.nju.clubunion.mBean.club;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.AClub;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;
import cn.edu.nju.clubunion.helper.PPW;

public class ClubHelp {

	private String clubName;
	private boolean clubManager = false;
	private boolean visitor = false;

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public boolean isClubManager() {
		return clubManager;
	}

	public void setClubManager(boolean clubManager) {
		this.clubManager = clubManager;
	}

	public boolean isVisitor() {
		return visitor;
	}

	public void setVisitor(boolean visitor) {
		this.visitor = visitor;
	}

	public ClubHelp() {
		IAccountService user = CUH.getAccountService();
		IClubService clubService;
		Integer clubId = CUH.getFromRequest("club_id");
		FacesContext fctx = FacesContext.getCurrentInstance();
		AClub clubInfo;
		if (clubId == null)
			return;
		if (user.getPermissionInClub(clubId) == PPW.CLUB_MANAGER)
			this.clubManager = true;
		if ((user.getPermissionInClub(clubId)) == PPW.VISITOR)
			this.visitor = true;

		clubService = (IClubService) CUH.getRemoteService("ClubService/remote");
		try {
			clubInfo = clubService.getClubInfo(user, clubId);
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			e.printStackTrace();
			return;
		}
		this.clubName = clubInfo.getName();

	}

}
