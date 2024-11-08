package com.example.latihan5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Food extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    ProgressDialog progressDialog;

    private FoodAdapter adapter;
    private RecyclerView recyclerView;

    private FoodJsonPlaceHolderAPI FoodJsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        progressDialog = new ProgressDialog(Food.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url.food)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FoodJsonPlaceHolderAPI = retrofit.create(com.example.latihan5.FoodJsonPlaceHolderAPI.class);
        getPosts();
    }

    private void getPosts() {
        Call<List<FoodPost>> call = FoodJsonPlaceHolderAPI.getPosts();
        call.enqueue(new Callback<List<FoodPost>>() {
            @Override
            public void onResponse(Call<List<FoodPost>> call, Response<List<FoodPost>> response) {
                if (!response.isSuccessful()) {
                    Log.e(TAG, "Response Error Code: " + response.code());
                    Toast.makeText(Food.this, "Error Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<FoodPost> posts = response.body();
                if (posts != null && !posts.isEmpty()) {
                    Log.d(TAG, "Data diterima: " + posts.size() + " items");
                    generateDataList(posts);
                } else {
                    Log.e(TAG, "Tidak ada data");
                    Toast.makeText(Food.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<FoodPost>> call, Throwable t) {
                progressDialog.dismiss();
                // Tambahkan log error
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
                Toast.makeText(Food.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void generateDataList(List<FoodPost> photoList) {
        recyclerView = findViewById(R.id.foodrecyclerview);
        adapter = new FoodAdapter(this,photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Food.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}