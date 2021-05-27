package com.example.demo.Service;

import com.example.demo.Model.Amount;
import com.example.demo.Model.Ingredient;
import com.example.demo.Model.Recipe;
import com.example.demo.Repository.IngredientRepository;
import com.example.demo.Repository.RecipeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    IngredientRepository ingredientRepository;

    public Collection<Recipe> getAll(){
        return recipeRepository.findAll();
    }

    public Optional<Recipe> findByName(Long id){
        return recipeRepository.findById(id);
    }

//    public void addRecipe(Recipe recipe){
//        Amount amount = new Amount();
//        var ingredients = new HashMap<Ingredient, Amount>();
//        for (Ingredient i: recipe.getIngredients().keySet()) {
//            var newI = ingredientRepository.merge(i.getName());
//            ingredients.put(newI, amount);
//        }
//        recipe.setIngredients(ingredients);
//    }
}
