package cn.edu.nju.clubunion.sessionBean;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import cn.edu.nju.clubunion.abstractEntity.AMessage;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IMessageService;
import cn.edu.nju.clubunion.daoRemote.MessageDAORemote;
import cn.edu.nju.clubunion.entity.Message;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.PPW;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Stateless
@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
public class MessageService implements IMessageService {
	
	@EJB(beanName = "MessageDAO")
	MessageDAORemote messageDAO;

	public void deleteMessage(IAccountService user, int messageId)
			throws ErrorException {
		Message message = messageDAO.getMessageByID(messageId);
		if ((message.getUserId() != user.getId())
				&& (user.getPermissionInClub(message.getClubId()) != PPW.CLUB_MANAGER)
				&& (!user.isAdmin()))
			throw new ErrorException("您没有权限删除留言");

		messageDAO.deleteMessage(messageId);
	}

	public void editMessage(IAccountService user, AMessage messageInfo)
			throws ErrorException {
		Message message = messageDAO.getMessageByID(messageInfo.getId());
		if (message.getUserId() != user.getId())
			throw new ErrorException("您没有权限修改留言");

		message = (Message) messageInfo;
		messageDAO.mergeMessage(message);
	}

	public int leaveMessage(IAccountService user, AMessage messageInfo) {
		Message m = (Message) messageInfo;
		int id = messageDAO.insertMessage(m);
		return id;
	}

	public void specifyDatabase(String s) {
		messageDAO.specifyDatabase(s);
	}

}
