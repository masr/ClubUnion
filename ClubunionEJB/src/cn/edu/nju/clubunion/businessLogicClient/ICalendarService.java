package cn.edu.nju.clubunion.businessLogicClient;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.exception.ErrorException;
@Remote
public interface ICalendarService {
	//	根据日期获得活动通知
	List<ADocument> getNotices(Date activityDate)throws ErrorException;
	public void specifyDatabase(String s);

}
