package cn.edu.nju.clubunion.daoRemote;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.entity.Message;

@Remote
public interface MessageDAORemote {
	public int insertMessage(Message message);

	public void mergeMessage(Message message);

	public void deleteMessage(int mwssageId);

	public Message getMessageByID(int messageId);

	public void specifyDatabase(String s);

}
