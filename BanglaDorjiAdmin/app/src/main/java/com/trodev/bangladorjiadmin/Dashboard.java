package com.trodev.bangladorjiadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;
import com.trodev.bangladorjiadmin.men.OrderActivity;

public class Dashboard extends AppCompatActivity {

    private MaterialCardView user,men;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        user = findViewById(R.id.user);
        men = findViewById(R.id.men);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,ProfileActivity.class));
            }
        });

        men.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, OrderActivity.class));
            }
        });
    }
}