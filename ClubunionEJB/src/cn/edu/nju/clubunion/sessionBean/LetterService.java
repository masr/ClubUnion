package cn.edu.nju.clubunion.sessionBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import cn.edu.nju.clubunion.abstractEntity.ALetter;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.ILetterService;
import cn.edu.nju.clubunion.daoRemote.LetterDAORemote;
import cn.edu.nju.clubunion.entity.Letter;
import cn.edu.nju.clubunion.exception.ErrorException;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Stateless
@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
public class LetterService implements ILetterService {
	
	@EJB(beanName = "LetterDAO")
	LetterDAORemote letterDAO;

	public void deleteLetter(IAccountService user, int letterId)
			throws ErrorException {
		if (!letterDAO.canAccessLetter(letterId, user.getId()))
			throw new ErrorException("您没有权限删除信");
		letterDAO.deleteLetter(letterId);
	}

	public ALetter getLetterInfo(IAccountService user, int letterId)
			throws ErrorException {
		if (!letterDAO.canAccessLetter(letterId, user.getId()))
			throw new ErrorException("您没有权限查看信");
		Letter letter = letterDAO.getLetterByID(letterId);
		if (letter == null)
			throw new ErrorException("该信不存在");

		return letter;
	}

	public int sendLetter(IAccountService user, ALetter letterInfo)
			throws ErrorException {
		Letter l = (Letter) letterInfo;
		int id = letterDAO.insertLetter(l);
		return id;
	}

	public void specifyDatabase(String s) {
		letterDAO.specifyDatabase(s);
	}

}
