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

public class Information extends AppCompatActivity {
    private static final String TAG = "Information";

    private InformationAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<InformationPost> originalInformationList;
    private InformationJsonPlaceHolderAPI informationJsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        initializeViews();
        setupSwipeRefresh();
        initializeRetrofit();
        fetchInformationData();
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.informationrecyclerview);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    private void setupSwipeRefresh() {
        swipeRefreshLayout.setColorSchemeResources(R.color.primary);
        swipeRefreshLayout.setOnRefreshListener(this::fetchInformationData);
    }

    private void initializeRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url.information)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        informationJsonPlaceHolderAPI = retrofit.create(InformationJsonPlaceHolderAPI.class);
    }

    private void fetchInformationData() {
        swipeRefreshLayout.setRefreshing(true);

        Call<List<InformationPost>> call = informationJsonPlaceHolderAPI.getPosts();
        call.enqueue(new Callback<List<InformationPost>>() {
            @Override
            public void onResponse(Call<List<InformationPost>> call, Response<List<InformationPost>> response) {
                swipeRefreshLayout.setRefreshing(false);

                if (!response.isSuccessful()) {
                    showError("Error Code: " + response.code());
                    return;
                }

                List<InformationPost> posts = response.body();
                if (posts != null && !posts.isEmpty()) {
                    originalInformationList = new ArrayList<>(posts);
                    setupRecyclerView(posts);
                } else {
                    showError("Tidak ada data");
                }
            }

            @Override
            public void onFailure(Call<List<InformationPost>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                showError("Error: " + t.getMessage());
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }

    private void setupRecyclerView(List<InformationPost> informationList) {
        adapter = new InformationAdapter(this, informationList);
        recyclerView.setAdapter(adapter);
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.e(TAG, message);
    }
}