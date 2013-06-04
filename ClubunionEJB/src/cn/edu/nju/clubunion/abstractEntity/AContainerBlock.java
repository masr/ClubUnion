package cn.edu.nju.clubunion.abstractEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import cn.edu.nju.clubunion.entity.GalleryImg;
import cn.edu.nju.clubunion.entity.ImgContent;
import cn.edu.nju.clubunion.entity.TextContent;

@MappedSuperclass
public abstract class AContainerBlock implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "x")
	private String x;
	@Column(name = "y")
	private String y;
	@Column(name = "w")
	private String w;
	@Column(name = "h")
	private String h;
	@Column(name = "club_style_id", insertable = false, updatable = false)
	private Integer clubStyleId;
	@Column(name = "content_type")
	private Integer contentType;
	@Column(name = "font_size")
	private String fontSize;
	@Column(name = "font_color")
	private String fontColor;
	@Column(name = "font_family")
	private String fontFamily;
	@Column(name="border_solid")
	private String borderSolid;
	@Column(name="border_color")
	private String borderColor;
	@Column(name="back_color")
	private String backColor;
	@Column(name="link_color")
	private String linkColor;
	@Column(name="title")
	private String title;
    @Column(name="title_back_color")
    private String titleBackColor;
    @Column(name="title_font_family")
    private String titleFontFamily;
    @Column(name="title_color")
    private String titleColor;
    @Column(name="theme_name")
	private String themeName;
    
    
	
	
	

	
	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public String getTitleBackColor() {
		return titleBackColor;
	}

	public void setTitleBackColor(String titleBackColor) {
		this.titleBackColor = titleBackColor;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLinkColor() {
		return linkColor;
	}

	public void setLinkColor(String linkColor) {
		this.linkColor = linkColor;
	}

	public String getFontFamily() {
		return fontFamily;
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getBackColor() {
		return backColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getW() {
		return w;
	}

	public void setW(String w) {
		this.w = w;
	}

	public String getH() {
		return h;
	}

	public void setH(String h) {
		this.h = h;
	}

	public Integer getClubStyleId() {
		return clubStyleId;
	}

	public void setClubStyleId(Integer clubStyleId) {
		this.clubStyleId = clubStyleId;
	}

	



	public Integer getContentType() {
		return contentType;
	}

	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}
	
	public abstract ImgContent getImgContent() ;



	public  abstract TextContent getTextContent();

	


	

	public  abstract List<AGalleryImg> getGalleryImgs() ;





}
