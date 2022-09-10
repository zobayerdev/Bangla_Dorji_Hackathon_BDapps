package com.trodev.banglardorji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private MaterialButton logout;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private ProgressBar progressBar;
    private ImageView order,cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        order = findViewById(R.id.order);
        cart = findViewById(R.id.cart_btn);

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,OrderActivity.class));
                Toast.makeText(ProfileActivity.this, "Order Details", Toast.LENGTH_SHORT).show();
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,TcartAvtivity.class));
            }
        });


        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                Toast.makeText(ProfileActivity.this, "Log-out Successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        progressBar = findViewById(R.id.progressBar);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        //  final TextView greatingText = findViewById(R.id.greetingTextView);
        final TextView nameET = findViewById(R.id.nameEt);
        final TextView emailET = findViewById(R.id.emailEt);
        final TextView numberET = findViewById(R.id.numberEt);

        progressBar.setVisibility(View.VISIBLE);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {
                    String uname = userProfile.uname;
                    String email = userProfile.email;
                    String num = userProfile.num;

                    //  greatingText.setText(uname);
                    nameET.setText(uname);
                    emailET.setText(email);
                    numberET.setText("+88 " + num);

                    Toast.makeText(ProfileActivity.this, "User data found", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileActivity.this, "Server Problem!", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.VISIBLE);

            }
        });

    }
}