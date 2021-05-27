package com.example.demo.dbUtility;

import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Neo4jScript {
    @Autowired
    Neo4jDriverCustom neo4jDriver;

    public void runScripts(){

        try(Session session = neo4jDriver.getDriver().session()){
            Result result = session.run("call apoc.cyhper.runFile(\"../../resources/db.migration/V5__neo4j.cypher\", {})");
            System.out.println(result);
        }
    }
}
