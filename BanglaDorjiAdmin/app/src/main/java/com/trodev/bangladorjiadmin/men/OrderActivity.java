package com.trodev.bangladorjiadmin.men;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trodev.bangladorjiadmin.R;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    DatabaseReference databaseReference, dbRef;
    private List<Ordertshirt> mentshirtList;
    private OrdertshirtAdapter tshirtAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Men's Order List");

        progressDialog = new ProgressDialog(this);

        // Admin Data get korbe eikhane theke
        databaseReference = FirebaseDatabase.getInstance().getReference("Order");
        recyclerView = findViewById(R.id.recyclerView);

        orderTshirt();
    }

    private void orderTshirt() {
        progressDialog.show();
        progressDialog.setMessage("Please wait...!");
        dbRef = databaseReference.child("t-shirt");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mentshirtList = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    progressDialog.dismiss();
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    progressDialog.dismiss();
                    recyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Ordertshirt data = snapshot.getValue(Ordertshirt.class);
                        mentshirtList.add(0, data);
                    }
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
                    tshirtAdapter = new OrdertshirtAdapter(OrderActivity.this, mentshirtList, "t-shirt");
                    recyclerView.setAdapter(tshirtAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(OrderActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}