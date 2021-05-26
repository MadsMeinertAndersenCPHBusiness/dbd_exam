package com.example.demo.Repository;

import com.example.demo.Model.Ingredient;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface IngredientRepository extends Neo4jRepository<Ingredient, Long> {

    @Query("merge(n:Ingredient {name: {name}}) return n")
    Ingredient merge(@Param("name") String name);

}
