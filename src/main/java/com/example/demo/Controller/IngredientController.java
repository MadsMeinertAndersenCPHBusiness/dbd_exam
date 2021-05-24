package com.example.demo.Controller;

import com.example.demo.Model.Ingredient;
import com.example.demo.Model.Recipe;
import com.example.demo.Service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    @Autowired
    IngredientService ingredientService;


    @GetMapping
    public Collection<Recipe> getAll(){
        return ingredientService.usedId();
    }
}
