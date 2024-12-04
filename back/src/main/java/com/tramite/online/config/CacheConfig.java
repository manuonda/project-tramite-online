package com.tramite.online.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import static com.tramite.online.constants.CacheConstants.CACHE_TTL_MINUTES;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return new LettuceConnectionFactory();
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofMinutes(CACHE_TTL_MINUTES));
        return RedisCacheManager.builder(redisConnectionFactory)
        .cacheDefaults(redisCacheConfiguration)
        .build();
    }
    

}
