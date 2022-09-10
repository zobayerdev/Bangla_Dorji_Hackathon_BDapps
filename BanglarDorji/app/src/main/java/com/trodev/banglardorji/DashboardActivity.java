package com.trodev.banglardorji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {

    private MaterialCardView clothing, laundry, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        clothing = findViewById(R.id.clothing);
        laundry = findViewById(R.id.laundry);
        user = findViewById(R.id.user);


        clothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, ClothingActivity.class));
                //Toast.makeText(DashboardActivity.this, "Watch Men's Collection", Toast.LENGTH_SHORT).show();
            }
        });

        laundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, ClothingActivity.class));
                //  Toast.makeText(DashboardActivity.this, "Watch Women's Collection", Toast.LENGTH_SHORT).show();
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
                Toast.makeText(DashboardActivity.this, "User Profile", Toast.LENGTH_SHORT).show();
            }
        });


    }
}