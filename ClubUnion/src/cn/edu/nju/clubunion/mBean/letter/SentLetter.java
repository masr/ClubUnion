package cn.edu.nju.clubunion.mBean.letter;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.ALetter;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IUserService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

public class SentLetter {
	private List<ALetter> letters;

	public List<ALetter> getLetters() {
		return letters;
	}

	public void setLetters(List<ALetter> letters) {
		this.letters = letters;
	}

	public SentLetter() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IUserService service;

		service = (IUserService) CUH.getRemoteService("UserService/remote");
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return;
		}
		try {
			letters = service.getSentLetters(user);
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
		}

	}
}
