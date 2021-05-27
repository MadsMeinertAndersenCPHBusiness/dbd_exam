package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
//import org.neo4j.ogm.annotation.NodeEntity;

import javax.persistence.NamedEntityGraph;
import java.util.ArrayList;
import java.util.List;

@Node("Ingredient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue
    Long id;

    String name;

    @Relationship(type = "USED_IN", direction = Relationship.Direction.OUTGOING)
    private List<Amount> amounts = new ArrayList();

    public Ingredient(String name) {
        this.name = name;
    }
}
