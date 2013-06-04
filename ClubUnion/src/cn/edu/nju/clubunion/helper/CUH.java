package cn.edu.nju.clubunion.helper;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.mBean.account.UserBean;

public class CUH {

	public static IAccountService getAccountService() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = (IAccountService) fctx.getApplication()
				.evaluateExpressionGet(fctx, "#{UserBacking.accountService}",
						IAccountService.class);
		return user;
	}

	public static UserBean getUserBackingBean() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		UserBean user = (UserBean) fctx.getApplication().evaluateExpressionGet(
				fctx, "#{UserBacking}", UserBean.class);
		return user;
	}

	public static Object getRemoteService(String serviceName) {
		try {
			Context ctx = new InitialContext();
			Object object = ctx.lookup(serviceName);
			return object;
		} catch (NamingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void addServiceExceptionMessage() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		fctx.addMessage(null, new FacesMessage("服务出现异常"));
	}

	public static Integer getFromRequest(String s) {
		FacesContext fctx = FacesContext.getCurrentInstance();

		String clubIdString = fctx.getExternalContext()
				.getRequestParameterMap().get(s);
		Integer clubId;
		try {
			clubId = (clubIdString == null) ? null : Integer
					.parseInt(clubIdString);
		} catch (Exception e) {
			return null;
		}
		return clubId;
	}

	public static String getStringFromRequest(String s) {
		FacesContext fctx = FacesContext.getCurrentInstance();
		String string = fctx.getExternalContext().getRequestParameterMap().get(
				s);
		return string;

	}

	public static Object getValueBinding(String s, Class c) {
		FacesContext fctx = FacesContext.getCurrentInstance();
		Object o = fctx.getApplication().evaluateExpressionGet(fctx, s, c);
		return o;
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	
	  public static String getParameter(String Line,String parameter)
	    {
		  
	        String result=null;
	        int para=Line.indexOf(parameter+"=");
	        if (para<0)
	        	return null;
	        
	        int middle =Line.indexOf("&",para+parameter.length()+1);
	        if(middle<=0)
	        {
	            
	            result=Line.substring(para+parameter.length()+1,Line.length());
	        }
	        else
	        {
	            result=Line.substring(para+parameter.length()+1,middle);
	        }
	        return result;
	    }

	public static <T> List<T> reverse(List<T> list) {
		List<T> newList = new ArrayList<T>();
		for (int i = list.size() - 1; i >= 0; i--) {
			newList.add(list.get(i));
		}
		return newList;
	}

}
