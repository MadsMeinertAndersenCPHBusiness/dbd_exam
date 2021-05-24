package com.example.demo.Service;

import com.example.demo.Model.Recipe;
import com.example.demo.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    public Collection<Recipe> getAll(){
        return recipeRepository.getAll();
    }
}
