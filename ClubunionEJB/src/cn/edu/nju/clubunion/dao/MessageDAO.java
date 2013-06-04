package cn.edu.nju.clubunion.dao;

import javax.ejb.Stateless;

import cn.edu.nju.clubunion.daoRemote.MessageDAORemote;
import cn.edu.nju.clubunion.entity.Club;
import cn.edu.nju.clubunion.entity.Message;
import cn.edu.nju.clubunion.entity.User;
import javax.persistence.Entity;


@Stateless

public class MessageDAO extends AbstractDAO implements MessageDAORemote {

	public int insertMessage(Message message) {
		User u = entitymanager.find(User.class, message.getUserId());
		Club c = entitymanager.find(Club.class, message.getClubId());
		message.setClub(c);
		message.setUser(u);
		entitymanager.persist(message);
		return message.getId();
	}

	public void mergeMessage(Message message) {
		entitymanager.merge(message);

	}

	public void deleteMessage(int messageid) {
		Message message = entitymanager.find(Message.class, messageid);
		if (message != null)
			entitymanager.remove(message);

	}

	public Message getMessageByID(int messageid) {
		return entitymanager.find(Message.class, messageid);

	}
}
