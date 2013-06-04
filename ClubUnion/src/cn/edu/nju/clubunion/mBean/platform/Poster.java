package cn.edu.nju.clubunion.mBean.platform;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.APoster;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IPosterService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

/**
 * @author godfrey
 * 
 */
public class Poster {
	private List<APoster> posters;

	public Poster() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IPosterService posterService;

		posterService = (IPosterService) CUH
				.getRemoteService("PosterService/remote");
		if (posterService == null) {
			CUH.addServiceExceptionMessage();
			return;
		}
		try {
			Date date = new Date();
			posters = posterService.getPosters(user, date);
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
		}

	}

	public List<APoster> getPosters() {
		return posters;
	}

	public void setPosters(List<APoster> posters) {
		this.posters = posters;
	}

}
