package com.example.demo.Service;

import com.example.demo.Model.RedisProduct;
import com.example.demo.Repository.CartRepository;
import com.example.demo.Repository.CartRepositoryCustom;
import com.example.demo.Repository.CartRepositoryCustomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class CartService {

    @Autowired
    private CartRepositoryCustomImpl cartRepositoryCustom;

    public void addToCart(RedisProduct product, String userId){
        cartRepositoryCustom.AddToCart(product, userId);
    }
    public Map<String, RedisProduct> getCart(String id){
        return cartRepositoryCustom.getCart(id);
    }

    public void emptyCart(String userId){
        cartRepositoryCustom.emptyCart(userId);
    }
    public void deleteItemFromCart(String userId, String itemId){
        cartRepositoryCustom.deleteItemFromCart(userId, itemId);
    }

}
