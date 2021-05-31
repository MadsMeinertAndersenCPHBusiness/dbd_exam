package com.example.demo.Service;

import com.example.demo.Model.Amount;
import com.example.demo.Model.Ingredient;
import com.example.demo.Model.Recipe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class RecipeService {


    @Value("${Neo4jUrl}")
    String url;

    public Collection<Recipe> getAll(){
        Collection<Recipe> recipes = new ArrayList<>();
        try(CloseableHttpClient client = HttpClients.createDefault()){
            HttpGet request = new HttpGet(url + "/recipe");
            request.setHeader("Content-Type", "application/json;charset=utf-8");
            try (CloseableHttpResponse response = client.execute(request)){
                HttpEntity entity = response.getEntity();
                if (entity != null){
                    String result = EntityUtils.toString(entity);
                    JSONArray json = new JSONArray(result);
                    for(int i = 0; i<json.length(); i++){
                        List<Amount> amounts = new ArrayList<>();
                        JSONObject jsonObject = (JSONObject) json.get(i);
                        var name = jsonObject.getString("name");
                        var amountArray = jsonObject.getJSONArray("amount");
                        for (int o = 0; o < amountArray.length(); o++){
                        JSONObject amountObject = (JSONObject) amountArray.get(o);

                        var amount = amountObject.getLong("amount");
                        var ingredient = amountObject.getJSONObject("ingredient");
                        Gson gson = new Gson();
                        Ingredient ingredient1 = gson.fromJson(String.valueOf(ingredient), Ingredient.class);

                        amounts.add(new Amount(amount, ingredient1));
                        }
                        recipes.add(new Recipe(name, amounts));
                    }
                }

            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return recipes;
    }


    public void addRecipe(Recipe recipe) throws Exception {


        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(recipe);

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .setHeader("Content-Type", "application/json")
                .uri(URI.create( url + "/recipe"))
                .build();

        HttpResponse<?> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());
        System.out.println(response.statusCode());
    }
}
