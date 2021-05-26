package com.example.demo.Repository;

import com.example.demo.Model.Recipe;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RecipeRepository extends Neo4jRepository<Recipe, Long> {
    @Query("MATCH (r:Recipe)<-[u:USED]-(i:Ingredient) return r,u,i")
    Collection<Recipe> getAllRecipes();


}
