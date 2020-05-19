package br.com.alura.interception;

import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Priority(1)
@br.com.alura.interception.Logger
public class InterceptionLogger {

	@AroundInvoke
	public Object tratarExcecao(InvocationContext context) throws Exception {
		Logger logger = Logger.getLogger(context.getTarget().getClass().getName());
		try {
			return context.proceed();
		} catch (Exception e) {
			logger.info("Interceptador chamado");
			throw e;
		}
	}
}
