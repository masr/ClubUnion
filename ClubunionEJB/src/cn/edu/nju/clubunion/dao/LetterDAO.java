package cn.edu.nju.clubunion.dao;

import javax.ejb.Stateless;

import cn.edu.nju.clubunion.daoRemote.LetterDAORemote;
import cn.edu.nju.clubunion.entity.Letter;
import cn.edu.nju.clubunion.entity.User;
import javax.persistence.Entity;


@Stateless

public class LetterDAO extends AbstractDAO implements LetterDAORemote {

	public int insertLetter(Letter letter) {
		User s = entitymanager.find(User.class, letter.getSenderId());
		User r = entitymanager.find(User.class, letter.getReceiverId());
		letter.setSender(s);
		letter.setReceiver(r);
		entitymanager.persist(letter);
		return letter.getId();
	}

	public void mergeLetter(Letter letter) {
		entitymanager.merge(letter);

	}

	public void deleteLetter(int letterid) {
		Letter letter = entitymanager.find(Letter.class, letterid);
		if (letter != null)
			entitymanager.remove(letter);

	}

	public Letter getLetterByID(int letterid) {
		return entitymanager.find(Letter.class, letterid);
	}

	public boolean canAccessLetter(int letterId, int userId) {
		Letter letter = this.getLetterByID(letterId);
		if (letter.getSenderId() == userId || letter.getReceiverId() == userId)
			return true;
		else
			return false;
	}

}
