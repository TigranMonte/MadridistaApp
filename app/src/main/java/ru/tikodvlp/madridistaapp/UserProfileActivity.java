package ru.tikodvlp.madridistaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class UserProfileActivity extends AppCompatActivity {

    TextInputLayout fullName, email, phoneNum, password;
    TextView fullNameLabel, usernameLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        phoneNum = findViewById(R.id.phone_num_profile);
        password = findViewById(R.id.password_profile);
        fullNameLabel = findViewById(R.id.full_name_label);
        usernameLabel = findViewById(R.id.username_label);

        showAllUserData();
    }

    private void showAllUserData() {
        Intent intent = getIntent();
        String user_username = intent.getStringExtra("username");
        String user_name = intent.getStringExtra("name");
        String user_email = intent.getStringExtra("email");
        String user_phoneNum = intent.getStringExtra("phoneNum");
        String user_password = intent.getStringExtra("password");

        fullNameLabel.setText(user_name);
        usernameLabel.setText(user_username);
        email.getEditText().setText(user_email);
        phoneNum.getEditText().setText(user_phoneNum);
        password.getEditText().setText(user_password);
        fullName.getEditText().setText(user_name);
    }
}