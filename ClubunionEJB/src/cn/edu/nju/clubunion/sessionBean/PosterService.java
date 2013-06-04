package cn.edu.nju.clubunion.sessionBean;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import cn.edu.nju.clubunion.abstractEntity.APoster;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IPosterService;
import cn.edu.nju.clubunion.daoRemote.PosterDAORemote;
import cn.edu.nju.clubunion.entity.Poster;
import cn.edu.nju.clubunion.exception.ErrorException;

@Stateless

public class PosterService implements IPosterService{

	@EJB(beanName = "PosterDAO")
	PosterDAORemote posterDAO;
	
	@Interceptors(cn.edu.nju.clubunion.interceptors.ClubManagerInterceptor.class)
	public void addNoticePoster(IAccountService user,int clubId, APoster poster,
			int documentId) throws ErrorException{
		posterDAO.addPoster((Poster)poster, documentId);
	}

	public List<APoster> getPosters(IAccountService user, Date date) throws ErrorException{
		Date startDate = null;
		Date endDate = null;
		
		startDate = date;
		startDate.setHours(0);
		startDate.setMinutes(0);
		endDate=(Date) startDate.clone();
		endDate.setHours(23);
		endDate.setMinutes(59);
		
		List<? super Poster> posters;
		posters = posterDAO.getPostersByDate(startDate,endDate);
		return (List<APoster>)posters;
	}

	public void specifyDatabase(String s) {
		posterDAO.specifyDatabase(s);
		
	}

}
