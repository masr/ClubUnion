package cn.edu.nju.clubunion.mBean.club;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

/**
 * @author godfrey
 * 
 */
public class ClubDocument {
	private List<ADocument> notices;
	private List<ADocument> publicDocuments;
	private List<ADocument> privateDocuments;

	public List<ADocument> getNotices() {
		return notices;
	}

	public void setNotices(List<ADocument> notices) {
		this.notices = notices;

	}

	public List<ADocument> getPublicDocuments() {
		return publicDocuments;
	}

	public void setPublicDocuments(List<ADocument> publicDocuments) {
		this.publicDocuments = publicDocuments;
	}

	public List<ADocument> getPrivateDocuments() {
		return privateDocuments;
	}

	public void setPrivateDocuments(List<ADocument> privateDocuments) {
		this.privateDocuments = privateDocuments;
	}

	public ClubDocument() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IClubService clubService;
		Integer clubId = CUH.getFromRequest("club_id");
		if (clubId == null) {
			fctx.addMessage(null, new FacesMessage("参数异常"));
			return;
		}

		clubService = (IClubService) CUH.getRemoteService("ClubService/remote");
		if (clubService == null) {
			CUH.addServiceExceptionMessage();
			return;
		}

		try {
			notices = CUH.reverse(clubService.getNotices(user, clubId));
			publicDocuments = CUH.reverse(clubService.getPublicDocuments(user,
					clubId));
			privateDocuments = CUH.reverse(clubService.getPrivateDocuments(
					user, clubId));
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
		}
	}

}
