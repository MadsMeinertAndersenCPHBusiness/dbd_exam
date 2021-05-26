package com.example.demo.Service;

import com.example.demo.Model.Ingredient;
import com.example.demo.Model.Recipe;
import com.example.demo.Repository.IngredientRepository;
import com.example.demo.Repository.RecipeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    IngredientRepository ingredientRepository;

    public Collection<Recipe> getAll(){
        return recipeRepository.findAll();
    }

    public void addRecipe(Recipe recipe){
        var ingredients = new ArrayList<Ingredient>();
        for (Ingredient i: recipe.getIngredients()) {
            var newI = ingredientRepository.merge(i.getName());
            ingredients.add(newI);
        }
        recipe.setIngredients(ingredients);
    }
}
