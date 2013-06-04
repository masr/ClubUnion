package cn.edu.nju.clubunion.sessionBean;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.businessLogicClient.ICalendarService;
import cn.edu.nju.clubunion.daoRemote.DocumentDAORemote;
import cn.edu.nju.clubunion.entity.Document;

@Stateless
public class CalendarService implements ICalendarService {
	@EJB(beanName = "DocumentDAO")
	DocumentDAORemote documentDAO;

	public List<ADocument> getNotices(Date activityDate) {
		Date startDate = null;
		Date endDate = null;
		
		startDate = activityDate;
		endDate = (Date) activityDate.clone();
		startDate.setHours(0);
		startDate.setMinutes(0);
		endDate.setHours(23);
		endDate.setMinutes(59);
		
		List<? super Document> notices;
		notices=documentDAO.getNoticesByActivityDate(startDate,endDate);
		return ((List<ADocument>) notices);
	}

	public void specifyDatabase(String s) {
      documentDAO.specifyDatabase(s);		
	}

}
