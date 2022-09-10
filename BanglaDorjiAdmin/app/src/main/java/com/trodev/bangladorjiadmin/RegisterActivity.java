package com.trodev.bangladorjiadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private MaterialButton login;
    private EditText nameET, mobileET, emailET, passET, addET;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        login = findViewById(R.id.log_Btn);
        login.setOnClickListener(this);
        nameET = findViewById(R.id.nameEt);
        mobileET = findViewById(R.id.numberEt);
        emailET = findViewById(R.id.emailEt);
        addET = findViewById(R.id.addEt);
        passET = findViewById(R.id.passEt);
        progressBar = findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.log_Btn:
                registarUser();
                break;
        }
    }

    private void registarUser() {
        String name, number, email, pass, add;
        name = nameET.getText().toString().trim();
        number = mobileET.getText().toString().trim();
        email = emailET.getText().toString().trim();
        pass = passET.getText().toString().trim();
        add = addET.getText().toString().trim();

        if (name.isEmpty()) {
            nameET.setError("Name is required");
            nameET.requestFocus();
            return;
        }
        if (number.isEmpty()) {
            mobileET.setError("Mobile number is required");
            mobileET.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            emailET.setError("E-mail is required");
            emailET.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailET.setError("Please provide valid email!");
            emailET.requestFocus();
            return;
        }

        if (add.isEmpty()) {
            addET.setError("Address is required");
            addET.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            passET.setError("Password is required");
            passET.requestFocus();
            return;
        }

        if (pass.length() <= 6) {
            passET.setError("Min password length should be 6 character");
            passET.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(name, email, pass, number, add);

                            FirebaseDatabase.getInstance().getReference("Shop_owner")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                                finish();

                                            } else {
                                                progressBar.setVisibility(View.VISIBLE);
                                                Toast.makeText(RegisterActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });

                        }
                    }
                });


    }
}