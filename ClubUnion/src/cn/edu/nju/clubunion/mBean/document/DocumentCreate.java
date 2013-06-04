package cn.edu.nju.clubunion.mBean.document;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.ADocument;
import cn.edu.nju.clubunion.abstractEntity.APoster;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IDocumentService;
import cn.edu.nju.clubunion.businessLogicClient.IPosterService;
import cn.edu.nju.clubunion.entity.Document;
import cn.edu.nju.clubunion.entity.Poster;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;
import cn.edu.nju.clubunion.helper.PPW;

/**
 * @author godfrey
 * 
 */
public class DocumentCreate {
	private String title = null;
	private String description = null;
	private String content = null;
	private String tagString = null;
	private Integer documentType = null;
	private String activityDate = null;

	public Integer getDocumentType() {
		return documentType;
	}

	public void setDocumentType(Integer documentType) {
		this.documentType = documentType;
	}

	public String getTagString() {
		return tagString;
	}

	public void setTagString(String tagString) {
		this.tagString = tagString;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String submit() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IDocumentService service;

		Integer clubId = CUH.getFromRequest("club_id");
		if (clubId == null) {
			fctx.addMessage(null, new FacesMessage("参数异常"));
			return "fail";
		}

		service = (IDocumentService) CUH
				.getRemoteService("DocumentService/remote");
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return "fail";
		}
		ADocument document = new Document();
		document.setClubId(clubId);
		document.setUserId(user.getId());
		document.setContent(CUH.getStringFromRequest("content1"));
		document.setDescription(description);

		document.setTitle(title);
		document.setDocumentType(documentType);
		document.setCreatedAt(new Date());
		document.setUpdatedAt(new Date());
		activityDate = CUH.getStringFromRequest("activityDate");
		if (activityDate != null && !activityDate.equals("")) {

			String time[] = activityDate.split("/");
			int month = Integer.parseInt(time[0]);
			int day = Integer.parseInt(time[1]);
			int year = Integer.parseInt(time[2]);
			Date date = new Date();

			date.setMonth(month - 1);
			date.setYear(year - 1900);
			date.setDate(day);
			document.setActivityData(date);

		}
		int documentId;
		try {
			documentId = service.createDocument(user, document);

		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
			return "fail";
		}

		if (documentType.equals(PPW.NOTICE_TYPE)) {
			String post = CUH.getStringFromRequest("post_url");
			if (post == null || post.equals(""))
				return "success";
			IPosterService posterService;
			posterService = (IPosterService) CUH
					.getRemoteService("PosterService/remote");
			APoster poster = new Poster();
			poster.setDocumentId(documentId);
			poster.setPosterURL(post);

			try {
				posterService.addNoticePoster(user, clubId, poster, documentId);

			} catch (ErrorException e) {

				fctx.addMessage(null, new FacesMessage(e.getReason()));
				e.printStackTrace();
				return "fail";
			}

		}
		return "success";
	}
}
