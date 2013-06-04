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

public class LeftBackImgUpload extends HttpServlet {

	private FileSaver saver;

	public LeftBackImgUpload() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("left_back_img_upload_start");
		File tmpDir;
		File saveDir;
		String url = null;

		if (request.getMethod().equalsIgnoreCase("post")) {
			saver = new FileSaver();
			tmpDir = new File(this.getServletContext().getRealPath("/")
					+ "upload/back_upload/tmp/");
			saveDir = new File(this.getServletContext().getRealPath("/")
					+ "upload/back_upload/save");

			String baseURL = "/ClubUnion/upload/back_upload/save/";

			PrintWriter out = response.getWriter();
			url = baseURL + saver.saveFile(saveDir, tmpDir, request);
			out.print("<script>window.parent.UploadResponse.LoadLeftBackIMG('"
					+ url + "')</script>");

		}

	}

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
