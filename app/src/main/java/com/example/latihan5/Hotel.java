package com.example.latihan5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Hotel extends AppCompatActivity {
    private static final String TAG = "Hotel";

    private HotelAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private EditText searchHotel;
    private List<HotelPost> originalHotelList;
    private HotelJsonPlaceHolderAPI hotelJsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);

        initializeViews();
        setupSwipeRefresh();
        initializeRetrofit();
        fetchHotelData();
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.hotelrecyclerview);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    private void setupSwipeRefresh() {
        swipeRefreshLayout.setColorSchemeResources(R.color.primary);
        swipeRefreshLayout.setOnRefreshListener(this::fetchHotelData);
    }

    private void initializeRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url.hotel)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        hotelJsonPlaceHolderAPI = retrofit.create(HotelJsonPlaceHolderAPI.class);
    }

    private void fetchHotelData() {
        swipeRefreshLayout.setRefreshing(true);

        Call<List<HotelPost>> call = hotelJsonPlaceHolderAPI.getPosts();
        call.enqueue(new Callback<List<HotelPost>>() {
            @Override
            public void onResponse(Call<List<HotelPost>> call, Response<List<HotelPost>> response) {
                swipeRefreshLayout.setRefreshing(false);

                if (!response.isSuccessful()) {
                    showError("Error Code: " + response.code());
                    return;
                }

                List<HotelPost> posts = response.body();
                if (posts != null && !posts.isEmpty()) {
                    originalHotelList = new ArrayList<>(posts);
                    setupRecyclerView(posts);
                } else {
                    showError("Tidak ada data");
                }
            }

            @Override
            public void onFailure(Call<List<HotelPost>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                showError("Error: " + t.getMessage());
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }

    private void setupRecyclerView(List<HotelPost> hotelList) {
        adapter = new HotelAdapter(this, hotelList);
        recyclerView.setAdapter(adapter);
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.e(TAG, message);
    }
}