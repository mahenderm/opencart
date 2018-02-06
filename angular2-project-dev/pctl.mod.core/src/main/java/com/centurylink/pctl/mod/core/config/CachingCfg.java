package com.centurylink.pctl.mod.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by begin.samuel on 11/23/2016.
 */

@Configuration
@ConfigurationProperties(prefix = "redis")
public class CachingCfg {


    public String getCacheHost() {
        return cacheHost;
    }

    public void setCacheHost(String cacheHost) {
        this.cacheHost = cacheHost;
    }

    public int getCachePort() {
        return cachePort;
    }

    public void setCachePort(int cachePort) {
        this.cachePort = cachePort;
    }

    public long getCacheExpiration() {
        return cacheExpiration;
    }

    public void setCacheExpiration(long cacheExpiration) {
        this.cacheExpiration = cacheExpiration;
    }


    private String cacheHost;
    private int cachePort;
    private long cacheExpiration;




    @Bean
    public JedisConnectionFactory jedisConnectionFactory()
    {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(this.cacheHost);
         jedisConnectionFactory.setPort(this.cachePort);
        //jedisConnectionFactory.setHostName("127.0.0.1");
        //jedisConnectionFactory.setPort(6379);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate()
    {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setExposeConnection(true);
        return redisTemplate;
    }

    @Bean
    public RedisCacheManager cacheManager()
    {
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
        redisCacheManager.setTransactionAware(true);
        redisCacheManager.setLoadRemoteCachesOnStartup(true);
        redisCacheManager.setDefaultExpiration(this.cacheExpiration); /*Enabled for Testing Purposes*/
        redisCacheManager.setUsePrefix(true);
        return redisCacheManager;
    }

}
