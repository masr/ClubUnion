package cn.edu.nju.clubunion.businessLogicClient;

import java.util.List;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.exception.ErrorException;

@Remote
public interface ISupService {
	public List<ARequest> getUnsolvedClubCreateRequests(IAccountService user)
			throws ErrorException;

	public List<ARequest> getAllClubCreateRequests(IAccountService user)
			throws ErrorException;

	public void specifyDatabase(String s);

}
