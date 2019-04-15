package com.company;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) throws IOException {
        Main example = new Main();
        Gson gson = new Gson();
        String response = example.run("http://api.openweathermap.org/data/2.5/weather?q=Ufa&appid=af897b16b3f7759e62e7a05058273246");
        System.out.println(response);
        JsonElement elem = new JsonParser().parse(response);
        JsonObject obj = elem.getAsJsonObject();
        System.out.println(obj.getAsJsonObject("main").get("temp"));
    }
}
