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
public class AttachUpload extends HttpServlet {

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
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("attach_upload_start");
		File tmpDir, saveDir;
		tmpDir = new File(this.getServletContext().getRealPath("/")
				+ "upload/document_upload" + File.separator + "attach_tmp/");
		saveDir = new File(this.getServletContext().getRealPath("/")
				+ "upload/document_upload" + File.separator + "attach/");
		saver = new FileSaver();
		saver.saveFile(saveDir, tmpDir, request);
		String baseURL = "/ClubUnion/upload/document_upload/attach/";
		PrintWriter out = response.getWriter();
		String url = baseURL + saver.saveFile(saveDir, tmpDir, request);
		out.print("<script>window.parent.LoadAttach('" + url + "')</script>");
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
