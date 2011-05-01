package com.jboss.specj.infinispan.nodestarter;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Map;
import java.util.Properties;

import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import org.infinispan.Cache;
import org.infinispan.config.Configuration;
import org.infinispan.config.ConfigurationValidatingVisitor;
import org.infinispan.config.GlobalConfiguration;
import org.infinispan.config.InfinispanConfiguration;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;

import org.hibernate.cfg.Environment;
import org.hibernate.ogm.datastore.infinispan.impl.TransactionManagerLookupDelegator;
import org.hibernate.transaction.JBossTSStandaloneTransactionManagerLookup;

/**
 * Start the Infinispan node
 */
public class App {
	public static void main(String[] args) throws Exception {
		System.out.println( "Starting Infinispan node" );
		EmbeddedCacheManager manager = createCustomCacheManager( "default-config.xml", new Properties() );
		manager.start();

		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name = new ObjectName( "org.infinispan:type=CacheManager,name=\"HibernateOGM\",component=CacheManager" );
		for ( String cacheName : manager.getCacheNames() ) {
			System.out.println(cacheName);
			//initialize caches: see discussions with Sanne and Manik on homogeneous clusters
			manager.getCache( cacheName );
		}
		for (;;) {
			Thread.sleep( 5000 );
			StringBuilder sb = new StringBuilder(  );
			sb.append( "Cluster size: ").append( mbs.getAttribute( name, "ClusterSize" ) );
			String clusteringMode = "repl";   //"dist"
			for ( String cacheName : manager.getCacheNames() ) {
				final ObjectName mbean = new ObjectName( "org.infinispan:type=Cache,name=\"" + cacheName + "(" + clusteringMode + "_sync)\",manager=\"HibernateOGM\",component=Statistics" );
				sb.append( " [" ).append( cacheName ).append( " entries: ").append( mbs.getAttribute( mbean, "NumberOfEntries" ) ).append( "]" );
			}
			System.out.println( sb.toString() );
		}
	}

	private static EmbeddedCacheManager createCustomCacheManager(String cfgName, Properties properties) {
		try {
			InfinispanConfiguration configuration = InfinispanConfiguration.newInfinispanConfiguration(
					cfgName, InfinispanConfiguration.resolveSchemaPath(),
					new ConfigurationValidatingVisitor());
			GlobalConfiguration globalConfiguration = configuration.parseGlobalConfiguration();
			Configuration defaultConfiguration = configuration.parseDefaultConfiguration();
			properties.setProperty(
					Environment.TRANSACTION_MANAGER_STRATEGY,
					JBossTSStandaloneTransactionManagerLookup.class.getName()
			);
			TransactionManagerLookupDelegator transactionManagerLookupDelegator = new TransactionManagerLookupDelegator( properties );
			final DefaultCacheManager cacheManager = new DefaultCacheManager( globalConfiguration, defaultConfiguration, true );
			for (Map.Entry<String, Configuration> entry : configuration.parseNamedConfigurations().entrySet()) {
				Configuration cfg = entry.getValue();
				cfg.setTransactionManagerLookup( transactionManagerLookupDelegator );
				cacheManager.defineConfiguration( entry.getKey(), cfg );
			}
			return cacheManager;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit( -1 );
		}
		return null; //actually this line is unreachable
	}
}
