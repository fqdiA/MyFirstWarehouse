package com.fq.superparking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        //管理redis工厂
        redisTemplate.setConnectionFactory(factory);
        // 设置key的序列化方式
        redisTemplate.setKeySerializer(RedisSerializer.string());
        // 设置value的序列化方式
        redisTemplate.setValueSerializer(RedisSerializer.json());
        return redisTemplate;
    }
}
