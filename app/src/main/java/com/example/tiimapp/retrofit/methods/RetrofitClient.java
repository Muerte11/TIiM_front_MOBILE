package com.example.tiimapp.retrofit.methods;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    private static String BASE_URL = "http://192.168.0.108:8085/";



    public static Retrofit getRetrofitInstance(){

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> {
            Instant instant = Instant.ofEpochMilli(json.getAsJsonPrimitive().getAsLong());
            return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        }).create();

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
