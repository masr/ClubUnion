package cn.edu.nju.clubunion.mBean.letter;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.ALetter;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.ILetterService;
import cn.edu.nju.clubunion.entity.Letter;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

/**
 * @author godfrey
 * 
 */
public class LetterCreate {
	private String title = null;
	private String content = null;
	private Integer toPersonId = null;
	private String toClubId = null;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getToPersonId() {
		return toPersonId;
	}

	public void setToPersonId(Integer toPersonId) {
		this.toPersonId = toPersonId;
	}

	public String getToClubId() {
		return toClubId;
	}

	public void setToClubId(String toClubId) {
		this.toClubId = toClubId;
	}

	public String send() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		ILetterService service;

		service = (ILetterService) CUH.getRemoteService("LetterService/remote");
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return "fail";
		}
		ALetter letter = new Letter();
		letter.setTitle(title);
		letter.setContent(content);
		letter.setReceiverId(toPersonId);
		letter.setSenderId(user.getId());

		try {
			service.sendLetter(user, letter);
			return "success";
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			return "fail";
		}
	}
}
