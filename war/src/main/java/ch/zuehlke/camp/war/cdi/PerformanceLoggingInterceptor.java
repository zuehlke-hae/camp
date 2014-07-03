package ch.zuehlke.camp.war.cdi;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@PerformanceLogging
@Priority(2200)
public class PerformanceLoggingInterceptor implements Serializable {

	private static final long serialVersionUID = -5908825658535901904L;

	@AroundInvoke
	public Object log(InvocationContext context) throws Exception {
		String name = context.getMethod().getName();
//		String params = context.getParameters().toString();
		System.out.println("Method " + name + " started at " + System.currentTimeMillis());
		Object result = context.proceed();
		System.out.println("Method " + name + " ended   at " + System.currentTimeMillis());
		return result;
	}

}