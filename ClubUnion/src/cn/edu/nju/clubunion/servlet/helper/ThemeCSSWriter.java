package cn.edu.nju.clubunion.servlet.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThemeCSSWriter {
	private String jquerycss = null;

	public boolean hasRead() {
		return !(jquerycss == null);
	}

	/**
	 * 该方法接收一个文件路径并读入的css代码存入属性中。
	 * 
	 * @param request
	 */
	public void readFile(String path) {
		File file = new File(path);
		StringBuffer tmpSource = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String temp;
			while ((temp = reader.readLine()) != null) {
				tmpSource.append(temp + '\n');
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jquerycss = tmpSource.toString();
	}

	/**
	 * 该方法接收一个css中的选择符和一个主题名称，返回css样式表代码，在每一个样式之前加入选择符限定，并修改原有代码中关于文件路径的地方。
	 * 
	 * @param request
	 */
	public String getSource(String selector, String themeName) {
		if (!hasRead())
			return null;

		String source = new String(jquerycss);
		source = source.replaceAll("images/", "js/jquery-ui-1.7.2.custom/css/"
				+ themeName + "/images/");
		source = source.replaceAll("\n.ui", "\n" + selector + " .ui");
		source = source.replaceAll(", .ui", ", " + selector + " .ui");

		return source;

	}
}
