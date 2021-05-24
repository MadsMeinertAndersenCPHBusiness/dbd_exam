package com.example.demo.Service;

import com.example.demo.Model.Ingredient;
import com.example.demo.Model.Recipe;
import com.example.demo.Repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class IngredientService {
    @Autowired
    IngredientRepository ingredientRepository;

    public Collection<Ingredient> getAll(){
        return ingredientRepository.findAll();
    }
    public Collection<Recipe> usedId(){
        return ingredientRepository.usedIn();
    }

}
