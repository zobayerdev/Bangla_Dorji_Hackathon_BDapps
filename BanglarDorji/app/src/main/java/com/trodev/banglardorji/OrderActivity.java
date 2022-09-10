package com.trodev.banglardorji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    DatabaseReference databaseReference, dbRef;
    private List<Mentshirt> mentshirtList;
    private TshirtAdapter tshirtAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        databaseReference = FirebaseDatabase.getInstance().getReference("Order");
        recyclerView = findViewById(R.id.recyclerView);

        dua();
    }

    private void dua() {


        Query query = databaseReference.child("t-shirt").orderByChild("uid").equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());

        // eikhane old code ache

        // dbRef = databaseReference.child("t-shirt").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        /*        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mentshirtList = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {

                    recyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Mentshirt data = snapshot.getValue(Mentshirt.class);
                        mentshirtList.add(0, data);
                    }
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
                    tshirtAdapter = new TshirtAdapter(OrderActivity.this, mentshirtList, "t-shirt");
                    recyclerView.setAdapter(tshirtAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(OrderActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });*/

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mentshirtList = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    Toast.makeText(OrderActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Mentshirt data = snapshot.getValue(Mentshirt.class);
                        mentshirtList.add(0, data);

                    }
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(OrderActivity.this));
                    tshirtAdapter = new TshirtAdapter(OrderActivity.this, mentshirtList, "t-shirt");
                    recyclerView.setAdapter(tshirtAdapter);
                    Toast.makeText(OrderActivity.this, "Data Found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}