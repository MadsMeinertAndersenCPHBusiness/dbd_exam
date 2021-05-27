package com.example.demo;

import com.example.demo.Model.RedisProduct;
import com.example.demo.dbUtility.DbConnection;
import com.example.demo.dbUtility.Neo4jScript;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.neo4j.driver.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class DemoApplication {
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, RedisProduct> redisTemplate() {
        RedisTemplate<String, RedisProduct> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }


    public static void main(String[] args) throws FileNotFoundException, SQLException {
        SpringApplication.run(DemoApplication.class, args);
        DbConnection dbConnection = new DbConnection();
        dbConnection.runScripts();
        Driver driver;

        driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "password"));
        try(Session session = driver.session()){
            Result result = session.run("call apoc.cypher.runFile(\"/V5__neo4j.cypher\", {})");
            System.out.println(result);
        }

    }

}
