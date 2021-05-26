package com.example.demo.dbUtility;


import lombok.Data;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class DbConnection {

    @Value("${spring.datasource.url}")
    String url;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;


    public DbConnection() {
    }

    public Connection connect()
    {
        Connection conn = null;
        try
        {
            System.out.println("url: " + url + " " + " user: " +  username + " password: " + password);
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void runScripts() throws FileNotFoundException, SQLException {
        Flyway flyway = new Flyway(new FluentConfiguration()
                .baselineOnMigrate(true)
                .target("4")
                .dataSource("jdbc:postgresql://localhost:5432/postgres", "user", "password"));
        flyway.clean();
        flyway.migrate();
    }
}
