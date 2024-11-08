package com.example.latihan5;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface FoodJsonPlaceHolderAPI {
    @GET("food.php")
    Call<List<FoodPost>> getPosts();
    @GET("food.php")
    Call<List<FoodPost>> getPosts(@QueryMap Map<String, String> parameters);
}
