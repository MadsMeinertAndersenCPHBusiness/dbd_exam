package com.example.demo.Model;

import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class Amount {
    @TargetNode
    Recipe recipe;

    int amount;
}
