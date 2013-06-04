package cn.edu.nju.clubunion.servlet.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

public class FileSaver {

	/**
	 * 该方法接收一个含有文件流的request，并存入saveDir目录中，返回一个随机的文件名。
	 */
	public String saveFile(File saveDir, File tmpDir, HttpServletRequest request) {
		String fileName = null;
		try {
			if (ServletFileUpload.isMultipartContent(request)) {

				DiskFileItemFactory dff = new DiskFileItemFactory();// 创建该对象
				dff.setRepository(tmpDir);// 指定上传文件的临时目录
				dff.setSizeThreshold(1024000);// 指定在内存中缓存数据大小,单位为byte
				ServletFileUpload sfu = new ServletFileUpload(dff);// 创建该对象
				sfu.setFileSizeMax(5000000);// 指定单个上传文件的最大尺寸
				sfu.setSizeMax(10000000);// 指定一次上传多个文件的总尺寸
				FileItemIterator fii = sfu.getItemIterator(request);// 解析request
																	// 请求,并返回FileItemIterator集合
				while (fii.hasNext()) {
					FileItemStream fis = fii.next();// 从集合中获得一个文件流
					if (!fis.isFormField() && fis.getName().length() > 0) {// 过滤掉表单中非文件域
						fileName = (new Date()).toString()
								+ (int) (Math.random() * 1000)
								+ fis.getName().substring(
										fis.getName().lastIndexOf("."));
						fileName = fileName.replace(" ", "");
						fileName = fileName.replace(":", "");
						BufferedInputStream in = new BufferedInputStream(fis
								.openStream());// 获得文件输入流
						BufferedOutputStream out2 = new BufferedOutputStream(
								new FileOutputStream(new File(saveDir
										+ File.separator + fileName)));// 获得文件输出流
						Streams.copy(in, out2, true);// 开始把文件写到你指定的上传文件夹

					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileName;

	}
}
