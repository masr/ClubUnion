package cn.edu.nju.clubunion.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.exception.ErrorException;
import cn.edu.nju.clubunion.helper.PPW;
import javax.persistence.Entity;


public class ClubManagerInterceptor {
	@AroundInvoke
	public Object log(InvocationContext ctx) throws Exception {
		Object arg0 = ctx.getParameters()[0];
		Object arg1 = ctx.getParameters()[1];

		if (ctx.getMethod().getName().equals("specifyDatabase"))
			return ctx.proceed();

		if (arg0 == null || arg1 == null)
			throw new ErrorException("业务逻辑参数异常");

		if (((IAccountService) arg0).getPermissionInClub((Integer) arg1) != PPW.CLUB_MANAGER)
			throw new ErrorException("您不是该组织的管理员");

		return ctx.proceed();
	}
}
