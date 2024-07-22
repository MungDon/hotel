package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // Redis 서버와의 연결을 관리하는 LettuceConnectionFactory 객체를 생성합니다. host와 port는 앞서 주입된 값입니다.
        return new LettuceConnectionFactory(host, port);
    }

    // RedisTemplate 타입의 Bean을 생성합니다.
    @Bean
    public RedisTemplate<?,?> redisTemplate() {
        // Redis 데이터를 직렬화하여 저장하기 위해 RedisTemplate 객체를 생성
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        // redisTemplate에 Redis 서버와의 연결을 관리하는 redisConnectionFactory를 설정
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        // redisTemplate 객체를 반환하여 Spring 컨테이너가 이를 관리

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // Key    로 들어가는 값을 직렬화 시킴
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        // value 로 들어가는 값을 직렬화 시킴
        return redisTemplate;
    }
}
