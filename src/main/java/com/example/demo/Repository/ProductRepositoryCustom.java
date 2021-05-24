package com.example.demo.Repository;

import com.example.demo.Model.Product;
import com.example.demo.Model.ProductDTO;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepositoryCustom {
    boolean insertProduct(Product product);
    boolean deleteProduct(int id);
    Collection<ProductDTO> getView(String view);
}
