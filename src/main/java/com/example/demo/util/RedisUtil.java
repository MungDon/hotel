package com.example.demo.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisUtil {
    private final RedisTemplate redisTemplate;

    // 키값으로 데이터 가져오기
    public String getData(String key){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }
    
    // 키값으로 데이터 가져와서 있는지 없는지 판단
    public boolean existData(String key){
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * 정보저장
     * @param key 
     * @param value
     * @param duration 유효시간
     */
    public void setDataExpire(String key, String value, long duration){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(duration);
        valueOperations.set(key,value,expireDuration);
    }

    // 키값으로 데이터 삭제
    public void deleteData(String key){
        redisTemplate.delete(key);
    }
}
