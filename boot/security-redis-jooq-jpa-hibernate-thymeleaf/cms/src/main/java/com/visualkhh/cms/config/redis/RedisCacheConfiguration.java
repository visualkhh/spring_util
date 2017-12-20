package com.visualkhh.cms.config.redis;

import com.visualkhh.common.config.redis.RedisCacheManager;
import com.visualkhh.common.config.redis.RedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
@EnableRedisHttpSession
@Import(RedisConfig.class)
@Slf4j
public class RedisCacheConfiguration {

    @Value("${project.properties.cache.redis-defaultExpireTime}")
    Long cacheRedisDefaultExpireTime = null;
    @Value("${project.properties.cache.redis-expireTime-billDetailData}")
    Long cacheRedisExpireTimeBillDetailData = null;
    @Value("${project.properties.cache.redis-expireTime-billSummaryInfos}")
    Long cacheRedisExpireTimeBillSummaryInfos = null;

    @Bean
    public CacheManagerCustomizer<org.springframework.data.redis.cache.RedisCacheManager> cacheManagerCustomizer() {
        return new CacheManagerCustomizer<org.springframework.data.redis.cache.RedisCacheManager>() {
            @Override
            public void customize(org.springframework.data.redis.cache.RedisCacheManager cacheManager) {
                cacheManager.setDefaultExpiration(cacheRedisDefaultExpireTime);
                Map<String, Long> expires = new HashMap<>();
                expires.put("billDetailData",cacheRedisExpireTimeBillDetailData);
                expires.put("billSummaryInfos",cacheRedisExpireTimeBillSummaryInfos);
                cacheManager.setExpires(expires);
            }
        };
    }

    @Bean
    public org.springframework.data.redis.cache.RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setUsePrefix(true);
        return redisCacheManager;
    }
}
