package ch.zuehlke.camp.war.cdi;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@PerformanceLogging
@Priority(1200)
public class PerformanceLoggingInterceptor {

	@AroundInvoke
	@PerformanceLogging
	public Object log(InvocationContext context) throws Exception {
		String name = context.getMethod().getClass().getSimpleName() + "." + context.getMethod().getName();
//		String params = context.getParameters().toString();
		System.out.println("Method " + name + " started at " + System.currentTimeMillis());
		Object result = context.proceed();
		System.out.println("Method " + name + " ended   at " + System.currentTimeMillis());
		return result;
	}

}