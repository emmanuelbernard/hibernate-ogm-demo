package org.hibernate.ogm.demo.intro.tools;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Emmanuel Bernard
 */
@Transactional @Interceptor
public class TransactionInterceptor {
	@Inject 
	TransactionManager manager;
	private static final Logger log = LoggerFactory.getLogger( TransactionManager.class );

	@AroundInvoke
	public Object manageTransaction(InvocationContext ctx) throws Exception {
		manager.begin();
		try {
			final Object result = ctx.proceed();
			manager.commit();
			return result;
		}
		catch ( Exception e ) {
			log.error( "Unable to commit transaction", e );
			manager.rollback();
			throw e;
		}
	}
}
