package com.example.latihan5;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface InformationJsonPlaceHolderAPI {
    @GET("information.php")
    Call<List<InformationPost>> getPosts();
    @GET("information.php")
    Call<List<InformationPost>> getPosts(@QueryMap Map<String, String> parameters);
}
