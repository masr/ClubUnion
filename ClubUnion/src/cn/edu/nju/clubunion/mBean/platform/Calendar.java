package cn.edu.nju.clubunion.mBean.platform;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.businessLogicClient.ICalendarService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

public class Calendar {
	private List<ADocument> notices;
	private Date date;

	public List<ADocument> getNotices() {
		return notices;
	}

	public void setNotices(List<ADocument> notices) {
		this.notices = notices;
	}

	public Calendar() {

		FacesContext fctx = FacesContext.getCurrentInstance();
		ICalendarService calendarService;

		calendarService = (ICalendarService) CUH
				.getRemoteService("CalendarService/remote");
		if (calendarService == null) {
			CUH.addServiceExceptionMessage();
			return;
		}

		String dateString = CUH.getStringFromRequest("date");

		if (dateString == null)
			return;
		String time[] = dateString.split("/");
		int month = Integer.parseInt(time[0]);
		int day = Integer.parseInt(time[1]);
		int year = Integer.parseInt(time[2]);
		date = new Date();

		date.setMonth(month - 1);
		date.setYear(year - 1900);
		date.setDate(day);
		try {
			notices = calendarService.getNotices(date);
			notices = CUH.reverse(notices);
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			return;
		}

	}

}
