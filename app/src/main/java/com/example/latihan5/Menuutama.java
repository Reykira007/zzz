package com.example.latihan5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Menuutama extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuutama);
    }

    //pindah ke menu food
    public void pindah_menufood(View view){
        Intent food = new Intent(Menuutama.this, Food.class);
        startActivity(food);
    }

    public void pindah_menuhotel(View view){
        Intent hotel = new Intent(Menuutama.this, Hotel.class);
        startActivity(hotel);
    }

    public void pindah_menukendaraan(View view){
        Intent kendaraan = new Intent(Menuutama.this, Rent.class);
        startActivity(kendaraan);
    }

    public void pindah_menuinformasi(View view){
        Intent informasi = new Intent(Menuutama.this, Information.class);
        startActivity(informasi);
    }
}