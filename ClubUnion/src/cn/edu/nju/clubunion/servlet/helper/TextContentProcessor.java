package cn.edu.nju.clubunion.servlet.helper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nju.clubunion.abstractEntity.AContainerBlock;
import cn.edu.nju.clubunion.abstractEntity.ATextContent;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubDesignService;
import cn.edu.nju.clubunion.entity.ContainerBlock;
import cn.edu.nju.clubunion.entity.TextContent;
import cn.edu.nju.clubunion.helper.CUH;
import cn.edu.nju.clubunion.helper.PPW;

public class TextContentProcessor extends AContentProcessor {

	public TextContentProcessor(String query, HttpServletRequest request,
			int containerId) {
		super(query, request, containerId);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 该方法接收和新建文字段落块有关的信息，需要接受clubId,imgURL。该方法创建块并填充块，在填充块内容之前先整合块的信息
	 * 
	 * @param request
	 */
	public void createContent() {
		Integer clubId = Integer.parseInt(CUH.getParameter(query,"clubId"));
		String[] styles = { "text" };
		IAccountService user = (IAccountService) request.getSession(true)
				.getAttribute("user");

		IClubDesignService designService = (IClubDesignService) CUH
				.getRemoteService("ClubDesignService/remote");
		if (designService == null) {
			CUH.addServiceExceptionMessage();
			return;
		}

		ATextContent content = new TextContent();
		content.setContainerId(containerId);
		for (String s : styles) {
			if (this.getRequestAvailable( s)) {
				String param = CUH.getParameter(query,s);
				
				this.setStyleAttribute( s, param, content);
			}
		}
		designService.createTextContent(user, clubId, containerId, content);

	}

	public void editContent() {
		Integer clubId = Integer.parseInt(CUH.getParameter(query,"clubId"));
		Integer containerId = Integer.parseInt(CUH
				.getParameter(query,"containerId"));
		String[] styles = { "text" };
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
		ATextContent content = container.getTextContent();

		for (String s : styles) {
			if (this.getRequestAvailable( s)) {
				String param = CUH.getParameter(query,s);
				
				this.setStyleAttribute( s, param, content);
			}
		}
		designService.mergeTextContent(user, clubId, content);
	}

	public void setStyleAttribute( String s,
			String c, ATextContent content) {
		if (s.equals("text"))
			content.setText(c);

	}

	

	@Override
	public boolean hasContentChanged() {
		return (CUH.getParameter(query,"text") != null);
	}


}
