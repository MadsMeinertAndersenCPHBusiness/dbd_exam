package com.example.demo.dbUtility;

import lombok.Data;
import org.neo4j.driver.Driver;
import org.springframework.stereotype.Service;


@Service
public class Neo4jDriverCustom {
    private Driver driver;

    public Neo4jDriverCustom(Driver driver) {
        this.driver = driver;
    }

    public Driver getDriver() {
        return driver;
    }
}
