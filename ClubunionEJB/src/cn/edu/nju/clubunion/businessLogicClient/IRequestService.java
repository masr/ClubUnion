package cn.edu.nju.clubunion.businessLogicClient;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.abstractEntity.ARequest;
import cn.edu.nju.clubunion.exception.ErrorException;

@Remote
public interface IRequestService {
	// 提交请求，创建请求实体，根据requestType创建不同request实体（共有四种）。验证权限
	public void submitRequest(IAccountService user, ARequest requestInfo)
			throws ErrorException;

	public ARequest getRequsetInfo(IAccountService user, int requestId)
			throws ErrorException;// 得到特定请求信息，验证权限

	public void rejectRequset(IAccountService user, int requestId)
			throws ErrorException;// 拒绝请求,验证权限

	public void approveRequset(IAccountService user, int requestId)
			throws ErrorException;// 同意请求,验证权限

	public void deleteRequset(IAccountService user, int requestId)
			throws ErrorException;// 删除请求，验证权限

	public void specifyDatabase(String s);

	public void setRequestService(int type) throws ErrorException;

}
