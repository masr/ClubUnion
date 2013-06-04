package cn.edu.nju.clubunion.sessionBean;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.businessLogicClient.IRequestService;
import cn.edu.nju.clubunion.daoRemote.RequestDAORemote;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.PPW;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Stateless
public class RequestService implements IRequestService {
	
	private RequestDAORemote dao;
	private String database;

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	public void approveRequset(IAccountService user, int requestId)
			throws ErrorException {
		if (dao == null)
			throw new ErrorException("服务不存在异常");

		if (!dao.isRequestReceiver(requestId, user.getId()))
			throw new ErrorException("您没有权限同意请求");
		dao.approveRequest(requestId);
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	public void deleteRequset(IAccountService user, int requestId)
			throws ErrorException {
		if (dao == null)
			throw new ErrorException("服务不存在异常");

		if (!dao.isRequestReceiver(requestId, user.getId()))
			throw new ErrorException("您没有权限删除请求");
		dao.specifyDatabase(database);

		dao.approveRequest(requestId);

	}

	// 在这个地方需要在客户端强制转换成需要的类型
	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	public ARequest getRequsetInfo(IAccountService user, int requestId)
			throws ErrorException {
		if (dao == null)
			throw new ErrorException("服务不存在异常");

		if (!dao.isRequestReceiver(requestId, user.getId()))
			throw new ErrorException("您没有权限得到请求");

		ARequest request = dao.getRequestById(requestId);
		return request;// 在客户端要强制转换，这好不好？
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	public void rejectRequset(IAccountService user, int requestId)
			throws ErrorException {
		if (dao == null)
			throw new ErrorException("服务不存在异常");

		if (!dao.isRequestReceiver(requestId, user.getId()))
			throw new ErrorException("您没有权限拒绝请求");


		dao.rejectRequest(requestId);
	}

	@Interceptors(cn.edu.nju.clubunion.interceptors.LogginInterceptor.class)
	public void submitRequest(IAccountService user, ARequest requestInfo)
			throws ErrorException {
		if (dao == null)
			throw new ErrorException("服务不存在异常");

		dao.insertRequest(requestInfo);
	}

	public void setRequestService(int type) throws ErrorException {
		try {
			InitialContext ctx = new InitialContext();
			switch (type) {

			case PPW.CREATE_CLUB_REQUEST_TYPE:
				dao = (RequestDAORemote) ctx
						.lookup("ClubCreateRequestDAO/remote");
				dao.specifyDatabase(database);
				break;
			case PPW.FRIEND_REQUEST_TYPE:
				dao = (RequestDAORemote) ctx.lookup("FriendRequestDAO/remote");
				dao.specifyDatabase(database);
				break;
			case PPW.USER_APPLY_TO_CLUB_REQUEST:
				dao = (RequestDAORemote) ctx
						.lookup("UserApplyToClubRequestDAO/remote");
				dao.specifyDatabase(database);
				break;
			case PPW.CLUB_INVITE_USER_REQUEST:
				dao = (RequestDAORemote) ctx
						.lookup("ClubInviteUserRequestDAO/remote");
				dao.specifyDatabase(database);
				break;
			default:
				throw new ErrorException("服务调用异常");
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void specifyDatabase(String s) {
		database = s;
	}

}
