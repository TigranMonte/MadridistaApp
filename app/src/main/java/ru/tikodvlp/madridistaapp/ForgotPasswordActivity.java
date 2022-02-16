package ru.tikodvlp.madridistaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ForgotPasswordActivity extends AppCompatActivity {

    ImageView ivLogo;
    TextView logoNameReset, resetPassword;
    Button btnResetPassword;
    TextInputLayout enterEmail;
    ProgressBar progressBar;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ivLogo = findViewById(R.id.ivLogo);
        logoNameReset = findViewById(R.id.logoNameReset);
        resetPassword = findViewById(R.id.resetPassword);
        btnResetPassword = findViewById(R.id.btnResetPassword);
        enterEmail = findViewById(R.id.enter_email);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        auth = FirebaseAuth.getInstance();

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private Boolean validateEmail(){
        String val = enterEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            enterEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            enterEmail.setError("Invalid email address");
            return false;
        }
        else {
            enterEmail.setError(null);
            return true;
        }
    }

    private void resetPassword() {

        if (!validateEmail()) {
            return;
        }
        String email = enterEmail.getEditText().getText().toString().trim();
        progressBar.setVisibility(View.GONE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivity.this, "Check your email to reset your password",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    progressBar.setVisibility(View.VISIBLE);
                    finish();

                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Try again later", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ForgotPasswordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}