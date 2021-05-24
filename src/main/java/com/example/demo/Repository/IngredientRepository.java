package com.example.demo.Repository;

import com.example.demo.Model.Ingredient;
import com.example.demo.Model.Recipe;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IngredientRepository extends Neo4jRepository<Ingredient, Long> {
    @Query("match (n:Ingredient)-[r:USED_IN]->(m:Recipe) return n,r,m")
    Collection<Recipe> usedIn();
}
