package cn.edu.nju.clubunion.servlet.helper;

import javax.servlet.http.HttpServletRequest;

import cn.edu.nju.clubunion.helper.PPW;

public class ProcessorFactory {
	public AContentProcessor createProcessor(int type,HttpServletRequest request,String query,int containerId) {
		if (type == PPW.GALLERY_TYPE)
			return new GalleryProcessor(query, request, containerId);
		if (type == PPW.TEXT_TYPE)
			return new TextContentProcessor(query, request, containerId);
		if (type == PPW.IMG_TYPE)
			return new ImgContentProcessor( query, request, containerId);
		return null;
	}
}
