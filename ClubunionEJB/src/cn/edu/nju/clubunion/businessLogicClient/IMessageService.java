package cn.edu.nju.clubunion.businessLogicClient;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.abstractEntity.AMessage;
import cn.edu.nju.clubunion.exception.ErrorException;

@Remote
public interface IMessageService {
	public void deleteMessage(IAccountService user, int messageId)
			throws ErrorException;

	public int leaveMessage(IAccountService user, AMessage messsageInfo)
			throws ErrorException;

	public void editMessage(IAccountService user, AMessage messageInfo)
			throws ErrorException;

	public void specifyDatabase(String s);

}
