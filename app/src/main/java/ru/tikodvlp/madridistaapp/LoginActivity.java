package ru.tikodvlp.madridistaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {
    Button callSignUp;
    ImageView ivLogo;
    TextView tvLogoName, tvSignText;
    Button btnEnterLogin,signUpScreen, btnForgotPassword;
    TextInputLayout username, password;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        callSignUp = findViewById(R.id.signUpScreen);
        ivLogo = findViewById(R.id.logoImageLogin);
        tvLogoName = findViewById(R.id.logoNameLogin);
        tvSignText = findViewById(R.id.tvSignInLogin);
        btnEnterLogin = findViewById(R.id.btnEnterLogin);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    private Boolean validateUsername() {
            String val = username.getEditText().getText().toString();

            if (val.isEmpty()) {
                username.setError("Field cannot be empty");
                return false;
            } else {
                username.setError(null);
                username.setErrorEnabled(false);
                return true;
            }
        }

    private Boolean validatePassword() {
            String val = password.getEditText().getText().toString();

            if (val.isEmpty()) {
                password.setError("Field cannot be empty");
                return false;
            } else {
                password.setError(null);
                //password.setEnabled(false);
                return true;
            }
        }
    public void loginUser(View view) {
            // validate Login info
            if (!validateUsername() | !validatePassword()) {
                return;
            }
            else {
                isUser();
            }
        }

    private void isUser() {
        progressBar.setVisibility(View.VISIBLE);
        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    username.setError(null);
                    username.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if(passwordFromDB.equals(userEnteredPassword)) {

                        username.setError(null);
                        username.setErrorEnabled(false);

                        String nameFromDB = snapshot.child(userEnteredUsername).child("name").getValue(String.class);
                        String usernameFromDB = snapshot.child(userEnteredUsername).child("username").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUsername).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);
                        progressBar.setVisibility(View.VISIBLE);
                    }
                    else {
                        progressBar.setVisibility(View.GONE);
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    username.setError("No such User exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void callForgotPassword(View view) {
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        Pair[] pairs = new Pair[5];

        pairs[0] = new Pair<View, String>(ivLogo, "logo_image");
        pairs[1] = new Pair<View, String>(tvLogoName, "logo_text");
        pairs[2] = new Pair<View, String>(username, "email_tran");
        pairs[3] = new Pair<View, String>(tvSignText, "reset");
        pairs[4] = new Pair<View, String>(btnEnterLogin, "reset");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
        startActivity(intent, options.toBundle());
    }

    public void callSignUpScreen(View view) {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);

            Pair[] pairs = new Pair[7];

            pairs[0] = new Pair<View, String>(ivLogo, "logo_image");
            pairs[1] = new Pair<View, String>(tvLogoName, "logo_text");
            pairs[2] = new Pair<View, String>(tvSignText, "sign");
            pairs[3] = new Pair<View, String>(btnEnterLogin, "enter_tran");
            pairs[4] = new Pair<View, String>(callSignUp, "sign_tran");
            pairs[5] = new Pair<View, String>(password, "pass_tran");
            pairs[6] = new Pair<View, String>(username, "user_tran");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
            startActivity(intent, options.toBundle());
        }
  }