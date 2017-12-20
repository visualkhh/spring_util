package com.visualkhh.api.config.redis;

import com.visualkhh.common.config.properties.ProjectProperties;
import com.visualkhh.common.config.redis.RedisConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableCaching
@EnableRedisHttpSession
@Import(RedisConfig.class)
@Slf4j
public class RedisCacheConfiguration {

    @Autowired
    private ProjectProperties projectProperties;

    @Bean
    public CacheManagerCustomizer<org.springframework.data.redis.cache.RedisCacheManager> cacheManagerCustomizer() {
        return new CacheManagerCustomizer<org.springframework.data.redis.cache.RedisCacheManager>() {
            @Override
            public void customize(org.springframework.data.redis.cache.RedisCacheManager cacheManager) {
                cacheManager.setDefaultExpiration(Long.valueOf(projectProperties.getCache().getDefaultExpireTime()));
                cacheManager.setExpires(projectProperties.getCache().getExpireTime());
            }
        };
    }

//    @Bean
//    public org.springframework.data.redis.cache.RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
//        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
//        redisCacheManager.setUsePrefix(true);
//        return redisCacheManager;
//    }
}
