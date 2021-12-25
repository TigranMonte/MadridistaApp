package ru.tikodvlp.madridistaapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity {
    Button callSignUp;
    ImageView ivLogo;
    TextView tvLogoName, tvSignText;
    Button btnEnterLogin, signUpScreen;
    TextInputLayout username, password;

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
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);


        callSignUp.setOnClickListener(view -> {
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
        });
        }
  }

