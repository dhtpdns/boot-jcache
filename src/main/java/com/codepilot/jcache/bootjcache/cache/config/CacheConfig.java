package com.codepilot.jcache.bootjcache.cache.config;


import java.time.Duration;
import java.util.Arrays;

import javax.cache.CacheManager;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.codepilot.jcache.bootjcache.cache.enums.CacheConst;
import com.codepilot.jcache.bootjcache.cache.enums.CacheConst.CacheNames;

import org.springframework.beans.factory.annotation.Value;

/**
 * reference doc
 * https://www.ehcache.org/documentation/3.4/getting-started.html#configuring-ehcache
 *
 */
@Component
@EnableCaching
public class CacheConfig implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(CacheConfig.class);

//    private final CacheManager cacheManager;
//    public CacheConfig(CacheManager cacheManager) {
//        this.cacheManager = cacheManager;
//    }
//


	//@Value("${cache.item_limit_szie}")
	//private long CHASING_ITEM_LIMIT_SZIE;

    public static final String TEST_CACHE = "Test";

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfig( @Value("${cache.max_bucket}") long chasingMaxBucket,
    		@Value("${cache.item_limit_szie}") long itemLimitSzie,
    		@Value("${cache.time_to_idle}") long timeToIdle,
    		@Value("${cache.time_to_live}") long timeTo_live
    		) {
    
        this.jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.newResourcePoolsBuilder()
                        .heap(chasingMaxBucket, EntryUnit.ENTRIES))      								// Chasing Elemnet Count
                .withSizeOfMaxObjectSize(itemLimitSzie, MemoryUnit.B)  									// Caching Elemnet Max Size
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(timeToIdle)))   // 마지막 캐시 요청 이후 10초동안 재요청이 없는 경우 나료
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(timeTo_live)))); 		// 최초 캐시 입력 후 10초 동안 저장
        
    	logger.info("CHASING_MAX_BUCKET ({})" + "ITEM_LIMIT_SZIE ({})" + "TIME_TO_IDLE ({})" + "TIME_TO_LIVE ({})",chasingMaxBucket,itemLimitSzie,timeToIdle,timeTo_live );
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
        	Arrays.stream(CacheNames.values()).forEach(cache->{
        		cm.createCache(cache.name(), jcacheConfiguration);
        	});
            //cm.createCache(USER_CACHE, jcacheConfiguration);            
        };
    }
    
    @Override
    public void run(String... strings) throws Exception {
        logger.info("\n\n" + "=========================================================\n"
                + "Using cache manager: " + this.jcacheConfiguration.getClass().getName() + "\n"
                + "=========================================================\n\n");
    }
}