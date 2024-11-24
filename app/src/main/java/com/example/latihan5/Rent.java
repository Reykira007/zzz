package com.example.latihan5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rent extends AppCompatActivity {
    private static final String TAG = "Rent";

    private RentAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<RentPost> originalRentList;
    private RentJsonPlaceHolderAPI rentJsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        initializeViews();
        setupSwipeRefresh();
        initializeRetrofit();
        fetchRentData();
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.rentrecyclerview);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    private void setupSwipeRefresh() {
        swipeRefreshLayout.setColorSchemeResources(R.color.primary);
        swipeRefreshLayout.setOnRefreshListener(this::fetchRentData);
    }

    private void initializeRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url.rent)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rentJsonPlaceHolderAPI = retrofit.create(RentJsonPlaceHolderAPI.class);
    }

    private void fetchRentData() {
        swipeRefreshLayout.setRefreshing(true);

        Call<List<RentPost>> call = rentJsonPlaceHolderAPI.getPosts();
        call.enqueue(new Callback<List<RentPost>>() {
            @Override
            public void onResponse(Call<List<RentPost>> call, Response<List<RentPost>> response) {
                swipeRefreshLayout.setRefreshing(false);

                if (!response.isSuccessful()) {
                    showError("Error Code: " + response.code());
                    return;
                }

                List<RentPost> posts = response.body();
                if (posts != null && !posts.isEmpty()) {
                    originalRentList = new ArrayList<>(posts);
                    setupRecyclerView(posts);
                } else {
                    showError("Tidak ada data");
                }
            }

            @Override
            public void onFailure(Call<List<RentPost>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                showError("Error: " + t.getMessage());
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }

    private void setupRecyclerView(List<RentPost> rentList) {
        adapter = new RentAdapter(this, rentList);
        recyclerView.setAdapter(adapter);
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.e(TAG, message);
    }
}