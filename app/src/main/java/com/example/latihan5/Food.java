package com.example.latihan5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Food extends AppCompatActivity {
    private static final String TAG = "Food";

    private FoodAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private EditText searchFood;
    private ChipGroup priceRangeChipGroup;
    private List<FoodPost> originalFoodList;
    private FoodJsonPlaceHolderAPI foodJsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        initializeViews();
        setupSwipeRefresh();
//        setupSearch();
//        setupPriceFilter();
        initializeRetrofit();
        fetchFoodData();
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.foodrecyclerview);
        swipeRefresh = findViewById(R.id.swipeRefresh);
//        searchFood = findViewById(R.id.searchFood);
//        priceRangeChipGroup = findViewById(R.id.priceRangeChipGroup);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    private void setupSwipeRefresh() {
        swipeRefresh.setColorSchemeResources(R.color.primary);
        swipeRefresh.setOnRefreshListener(this::fetchFoodData);
    }

//    private void setupSearch() {
//        searchFood.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                filterFood(s.toString(), getCurrentPriceRange());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {}
//        });
//    }

//    private void setupPriceFilter() {
//        priceRangeChipGroup.setOnCheckedChangeListener((group, checkedId) -> {
//            if (checkedId != View.NO_ID) {
//                Chip chip = findViewById(checkedId);
//                String priceRange = chip.getText().toString();
//                filterFood(searchFood.getText().toString(), priceRange);
//            }
//        });
//    }

    private void initializeRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url.food)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        foodJsonPlaceHolderAPI = retrofit.create(FoodJsonPlaceHolderAPI.class);
    }

    private void fetchFoodData() {
        swipeRefresh.setRefreshing(true);

        Call<List<FoodPost>> call = foodJsonPlaceHolderAPI.getPosts();
        call.enqueue(new Callback<List<FoodPost>>() {
            @Override
            public void onResponse(Call<List<FoodPost>> call, Response<List<FoodPost>> response) {
                swipeRefresh.setRefreshing(false);

                if (!response.isSuccessful()) {
                    showError("Error Code: " + response.code());
                    return;
                }

                List<FoodPost> posts = response.body();
                if (posts != null && !posts.isEmpty()) {
                    originalFoodList = new ArrayList<>(posts);
                    setupRecyclerView(posts);
                } else {
                    showError("Tidak ada data");
                }
            }

            @Override
            public void onFailure(Call<List<FoodPost>> call, Throwable t) {
                swipeRefresh.setRefreshing(false);
                showError("Error: " + t.getMessage());
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
            }
        });
    }

    private void setupRecyclerView(List<FoodPost> foodList) {
        adapter = new FoodAdapter(this, foodList);
        recyclerView.setAdapter(adapter);
    }

    private void filterFood(String query, String priceRange) {
        if (originalFoodList == null) return;

        List<FoodPost> filteredList = new ArrayList<>();
        for (FoodPost food : originalFoodList) {
            boolean matchesSearch = food.getNamaMakanan().toLowerCase()
                    .contains(query.toLowerCase()) ||
                    food.getDeskripsiMakanan().toLowerCase()
                            .contains(query.toLowerCase());

            boolean matchesPrice = true;
            if (!priceRange.equals("Semua Harga")) {
                int price = Integer.parseInt(food.getHargaMakanan().replaceAll("[^0-9]", ""));
                switch (priceRange) {
                    case "< Rp 25.000":
                        matchesPrice = price < 25000;
                        break;
                    case "Rp 25.000 - 50.000":
                        matchesPrice = price >= 25000 && price <= 50000;
                        break;
                    case "> Rp 50.000":
                        matchesPrice = price > 50000;
                        break;
                }
            }

            if (matchesSearch && matchesPrice) {
                filteredList.add(food);
            }
        }

        adapter.updateData(filteredList);
    }

//    private String getCurrentPriceRange() {
//        int checkedId = priceRangeChipGroup.getCheckedChipId();
//        if (checkedId != View.NO_ID) {
//            Chip chip = findViewById(checkedId);
//            return chip.getText().toString();
//        }
//        return "Semua Harga";
//    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.e(TAG, message);
    }

    public static String formatPrice(String price) {
        try {
            double amount = Double.parseDouble(price);
            return String.format("Rp %,.0f", amount);
        } catch (NumberFormatException e) {
            return price;
        }
    }
}