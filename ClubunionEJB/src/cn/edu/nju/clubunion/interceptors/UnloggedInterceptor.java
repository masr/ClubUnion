package cn.edu.nju.clubunion.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import cn.edu.nju.clubunion.businessLogicClient.IAccountService;
import cn.edu.nju.clubunion.exception.ErrorException;
import javax.persistence.Entity;


public class UnloggedInterceptor {
	@AroundInvoke
	public Object log(InvocationContext ctx) throws Exception {
		Object o = ctx.getParameters()[0];
		if (ctx.getMethod().getName().equals("specifyDatabase"))
			return ctx.proceed();
		if (o == null)
			throw new ErrorException("业务逻辑参数异常");

		if (((IAccountService) o).loggedIn())
			throw new ErrorException("您已登录");

		return ctx.proceed();
	}
}
