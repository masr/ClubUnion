package cn.edu.nju.clubunion.servlet.helper;

import javax.servlet.http.HttpServletRequest;

import cn.edu.nju.clubunion.abstractEntity.AContainerBlock;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubDesignService;
import cn.edu.nju.clubunion.entity.ContainerBlock;
import cn.edu.nju.clubunion.helper.CUH;

public class ContainerProcessor {
	private String styles[] = { "fontSize", "fontColor", "fontFamily", "borderColor",
			"borderSolid", "x", "y", "w", "h", "contentType", "linkColor",
			"title", "titleFontFamily", "titleColor", "themeName" };;
   private String query;
   private HttpServletRequest request;
	/**
	 * 
	 * 此方法需要调用request中所有和块样式信息相关的参数，并调用业务逻辑整合块的样式信息。
	 */
			public ContainerProcessor(HttpServletRequest request,String query){
		       this.request=request;
		       this.query=query;
			}
	public void mergeContainer() {
		Integer clubId = Integer.parseInt(CUH.getParameter(query,"clubId"));
		int containerId = Integer.parseInt(CUH.getParameter(query,"containerId")); 

		IAccountService user = (IAccountService) request.getSession(true)
				.getAttribute("user");

		IClubDesignService designService = (IClubDesignService) CUH
				.getRemoteService("ClubDesignService/remote");
		if (designService == null) {
			CUH.addServiceExceptionMessage();
			return;
		}

		AContainerBlock container = designService.getSingleContainerBlock(user,
				clubId, containerId);

		for (String s : styles) {
			if (this.getRequestAvailable(s)) {
				String param = CUH.getParameter(query,s);
				this.setStyleAttribute( s, param, container);
			}
		}

		designService.updateContainerBlock(user, clubId, container);
	}

	private boolean getRequestAvailable( String s) {

		return CUH.getParameter(query,s) == null ? false : true;
	}

	private void setStyleAttribute( String s,
			String c, AContainerBlock container) {

		if (s.equals("fontSize"))
			container.setFontSize(c);
		if (s.equals("fontColor"))
			container.setFontColor("#" + c);
		if (s.equals("fontFamily"))
			container.setFontFamily(c);
		if (s.equals("borderColor"))
			container.setBorderColor("#" + c);
		if (s.equals("borderSolid"))
			container.setBorderSolid(c);
		if (s.equals("x"))
			container.setX(c);
		if (s.equals("y"))
			container.setY(c);
		if (s.equals("w"))
			container.setW(c);
		if (s.equals("h"))
			container.setH(c);
		if (s.equals("linkColor"))
			container.setLinkColor("#" + c);
		if (s.equals("contentType"))
			container.setContentType(Integer.parseInt(c));
		if (s.equals("title"))
			container.setTitle(CUH.unescape(c));
		if (s.equals("titleFontFamily"))
			container.setTitleFontFamily(c);
		if (s.equals("titleColor"))
			container.setTitleColor("#" + c);
		if (s.equals("themeName"))
			container.setThemeName(c);

	}

	/**
	 * 删除整个块，包括里面的内容
	 */
	public void deleteContainer() {
		Integer clubId = Integer.parseInt(CUH.getParameter(query,"clubId"));
		Integer containerId = Integer.parseInt(CUH
				.getParameter(query,"containerId"));
		IAccountService user = (IAccountService) request.getSession(true)
				.getAttribute("user");

		IClubDesignService designService = (IClubDesignService) CUH
				.getRemoteService("ClubDesignService/remote");
		if (designService == null) {
			CUH.addServiceExceptionMessage();
			return;
		}

		designService.deleteContainer(user, clubId, containerId);
	}

	public Integer createContainer() {
		Integer clubId = Integer.parseInt(CUH.getParameter(query,"clubId"));
		IAccountService user = (IAccountService) request.getSession(true)
				.getAttribute("user");

		IClubDesignService designService = (IClubDesignService) CUH
				.getRemoteService("ClubDesignService/remote");
		if (designService == null) {
			CUH.addServiceExceptionMessage();
			return null;
		}
		AContainerBlock container = new ContainerBlock();

		for (String s : styles) {
			if (this.getRequestAvailable( s)) {
				String param = CUH.getParameter(query,s);
				this.setStyleAttribute( s, param, container);
			}
		}
		return designService.createContainerBlock(user, clubId, container);

	}
}
