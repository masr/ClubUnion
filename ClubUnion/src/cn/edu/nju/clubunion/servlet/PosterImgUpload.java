package cn.edu.nju.clubunion.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nju.clubunion.servlet.helper.FileSaver;

public class PosterImgUpload extends HttpServlet {
	FileSaver saver;

	/**
	 * Constructor of the object.
	 */
	public PosterImgUpload() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("poster_img_uploader_start");
		File tmpDir = null;// 初始化上传文件的临时存放目录
		File saveDir = null;// 初始化上传文件后的保存目录
		saver = new FileSaver();
		tmpDir = new File(this.getServletContext().getRealPath("/")
				+ "upload/document_upload" + File.separator + "images_tmp/");
		saveDir = new File(this.getServletContext().getRealPath("/")
				+ "upload/document_upload" + File.separator + "images/");

		String baseURL = "/ClubUnion/upload/document_upload/images/";
		PrintWriter out = response.getWriter();
		String url = baseURL + saver.saveFile(saveDir, tmpDir, request);
		out
				.print("<script>window.parent.PosterUploader.LoadPosterIMG('" + url
						+ "')</script>");
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
