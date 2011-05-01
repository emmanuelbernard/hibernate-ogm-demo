package org.hibernate.ogm.demo.intro.tools;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;


/**
 * @author Emmanuel Bernard
 */
public class FTEMProvider {
	@Inject TransactionManager transactionManager;

	@Produces
	public FullTextEntityManager getFTEM() {
		return Search.getFullTextEntityManager( transactionManager.getEntityManager() );
	}
}
