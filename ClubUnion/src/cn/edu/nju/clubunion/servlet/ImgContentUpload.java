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

import cn.edu.nju.clubunion.servlet.helper.FileSaver;

/*
 * 该类负责接收图片内容块上传的文件保存操作，返回图片在服务器上的url。仅仅针对本地文件上传图片
 */
public class ImgContentUpload extends HttpServlet {
	private FileSaver saver;

	public ImgContentUpload() {
		super();
	}

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
		System.out.println("img_content_start");
		File tmpDir = null;// 初始化上传文件的临时存放目录
		File saveDir = null;// 初始化上传文件后的保存目录
		tmpDir = new File(this.getServletContext().getRealPath("/")
				+ "upload/img_content" + File.separator + "images_tmp/");
		saveDir = new File(this.getServletContext().getRealPath("/")
				+ "upload/img_content" + File.separator + "images/");
		String url = null;
		saver = new FileSaver();
		String baseURL = "/ClubUnion/upload/img_content/images/";

		if (request.getMethod().equalsIgnoreCase("post")) {
			PrintWriter out = response.getWriter();

			url = baseURL + saver.saveFile(saveDir, tmpDir, request);
			out
					.print("<script>window.parent.UploadResponse.createNewImgContent('"
							+ url + "')</script>");
		}

	}

	public void init() throws ServletException {
		// Put your code here
	}

}
