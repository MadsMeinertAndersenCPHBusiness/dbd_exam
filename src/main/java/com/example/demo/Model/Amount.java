package com.example.demo.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@Data
@NoArgsConstructor
@RelationshipProperties
public class Amount {


    Long amount;

    Ingredient ingredient;

    public Amount(Long amount, Ingredient ingredient) {
        this.amount = amount;
        this.ingredient = ingredient;
    }
}
