package cn.edu.nju.clubunion.sessionBean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.ISupService;
import cn.edu.nju.clubunion.daoRemote.SupDAORemote;
import cn.edu.nju.clubunion.exception.ErrorException;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Stateless
public class SupService implements ISupService {
	
	@EJB(beanName = "SupDAO")
	private SupDAORemote supDAO;

	@SuppressWarnings("unchecked")
	public List<ARequest> getAllClubCreateRequests(IAccountService user)
			throws ErrorException {
		return supDAO
				.getAllReceivedClubCreateRequests();
		
	}

	@SuppressWarnings("unchecked")
	public List<ARequest> getUnsolvedClubCreateRequests(IAccountService user)
			throws ErrorException {
		List<ARequest> allRequests = supDAO
				.getAllReceivedClubCreateRequests();
		for (int i=0;i<allRequests.size();i++) {
			if (allRequests.get(i).isProcessed())
			{
				allRequests.remove(allRequests.get(i));
				i--;
			}
		}

		return allRequests;
		
	}

	public void specifyDatabase(String s) {
		supDAO.specifyDatabase(s);
	}

}
