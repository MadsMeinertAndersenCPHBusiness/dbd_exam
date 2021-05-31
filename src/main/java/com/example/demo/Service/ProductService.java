package com.example.demo.Service;

import com.example.demo.Model.Ingredient;
import com.example.demo.Model.Product;
import com.example.demo.Model.ProductDTO;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Repository.ProductRepositoryCustomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductRepositoryCustomImpl productRepositoryCustom;

    @Autowired
    private IngredientService ingredientService;



    public Collection<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findOne(Long id){
        return productRepository.findById(id);
    }
    public boolean insertProduct(Product product){
        Ingredient ingredient = new Ingredient(product.getName());
        ingredientService.merge(product.getName());
        return productRepositoryCustom.insertProduct(product);
    }
    public boolean deleteProduct(int id){
        return productRepositoryCustom.deleteProduct(id);
    }
    public Collection<ProductDTO> getView(String view){
        return productRepositoryCustom.getView(view);
    }
}
