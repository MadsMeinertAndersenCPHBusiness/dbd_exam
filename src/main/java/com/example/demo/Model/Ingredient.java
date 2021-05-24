package com.example.demo.Model;


import lombok.Data;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import static org.neo4j.ogm.annotation.Relationship.OUTGOING;

@NodeEntity
@Data
public class Ingredient {

    @Id
    @GeneratedValue
    Long Id;

    String name;


    @Relationship(type = "USED_IN", direction = OUTGOING)
    private List<Recipe> recipes = new ArrayList<>();
}
