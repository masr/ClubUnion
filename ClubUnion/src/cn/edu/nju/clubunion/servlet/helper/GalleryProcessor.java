package cn.edu.nju.clubunion.servlet.helper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.edu.nju.clubunion.abstractEntity.AGalleryImg;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubDesignService;
import cn.edu.nju.clubunion.entity.GalleryImg;
import cn.edu.nju.clubunion.helper.CUH;

public class GalleryProcessor extends AContentProcessor {
	public GalleryProcessor(String query, HttpServletRequest request,
			int containerId) {
		super(query, request, containerId);

	}

	/**
	 * 该方法接收和链接表有关的信息，需要接受clubId,blockLinkTexts,blockLinkURLs。该方法创建块并填充块，
	 * 在填充块内容之前先整合块的信息
	 * 
	 * @param request
	 */
	public void createContent() {

		Integer clubId = Integer.parseInt(CUH.getParameter(query,"clubId"));

		String[] paramURL =CUH.getParameter(query,"galleryURLs")
				.split("@!@");

		IAccountService user = (IAccountService) request.getSession(true)
				.getAttribute("user");
		IClubDesignService designService = (IClubDesignService) CUH
				.getRemoteService("ClubDesignService/remote");
		if (designService == null) {
			CUH.addServiceExceptionMessage();
			return;
		}

		List<AGalleryImg> blockImgs = new ArrayList<AGalleryImg>();
		for (String url : paramURL) {
			AGalleryImg img = new GalleryImg();
			img.setImgURL(url);
			blockImgs.add(img);
		}
		designService.createBlockImg(user, clubId, containerId, blockImgs);

	}

	/**
	 * 该方法作为已经存在的链接表被用户修改时调用
	 */
	public void editContent() {
		IAccountService user = (IAccountService) request.getSession(true)
				.getAttribute("user");
		IClubDesignService designService = (IClubDesignService) CUH
				.getRemoteService("ClubDesignService/remote");
		if (designService == null) {
			CUH.addServiceExceptionMessage();
			return;
		}
		Integer containerId = Integer.parseInt(CUH.
				getParameter(query,"containerId"));
		Integer clubId = Integer.parseInt(CUH.getParameter(query,"clubId"));
		String[] paramURL = CUH.getParameter(query,"galleryURLs")
				.split("@e@");

		List<AGalleryImg> blockImgs = new ArrayList<AGalleryImg>();
		for (String url : paramURL) {
			AGalleryImg img = new GalleryImg();
			img.setImgURL(url);
			blockImgs.add(img);
		}

		designService.createBlockImg(user, clubId, containerId, blockImgs);
	}

	@Override
	public boolean hasContentChanged() {
		return (CUH.getParameter(query,"galleryURLs") != null);
	}

}
