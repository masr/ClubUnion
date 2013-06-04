package cn.edu.nju.clubunion.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubDesignService;
import cn.edu.nju.clubunion.helper.CUH;
import cn.edu.nju.clubunion.servlet.helper.FileSaver;

public class LogoUpload extends HttpServlet {
	private FileSaver saver;

	/**
	 * Constructor of the object.
	 */
	public LogoUpload() {
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
		System.out.println("logo_upload_start");
		String url = null;
		File tmpDir = null;// 初始化上传文件的临时存放目录
		File saveDir = null;// 初始化上传文件后的保存目录

		if (request.getMethod().equalsIgnoreCase("post")) {
			tmpDir = new File(this.getServletContext().getRealPath("/")
					+ "upload/logo_upload/tmp/");
			saveDir = new File(this.getServletContext().getRealPath("/")
					+ "upload/logo_upload/save");
			saver = new FileSaver();
			String baseURL = "/ClubUnion/upload/logo_upload/save/";

			PrintWriter out = response.getWriter();
			url = baseURL + saver.saveFile(saveDir, tmpDir, request);


			out.print("<script>window.parent.UploadResponse.LoadLogoIMG('"
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
