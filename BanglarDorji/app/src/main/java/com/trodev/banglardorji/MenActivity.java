package com.trodev.banglardorji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

public class MenActivity extends AppCompatActivity {

    private MaterialCardView tshirt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men);


        tshirt = findViewById(R.id.tshirt);

        tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MenActivity.this, MtshirtActivity.class));
                Toast.makeText(MenActivity.this, "Search your product", Toast.LENGTH_SHORT).show();
            }
        });

    }
}