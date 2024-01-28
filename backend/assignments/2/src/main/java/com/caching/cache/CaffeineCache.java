package com.caching.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

@Configuration
@EnableCaching
public class CaffeineCache {
    public Caffeine<Object,Object> caffeine(){
        return Caffeine.newBuilder().maximumSize(10).expireAfterAccess(10, TimeUnit.MINUTES);
    }
    @Bean
    public CacheManager manager(){
        CaffeineCacheManager cacheManager=new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeine());
        cacheManager.setCacheNames(asList("geocoding","reverse-geocoding"));
        return cacheManager;
    }
}
