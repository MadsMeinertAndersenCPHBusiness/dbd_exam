package com.example.demo.Controller;

import com.example.demo.Model.Recipe;
import com.example.demo.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping
    public Collection<Recipe> getAll(){
        return recipeService.getAll();
    }


    @PostMapping
    public void addRecipe(@RequestBody Recipe recipe) throws Exception {
        recipeService.addRecipe(recipe);
    }
}
