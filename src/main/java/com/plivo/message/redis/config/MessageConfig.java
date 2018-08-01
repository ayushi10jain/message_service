package com.plivo.message.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
//import com.gateway.utils.LdapFailAwareRedisObjectSerializer;



@Configuration
@PropertySource("application.properties")
public class MessageConfig {
	
	@Value("${spring.redis.hostname}")
    private String redisHostName;

    @Value("${spring.redis.port}")
    private int redisPort;
	
	@Bean
	 JedisConnectionFactory jedisConnectionFactory() {
		 JedisConnectionFactory factory = new JedisConnectionFactory();
	        factory.setHostName(redisHostName);
	        factory.setPort(redisPort);
	        factory.setUsePool(true);
	        return factory;
	 }

	 @Bean
	 RedisTemplate< String, Object > redisTemplate() {
	  final RedisTemplate< String, Object > template =  new RedisTemplate< String, Object >();
	  template.setKeySerializer(new StringRedisSerializer());
	    template.setHashKeySerializer(new StringRedisSerializer());
	  
	  template.setConnectionFactory( jedisConnectionFactory() );
	
	  return template;
	 }
	 
	 
	 @Bean
	    RedisCacheManager cacheManager() {
	        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
	        return redisCacheManager;
	    }
	 
	
	

}
