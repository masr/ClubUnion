package cn.edu.nju.clubunion.businessLogicClient;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.abstractEntity.APoster;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.sessionBean.AccountService;

@Remote
public interface IPosterService {
	public List<APoster> getPosters(IAccountService user, Date date)throws ErrorException;

	public void addNoticePoster(IAccountService user,int clubId, APoster poster,
			int documentId)throws ErrorException;
	
	public void specifyDatabase(String s);

}
