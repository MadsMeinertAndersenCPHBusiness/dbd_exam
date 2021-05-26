package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.NamedEntityGraph;

@NodeEntity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue
    Long id;

    String name;

    public Ingredient(String name) {
        this.name = name;
    }
}
