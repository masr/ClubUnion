package cn.edu.nju.clubunion.mBean.letter;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.ALetter;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.ILetterService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.CUH;

public class LetterShow {
	private String senderName;
	private String receiverName;
	private Integer senderId;
	private Integer receiverId;
	private String title;
	private String content;
	private Integer id;
	private Date createdAt;

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public LetterShow() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		ILetterService service;
		Integer letterId = CUH.getFromRequest("letter_id");
		if (letterId == null) {
			fctx.addMessage(null, new FacesMessage("参数异常"));
			return;
		}

		service = (ILetterService) CUH.getRemoteService("LetterService/remote");
		if (service == null) {
			CUH.addServiceExceptionMessage();
			return;
		}

		try {
			ALetter letter = service.getLetterInfo(user, letterId);
			senderName = letter.getSenderName();
			senderId = letter.getSenderId();
			receiverName = letter.getReceiverName();
			receiverId = letter.getReceiverId();
			id = letter.getId();
			content = letter.getContent();
			createdAt = letter.getCreatedAt();
			title = letter.getTitle();
		} catch (ErrorException e) {
			fctx.addMessage(null, new FacesMessage(e.getReason()));
		}

	}
}
