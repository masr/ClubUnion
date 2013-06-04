package cn.edu.nju.clubunion.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nju.clubunion.helper.CUH;
import cn.edu.nju.clubunion.helper.PPW;
import cn.edu.nju.clubunion.servlet.helper.AContentProcessor;
import cn.edu.nju.clubunion.servlet.helper.ContainerProcessor;
import cn.edu.nju.clubunion.servlet.helper.ProcessorFactory;

public class ContainerStyleUpload extends HttpServlet {

	private Integer clubId;
	private Integer contentType;
	private AContentProcessor acp;
	private ProcessorFactory factory;
	private ContainerProcessor cp;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	/**
	 * 此方法涉及到所有和container信息的ajax请求。不管是修改还是新建都会调用此方法。
	 * 所必需要的参数为clubId,contentType,edited或者created中一个。以及其他信息。
	 * 如果涉及到新建块，该类会委托给许多processor来创建块和添加块内容。
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("container_style_upload_start");
        System.out.println(request.getQueryString());

        String requestString=CUH.unescape(request.getQueryString());
        System.out.println(requestString);
        String requests[]=requestString.split("&!&");
        
        for(String query:requests)
        {
		clubId = Integer.parseInt(CUH.getParameter(query, "clubId"));
		contentType = Integer.parseInt(CUH.getParameter(query,"contentType"));
       
		if (clubId == null || contentType == null)
			return;
		
		cp = new ContainerProcessor(request,query);


		// 若为true表明为非创建自定义类型，可能为修改自定义类型
		if (CUH.getParameter(query,"edited") != null) {
			Integer containerId=Integer.parseInt(CUH.getParameter(query, "containerId"));
			if (contentType < PPW.DEFAULT_TYPE)// 为true表明是修改默认类型，较简单。不可删除。
			{

				cp.mergeContainer();// 修改默认类型容器信息
				return;
			}

			if (contentType > PPW.DEFAULT_TYPE)// 为true表明是修改自定义类型
			{
				if (CUH.getParameter(query,"deleted") != null)// 为true直接删掉container
				{
					cp.deleteContainer();
					return;
				}

				factory = new ProcessorFactory();// 运行到这里表明需要升级container，并向其中填入内容（如果有内容的话）
				acp = factory.createProcessor(contentType,request,query,containerId);

				if (acp.hasContentChanged())
					acp.editContent();

				cp.mergeContainer();

			}
		} else if (CUH.getParameter(query,"created") != null)// 为true表明是创建自定义类型
		{
			if (CUH.getParameter(query,"deleted") != null)// 新建又删除，什么事都不做
				return;
			
			int containerId = cp.createContainer();// 其实这里已经包括升级样式

			factory = new ProcessorFactory();// 运行到这表明需要创建一个container，并向其中填入内容（如果有内容的话）
			acp = factory.createProcessor(contentType,request,query,containerId);

			if (acp.hasContentChanged())
				acp.createContent();

		} else {
			return;
		}

	}
	}

}
