package cn.edu.nju.clubunion.daoRemote;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.entity.Letter;

@Remote
public interface LetterDAORemote {
	public int insertLetter(Letter letter);

	public void mergeLetter(Letter letter);

	public void deleteLetter(int letterid);

	public boolean canAccessLetter(int letterId, int userId);

	public Letter getLetterByID(int letterid);

	public void specifyDatabase(String s);

}
