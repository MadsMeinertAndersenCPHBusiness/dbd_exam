package com.example.demo.Model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Product")
@Data
public class RedisProduct implements Serializable {

    private String id;
    private String userId;
    private String name;
}
