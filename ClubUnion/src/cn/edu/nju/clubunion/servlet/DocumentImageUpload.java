package cn.edu.nju.clubunion.servlet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.faces.context.FacesContext;
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

/**
 * 
 * @author maosuhan
 */
public class DocumentImageUpload extends HttpServlet implements
		javax.servlet.Servlet {
	private FileSaver saver;

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */

	@Override
	public void init() throws ServletException {
		/*
		 * 对上传文件夹和临时文件夹进行初始化
		 */
		super.init();

	}

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("document_img_upload_start");
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
		out.print("<script>window.parent.LoadIMG('" + url + "')</script>");

	}

	// <editor-fold defaultstate="collapsed"
	// desc="HttpServlet 方法。单击左侧的 + 号以编辑代码。">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 * 
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
