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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TcartAvtivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    DatabaseReference databaseReference, dbRef;
    private List<Mentshirt> mentshirtList;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcart_avtivity);

        databaseReference = FirebaseDatabase.getInstance().getReference("Cart");
        recyclerView = findViewById(R.id.recyclerView);
        dua();
    }

    private void dua() {

        dbRef = databaseReference.child("t-shirt").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        dbRef.addValueEventListener(new ValueEventListener() {
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
                    recyclerView.setLayoutManager(new LinearLayoutManager(TcartAvtivity.this));
                    adapter = new CartAdapter(TcartAvtivity.this, mentshirtList, "t-shirt");
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TcartAvtivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}