package com.example.demo.Service;

import com.example.demo.Model.Ingredient;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class IngredientService {
    @Value("${Neo4jUrl}")
    String url;

    public Ingredient merge(String name){
        Ingredient ingredient = null;
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url + "/ingredient/" + name);
            request.setHeader("Content-Type", "application/json;charset=utf-8");

            try (CloseableHttpResponse response = client.execute(request)){
                HttpEntity entity = response.getEntity();
                if (entity != null){
                    String result = EntityUtils.toString(entity);
                    JSONObject json = new JSONObject(result);
                    var ingreName = json.getString("name");
                    ingredient = new Ingredient(ingreName);
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return ingredient;
    }
}
