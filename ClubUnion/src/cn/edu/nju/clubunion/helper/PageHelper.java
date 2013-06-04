package cn.edu.nju.clubunion.helper;

import javax.faces.context.FacesContext;

public class PageHelper {

	public String getBaseURL() {
		String baseURL;
		FacesContext fctx = FacesContext.getCurrentInstance();
		String path = fctx.getExternalContext().getRequestContextPath();
		String host = fctx.getExternalContext().getRequestHeaderMap().get(
				"Host");
		baseURL = "http://" + host + path + "/";
		return baseURL;
	}

}
