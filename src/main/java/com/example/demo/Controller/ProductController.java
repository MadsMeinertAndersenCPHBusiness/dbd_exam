package com.example.demo.Controller;

import com.example.demo.Model.Product;
import com.example.demo.Model.ProductDTO;
import com.example.demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public Optional<Product> findOne(@PathVariable Long id){
        return productService.findOne(id);
    }

    @GetMapping()
    public Collection<Product> findAll(){
        return productService.findAll();
    }

    @PostMapping()
    public boolean insertProduct(@RequestBody Product product){
        return productService.insertProduct(product);
    }
    @DeleteMapping("/{id}")
    public boolean deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }
    @GetMapping("/view/{view}")
    public Collection<ProductDTO> getView(@PathVariable String view){
        return productService.getView(view);
    }
}
