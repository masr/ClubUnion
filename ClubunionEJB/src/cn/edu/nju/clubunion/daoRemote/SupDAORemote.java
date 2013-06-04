package cn.edu.nju.clubunion.daoRemote;

import java.util.List;

public interface SupDAORemote {
	public List getAllReceivedClubCreateRequests();

	public void specifyDatabase(String s);

}
