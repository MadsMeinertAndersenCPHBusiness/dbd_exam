package com.example.demo.Model;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import static org.neo4j.ogm.annotation.Relationship.OUTGOING;

@NodeEntity
@Data
public class Recipe {

    @Id
    @GeneratedValue
    Long Id;

    String name;

    @Relationship(type = "USED_IN", direction = INCOMING)
    private List<Ingredient> ingredients;

}
