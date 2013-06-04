package cn.edu.nju.clubunion.mBean.club;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.AMessage;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

public class ClubMessage {
	private List<AMessage> messages = null;

	public List<AMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<AMessage> messages) {
		this.messages = messages;
	}

	public ClubMessage() {
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
			messages = CUH.reverse(service.getMessages(user, clubId));
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
		}

	}

}
