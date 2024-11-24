package com.example.latihan5;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RentJsonPlaceHolderAPI {
    @GET("kendaraan.php")
    Call<List<RentPost>> getPosts();
    @GET("kendaraan.php")
    Call<List<RentPost>> getPosts(@QueryMap Map<String, String> parameters);
}

