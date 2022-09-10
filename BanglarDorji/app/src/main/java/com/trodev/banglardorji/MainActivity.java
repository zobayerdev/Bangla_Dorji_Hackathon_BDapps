package com.trodev.banglardorji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView register;
    private EditText emailET, passET;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private MaterialButton log_Btn;
    private TextView forgot_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isUserSigninauto();

        register = findViewById(R.id.register);
        register.setOnClickListener(this);

        log_Btn = findViewById(R.id.log_Btn);
        log_Btn.setOnClickListener(this);


        emailET = findViewById(R.id.emailEt);
        passET = findViewById(R.id.passEt);

        forgot_pass = findViewById(R.id.forgot_pass);
        forgot_pass.setOnClickListener(this);

        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

    }

    private void isUserSigninauto() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, DashboardActivity.class));
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    // Register textView clicking
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                startActivity(new Intent(MainActivity.this, RegisterAvtivity.class));
                Toast.makeText(MainActivity.this, "Register Here", Toast.LENGTH_SHORT).show();
                finish();
                break;

            case R.id.log_Btn:
                userLogin();
                break;

            case R.id.forgot_pass:
                startActivity(new Intent(MainActivity.this, ForgotpassActivity.class));
                Toast.makeText(this, "Forgot Password", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void userLogin() {

        String email = emailET.getText().toString().trim();
        String pass = passET.getText().toString().trim();

        if (email.isEmpty()) {
            emailET.setError("Email is required");
            emailET.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            passET.setError("Password must be 6 character");
            passET.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user.isEmailVerified()) {
                        startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                        Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        finish();
                    } else {
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this, "Verify your e-mail", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Login UnSuccessful", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}