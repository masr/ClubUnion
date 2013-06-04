package cn.edu.nju.clubunion.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.nju.clubunion.servlet.helper.ThemeCSSWriter;

public class ContainerThemeChange extends HttpServlet {
	private ThemeCSSWriter writer;

	/**
	 * Constructor of the object.
	 */
	public ContainerThemeChange() {
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

		this.doPost(request, response);
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
		System.out.println("container_theme_change_start");
		System.out.println(request.getQueryString());
		writer = new ThemeCSSWriter();
		String themeName = request.getParameter("theme_name");
		String selectorId = request.getParameter("selector_id");
		String selectorClass = request.getParameter("selector_class");
		if (themeName == null || (selectorId == null && selectorClass == null))
			return;

		String selector = (selectorId != null) ? "#" + selectorId : "."
				+ selectorClass;

		writer.readFile(this.getServletContext().getRealPath("/")
				+ "js/jquery-ui-1.7.2.custom/css/" + themeName
				+ "/jquery-ui.css");
		String source = writer.getSource(selector, themeName);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(source);
		out.flush();
		out.close();
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
