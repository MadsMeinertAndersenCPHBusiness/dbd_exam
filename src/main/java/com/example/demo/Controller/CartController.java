package com.example.demo.Controller;

import com.example.demo.Model.CartDTO;
import com.example.demo.Model.RedisProduct;
import com.example.demo.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping()
    public void addToCart(@RequestBody RedisProduct redisProduct){
        cartService.addToCart(redisProduct, redisProduct.getUserId());
    }

    @GetMapping("/{userId}")
    public Map<String, RedisProduct> getCart(@PathVariable String userId){
        return cartService.getCart(userId);
    }

}
