package com.trodev.banglardorji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotpassActivity extends AppCompatActivity {
    private EditText emailET;
    private MaterialButton reset_Btn;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);

        emailET = findViewById(R.id.emailEt);
        reset_Btn = findViewById(R.id.reset_Btn);
        progressBar = findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        reset_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String email = emailET.getText().toString().trim();

        if(email.isEmpty())
        {
            emailET.setError("Email is required");
            emailET.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotpassActivity.this, "আপনার ই-মেইল চেক করুন", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(ForgotpassActivity.this, "দুঃখিত রিসেট লিংকটি পাঠানো সম্ভব হয় নি", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}