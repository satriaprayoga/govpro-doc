package com.govpro.config.cache;

import java.util.Properties;

import javax.cache.Cache;

import org.hibernate.cache.jcache.JCacheRegionFactory;
import org.hibernate.cache.spi.CacheDataDescription;

public class NoDefaultJCacheRegionFactory extends JCacheRegionFactory{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2160849767210619521L;
	
	@Override
	protected Cache<Object, Object> createCache(String regionName, Properties properties,
			CacheDataDescription metadata) {
		throw new IllegalStateException("All Hibernate caches should be created upfront. " +
	            "Please update CacheConfiguration.java to add " + regionName);
	}

}
