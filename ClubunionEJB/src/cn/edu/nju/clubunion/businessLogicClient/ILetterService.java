package cn.edu.nju.clubunion.businessLogicClient;

import javax.ejb.Remote;

import cn.edu.nju.clubunion.abstractEntity.ALetter;
import cn.edu.nju.clubunion.exception.ErrorException;

@Remote
public interface ILetterService {

	public ALetter getLetterInfo(IAccountService user, int letterId)
			throws ErrorException;// 得到某个站内信信息，依赖于某个站内信，需要验证用户是否有权限查看改站内信

	public int sendLetter(IAccountService user, ALetter letterInfo)
			throws ErrorException;// 发送站内信，登录用户可用

	public void deleteLetter(IAccountService user, int letterId)
			throws ErrorException;// 删除站内信，信箱主人可用

	public void specifyDatabase(String s);

}
