package cn.edu.nju.clubunion.mBean.club;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import cn.edu.nju.clubunion.abstractEntity.ABlockContent;
import cn.edu.nju.clubunion.abstractEntity.AClubStyle;
import cn.edu.nju.clubunion.abstractEntity.AContainerBlock;
import cn.edu.nju.clubunion.abstractEntity.AImgContent;
import cn.edu.nju.clubunion.abstractEntity.ATextContent;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IClubDesignService;
import cn.edu.nju.clubunion.businessLogicClient.IClubService;
import cn.edu.nju.clubunion.helper.CUH;
import cn.edu.nju.clubunion.helper.PPW;

public class ClubStyle {

	private List<AContainerBlock> containers = new ArrayList<AContainerBlock>();
	private AClubStyle clubStyle;
	private Integer clubId;

	public Integer getClubId() {
		return clubId;
	}

	public void setClubId(Integer clubId) {
		this.clubId = clubId;
	}

	public AClubStyle getClubStyle() {
		return clubStyle;
	}

	public void setClubStyle(AClubStyle clubStyle) {
		this.clubStyle = clubStyle;
	}

	public List<AContainerBlock> getContainers() {
		return containers;
	}

	public void setContainers(List<AContainerBlock> containers) {
		this.containers = containers;
	}

	public ClubStyle() {
		FacesContext fctx = FacesContext.getCurrentInstance();
		IAccountService user = CUH.getAccountService();
		IClubDesignService designService;
		clubId = CUH.getFromRequest("club_id");
		if (clubId == null) {
			fctx.addMessage(null, new FacesMessage("参数异常"));
			return;
		}

		designService = (IClubDesignService) CUH
				.getRemoteService("ClubDesignService/remote");
		if (designService == null) {
			CUH.addServiceExceptionMessage();
			return;
		}
		clubStyle = designService.getClubStyle(user, clubId);

		containers = designService.getContainerBlocks(user, clubId);

	}

}
