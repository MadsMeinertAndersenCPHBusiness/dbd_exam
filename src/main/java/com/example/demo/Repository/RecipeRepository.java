package com.example.demo.Repository;

import com.example.demo.Model.Recipe;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public interface RecipeRepository extends Neo4jRepository<Recipe, Long> {
    @Query("match (n:Recipe)<-[r:USED_IN]-(m:Ingredient) return n,r,m")
    Collection<Recipe> getAll();
}
