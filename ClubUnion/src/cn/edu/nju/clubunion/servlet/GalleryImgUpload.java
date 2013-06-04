package cn.edu.nju.clubunion.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nju.clubunion.servlet.helper.FileSaver;

public class GalleryImgUpload extends HttpServlet {

	private FileSaver saver;

	/**
	 * Constructor of the object.
	 */

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
		System.out.println("gallery_img_upload_start");
		String url = null;
		File tmpDir = null;// 初始化上传文件的临时存放目录
		File saveDir = null;// 初始化上传文件后的保存目录

		if (request.getMethod().equalsIgnoreCase("post")) {
			tmpDir = new File(this.getServletContext().getRealPath("/")
					+ "upload/block_img/tmp/");
			saveDir = new File(this.getServletContext().getRealPath("/")
					+ "upload/block_img/save");
			saver = new FileSaver();
			String baseURL = "/ClubUnion/upload/block_img/save/";

			PrintWriter out = response.getWriter();

			url = baseURL + saver.saveFile(saveDir, tmpDir, request);
			out
					.print("<script>window.parent.UploadResponse.insertNewGalleryImg('"
							+ url + "')</script>");

		}
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
