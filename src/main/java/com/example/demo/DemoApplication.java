package com.example.demo;

import com.example.demo.Model.RedisProduct;
import com.example.demo.dbUtility.DbConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class DemoApplication {
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, RedisProduct> redisTemplate() {
        RedisTemplate<String, RedisProduct> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

}
