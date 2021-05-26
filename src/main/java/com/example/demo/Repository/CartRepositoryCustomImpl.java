package com.example.demo.Repository;

import com.example.demo.Model.RedisProduct;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public class CartRepositoryCustomImpl implements CartRepositoryCustom {

    private RedisTemplate<String, RedisProduct> redisTemplate;
    private HashOperations hashOperations;

    public CartRepositoryCustomImpl(RedisTemplate<String, RedisProduct> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void AddToCart(RedisProduct product, String userId) {
        hashOperations.put(userId, product.getId(), product);
    }

    @Override
    public Map<String, RedisProduct> getCart(String userId) {
        return hashOperations.entries(userId);
    }

    @Override
    public void emptyCart(String userId) {
        var products = getCart(userId);
        redisTemplate.delete(userId);
        //hashOperations.delete(userId);

    }

    @Override
    public void deleteItemFromCart(String userId, String itemId) {
        hashOperations.delete(userId, itemId);
    }
}
