package com.example.demo.Repository;

import com.example.demo.Model.RedisProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<RedisProduct, String> {
}
