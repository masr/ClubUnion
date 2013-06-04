package cn.edu.nju.clubunion.mBean.letter;

import java.util.List;

import javax.faces.component.UIData;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.ALetter;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.ILetterService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

public class LetterDelete {

	@SuppressWarnings("unchecked")
	public String delete() {
		ILetterService letterService;
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();

		letterService = (ILetterService) CUH
				.getRemoteService("LetterService/remote");
		if (letterService == null)
			CUH.addServiceExceptionMessage();

		UIViewRoot root = fctx.getViewRoot();
		UIData tableData = (UIData) root.findComponent("letter_form")
				.findComponent("letter_table");
		List<ALetter> letters = (List<ALetter>) tableData.getValue();
		try {
			for (ALetter l : letters) {
				if (l.isSelected())
					letterService.deleteLetter(user, l.getId());

			}
		} catch (ErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

}
