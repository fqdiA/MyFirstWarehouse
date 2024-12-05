package com.fq.superparking.utils;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtil {

    final RedisTemplate<String,Object> redisTemplate;



    public void setKey(String key,Object value){
        ValueOperations ops = redisTemplate.opsForValue();
        ops.set(key,value);
    }

    public void setKey(String key,Object value,Integer time ){
        ValueOperations ops = redisTemplate.opsForValue();
        // 以秒为单位的过期时间
        ops.set(key,value,time, TimeUnit.SECONDS);
    }
    public Object getValue(String key){
        ValueOperations ops = redisTemplate.opsForValue();
        return  ops.get(key);
    }


    // 删除key
    public Boolean deleteKey(String key){
        return redisTemplate.delete(key);
    }


}
