package cn.edu.nju.clubunion.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nju.clubunion.abstractEntity.AClubStyle;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubDesignService;
import cn.edu.nju.clubunion.entity.ClubStyle;
import cn.edu.nju.clubunion.helper.CUH;

public class PageStyleUpload extends HttpServlet {
	private AClubStyle clubStyle;

	/**
	 * Constructor of the object.
	 */
	public PageStyleUpload() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("page_style_upload_start");
		System.out.println(request.getQueryString());
		String styles[] = { "backColor", "fontSize", "fontColor", "fontFamily",
				"borderColor", "borderSolid", "mainBackColor", "linkColor",
				"mainBackURL", "logoURL", "topBackURL", "rightBackURL",
				"leftBackURL", "themeName", "titleColor", "titleFontFamily",
				"topBackColor", "topBackRepeat", "leftBackRepeat",
				"rightBackRepeat", "mainBackRepeat" };

		IAccountService user = (IAccountService) request.getSession(true)
				.getAttribute("user");
		IClubDesignService designService;
		Integer clubId = Integer.parseInt(request.getParameter("clubId"));

		if (clubId == null)
			return;

		designService = (IClubDesignService) CUH
				.getRemoteService("ClubDesignService/remote");
		if (designService == null) {
			CUH.addServiceExceptionMessage();
			return;
		}
		clubStyle = designService.getClubStyle(user, clubId);
		for (String s : styles) {
			if (this.getRequestAvailable(request, s)) {
				String param = request.getParameter(s);
				this.setStyleAttribute(request, s, param);
			}
		}

		designService.updateClubStyle(user, clubId, clubStyle);
	}

	private boolean getRequestAvailable(HttpServletRequest request, String s) {
		return request.getParameter(s) == null ? false : true;
	}

	private void setStyleAttribute(HttpServletRequest request, String s,
			String c) {
		if (s.equals("backColor"))
			clubStyle.setBackColor("#" + c);
		if (s.equals("fontSize"))
			clubStyle.setFontSize(c);
		if (s.equals("fontColor"))
			clubStyle.setFontColor("#" + c);
		if (s.equals("fontFamily"))
			clubStyle.setFontFamily(c);
		if (s.equals("borderColor"))
			clubStyle.setBorderColor("#" + c);
		if (s.equals("borderSolid"))
			clubStyle.setBorderSolid(c);
		if (s.equals("linkColor"))
			clubStyle.setLinkColor("#" + c);
		if (s.equals("mainBackColor"))
			clubStyle.setMainBackColor("#" + c);
		if (s.equals("mainBackURL"))
			clubStyle.setMainBackURL(CUH.unescape(c));
		if (s.equals("topBackURL"))
			clubStyle.setTopBackURL(CUH.unescape(c));
		if (s.equals("logoURL"))
			clubStyle.setLogoURL(CUH.unescape(c));
		if (s.equals("leftBackURL"))
			clubStyle.setLeftBackURL(CUH.unescape(c));
		if (s.equals("rightBackURL"))
			clubStyle.setRightBackURL(CUH.unescape(c));
		if (s.equals("themeName"))
			clubStyle.setThemeName(c);
		if (s.equals("titleColor"))
			clubStyle.setTitleColor("#" + c);
		if (s.equals("titleFontFamily"))
			clubStyle.setTitleFontFamily(c);
		if (s.equals("topBackColor"))
			clubStyle.setTopBackColor("#" + c);
		if (s.equals("leftBackRepeat"))
			clubStyle.setLeftBackRepeat(c);
		if (s.equals("rightBackRepeat"))
			clubStyle.setRightBackRepeat(c);
		if (s.equals("topBackRepeat"))
			clubStyle.setTopBackRepeat(c);
		if (s.equals("mainBackRepeat"))
			clubStyle.setMainBackRepeat(c);

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
