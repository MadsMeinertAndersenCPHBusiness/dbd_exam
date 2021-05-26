package com.example.demo.Service;

import com.example.demo.Model.RedisProduct;
import com.example.demo.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {

    @Autowired
    private CartRepositoryCustomImpl cartRepositoryCustom;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public void addToCart(RedisProduct product, String userId){
        if (userExists(userId)) return;
        if (productExists(product)) return;
        cartRepositoryCustom.AddToCart(product, userId);
    }

    public Map<String, RedisProduct> getCart(String id){
        if (userExists(id)) return new HashMap<>();
        return cartRepositoryCustom.getCart(id);
    }

    public void emptyCart(String userId){
        if (userExists(userId)) return;
        cartRepositoryCustom.emptyCart(userId);
    }
    public void deleteItemFromCart(String userId, String itemId){
        if (userExists(userId)) return;
        var productCheck = productRepository.findById(Long.parseLong(itemId));
        if(productCheck.isEmpty()){
            System.out.println("Product does not exist");
            return;
        }
        cartRepositoryCustom.deleteItemFromCart(userId, itemId);
    }
    private boolean userExists(String userId) {
        var user = userRepository.findById(Long.parseLong(userId));
        if (user.isEmpty()) {
            System.out.println("User does not exist");
            return true;
        }
        return false;
    }
    private boolean productExists(RedisProduct product) {
        var productCheck = productRepository.findById(Long.parseLong(product.getId()));
        if(productCheck.isEmpty()){
            System.out.println("Product does not exist");
            return true;
        }
        return false;
    }
}
