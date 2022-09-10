package com.trodev.banglardorji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

public class ClothingActivity extends AppCompatActivity {

    private MaterialCardView men, women, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothing);

        user = findViewById(R.id.user);
        men = findViewById(R.id.man);
        women = findViewById(R.id.woman);


        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClothingActivity.this, MenActivity.class));
                //Toast.makeText(DashboardActivity.this, "Watch Men's Collection", Toast.LENGTH_SHORT).show();
            }
        });

        women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClothingActivity.this, WomenActivity.class));
                //  Toast.makeText(DashboardActivity.this, "Watch Women's Collection", Toast.LENGTH_SHORT).show();
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ClothingActivity.this, ProfileActivity.class));
                Toast.makeText(ClothingActivity.this, "User Profile", Toast.LENGTH_SHORT).show();
            }
        });
    }
}