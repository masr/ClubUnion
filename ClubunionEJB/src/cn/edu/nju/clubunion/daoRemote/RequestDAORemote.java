package cn.edu.nju.clubunion.daoRemote;

import java.util.List;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.abstractEntity.ARequest;

@Remote
public interface RequestDAORemote {
	public void rejectRequest(int requestId);

	public void insertRequest(ARequest request);

	public void deleteRequest(int requestId);

	public void approveRequest(int requestId);

	public boolean isRequestReceiver(int requestId, int roleId);

	public List getAllReceivedRequests(int roleId);

	public ARequest getRequestById(int requestId);

	public void specifyDatabase(String s);

}
