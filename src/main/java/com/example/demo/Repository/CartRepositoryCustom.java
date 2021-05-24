package com.example.demo.Repository;

import com.example.demo.Model.RedisProduct;
import org.springframework.stereotype.Repository;


import java.util.Map;

@Repository
public interface CartRepositoryCustom {
    void AddToCart(RedisProduct product, String userId);
    Map<String, RedisProduct> getCart(String userId);
    void emptyCart(String userId);
    void deleteItemFromCart(String userId, String itemId);
}
