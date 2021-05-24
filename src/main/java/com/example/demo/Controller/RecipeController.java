package com.example.demo.Controller;

import com.example.demo.Model.Recipe;
import com.example.demo.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    @Autowired
    RecipeService recipeService;

    @GetMapping
    public Collection<Recipe> getAll(){
        return recipeService.getAll();
    }
}
