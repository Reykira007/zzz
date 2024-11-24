package com.example.latihan5;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface HotelJsonPlaceHolderAPI {
    @GET("hotel.php")
    Call<List<HotelPost>> getPosts();
    @GET("hotel.php")
    Call<List<HotelPost>> getPosts(@QueryMap Map<String, String> parameters);
}

