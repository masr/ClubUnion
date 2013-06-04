package cn.edu.nju.clubunion.abstractEntity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AClubStyle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	
	@Column(name = "font_size")
	private String fontSize;
	@Column(name = "font_color")
	private String fontColor;
	@Column(name = "font_family")
	private String fontFamily;
	@Column(name = "logo_url")
	private String logoURL;
	@Column(name = "back_color")
	private String backColor;
	@Column(name = "club_id", insertable = false, updatable = false)
	private Integer clubId;
	@Column(name="left_back_url")
    private String leftBackURL;
	@Column(name="right_back_url")
    private String rightBackURL;


	@Column(name="border_solid")
    private String borderSolid;
	@Column(name="border_color")
	private String borderColor;
	@Column(name="link_color")
	private String linkColor;
	@Column(name="main_back_color")
	private String mainBackColor;
	@Column(name="top_back_url")
	private String topBackURL;
	@Column(name="main_back_url")
	private String mainBackURL;
	@Column(name="title_font_family")
	private String titleFontFamily;
	@Column(name="title_color")
	private String titleColor;
	@Column(name="theme_name")
	private String themeName;
	@Column(name="topBackColor")
	private String topBackColor;
	@Column(name="left_back_repeat")
	private String leftBackRepeat;
	@Column(name="right_back_repeat")
	private String rightBackRepeat;
	@Column(name="top_back_repeat")
	private String topBackRepeat;
	@Column(name="main_back_repeat")
	private String mainBackRepeat;
	
	
	
	
	public String getLeftBackRepeat() {
		return leftBackRepeat;
	}

	public void setLeftBackRepeat(String leftBackRepeat) {
		this.leftBackRepeat = leftBackRepeat;
	}

	public String getRightBackRepeat() {
		return rightBackRepeat;
	}

	public void setRightBackRepeat(String rightBackRepeat) {
		this.rightBackRepeat = rightBackRepeat;
	}

	public String getTopBackRepeat() {
		return topBackRepeat;
	}

	public void setTopBackRepeat(String topBackRepeat) {
		this.topBackRepeat = topBackRepeat;
	}

	public String getMainBackRepeat() {
		return mainBackRepeat;
	}

	public void setMainBackRepeat(String mainBackRepeat) {
		this.mainBackRepeat = mainBackRepeat;
	}

	public String getTopBackColor() {
		return topBackColor;
	}

	public void setTopBackColor(String topBackColor) {
		this.topBackColor = topBackColor;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public String getMainBackURL() {
		return mainBackURL;
	}

	public void setMainBackURL(String mainBackURL) {
		this.mainBackURL = mainBackURL;
	}

	

	public String getTitleFontFamily() {
		return titleFontFamily;
	}

	public void setTitleFontFamily(String titleFontFamily) {
		this.titleFontFamily = titleFontFamily;
	}

	public String getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	public String getMainBackColor() {
		return mainBackColor;
	}

	public void setMainBackColor(String mainBackColor) {
		this.mainBackColor = mainBackColor;
	}

	public String getLinkColor() {
		return linkColor;
	}

	public void setLinkColor(String linkColor) {
		this.linkColor = linkColor;
	}


    




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getTopBackURL() {
		return topBackURL;
	}

	public void setTopBackURL(String topBackURL) {
		this.topBackURL = topBackURL;
	}

	public String getBorderSolid() {
		return borderSolid;
	}

	public void setBorderSolid(String borderSolid) {
		this.borderSolid = borderSolid;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getFontFamily() {
		return fontFamily;
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}



	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}




	public String getBackColor() {
		return backColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}

	public String getLeftBackURL() {
		return leftBackURL;
	}

	public void setLeftBackURL(String leftBackURL) {
		this.leftBackURL = leftBackURL;
	}

	public String getRightBackURL() {
		return rightBackURL;
	}

	public void setRightBackURL(String rightBackURL) {
		this.rightBackURL = rightBackURL;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	public String getLogoURL() {
		return logoURL;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}



	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}



}
