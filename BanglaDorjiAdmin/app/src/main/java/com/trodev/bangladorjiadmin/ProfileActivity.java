package com.trodev.bangladorjiadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private ProgressBar progressBar;
    private ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Shop Profile");


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
        reference = FirebaseDatabase.getInstance().getReference("Shop_owner");
        userID = user.getUid();

        //  final TextView greatingText = findViewById(R.id.greetingTextView);
        final TextView nameET = findViewById(R.id.nameEt);
        final TextView emailET = findViewById(R.id.emailEt);
        final TextView numberET = findViewById(R.id.numberEt);
        final TextView addET = findViewById(R.id.addEt);
        final TextView passET = findViewById(R.id.passEt);

        progressBar.setVisibility(View.VISIBLE);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User shopProfile = snapshot.getValue(User.class);
                if (shopProfile != null) {

                    String uname = shopProfile.sname;
                    String uemail = shopProfile.semail;
                    String unum = shopProfile.snumber;
                    String uadd = shopProfile.saddress;
                    String upass = shopProfile.spass;

                    //  greatingText.setText(uname);
                    nameET.setText(uname);
                    emailET.setText(uemail);
                    numberET.setText("+88 " + unum);
                    addET.setText(uadd);
                    passET.setText(upass);

                    Toast.makeText(ProfileActivity.this, "Shop Info", Toast.LENGTH_SHORT).show();
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