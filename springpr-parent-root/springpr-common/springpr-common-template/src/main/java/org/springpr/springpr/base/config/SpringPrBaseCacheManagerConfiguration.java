/* (C)2023 */
package org.springpr.springpr.base.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class SpringPrBaseCacheManagerConfiguration {

    @Bean
    @Primary
    CacheManager springPrDefaultCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("springPrDefaultCache");
        cacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .initialCapacity(10)
                        .maximumSize(200)
                        .expireAfterWrite(1, TimeUnit.MINUTES)
                        .weakKeys()
                        .recordStats());
        return cacheManager;
    }

    @Bean
    CacheManager springPrSimpleCacheManager() {
        return new ConcurrentMapCacheManager("springPrSimpleCache");
    }
}
