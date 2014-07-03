package ch.zuehlke.camp.service.interceptor;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Logging
@Priority(2100)
public class LoggingInterceptor {

	@AroundInvoke
	public Object log(InvocationContext context) throws Exception {
		String methodName = context.getMethod().getName();
		String className = context.getMethod().getDeclaringClass().getSimpleName();
		long startTime = System.currentTimeMillis();
		System.out.println("START method " + className + "." + methodName);
		
		Object object = context.proceed();
		
		long endTime = System.currentTimeMillis();
		System.out.println("STOP method " + className + "." + methodName + " Time: " + (endTime - startTime) + "ms");
		
		return object;
	}
}
