package com.example.demo.Service;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DataService {

    @Value("${Neo4jUrl}")
    String url;

    public void getData(){
        try (CloseableHttpClient client = HttpClients.createDefault()){
            HttpGet request = new HttpGet(url + "/data");
            HttpResponse httpResponse = client.execute(request);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
