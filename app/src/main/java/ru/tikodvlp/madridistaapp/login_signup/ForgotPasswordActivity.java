package ru.tikodvlp.madridistaapp.login_signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import ru.tikodvlp.madridistaapp.R;

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


    private void resetPassword() {

        String email = enterEmail.getEditText().getText().toString().trim();

        if (email.isEmpty()) {
            enterEmail.setError("Email is required!");
            enterEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            enterEmail.setError("Please provide valid email!");
            enterEmail.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivity.this, "Check your email to reset your password",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ForgotPasswordActivity.this, "Try again later", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}