package ru.tikodvlp.madridistaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfileActivity extends AppCompatActivity {

    TextInputLayout fullName, email, password;
    TextView fullNameLabel, usernameLabel;

    String _USERNAME, _NAME, _EMAIL, _PASSWORD;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        reference = FirebaseDatabase.getInstance().getReference("users");

        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        password = findViewById(R.id.password_profile);
        fullNameLabel = findViewById(R.id.full_name_label);
        usernameLabel = findViewById(R.id.username_label);

        showAllUserData();
    }

    private void showAllUserData() {
        Intent intent = getIntent();
        _USERNAME = intent.getStringExtra("username");
        _NAME = intent.getStringExtra("name");
        _EMAIL = intent.getStringExtra("email");
        _PASSWORD = intent.getStringExtra("password");

        fullNameLabel.setText(_NAME);
        usernameLabel.setText(_USERNAME);
        email.getEditText().setText(_EMAIL);
        password.getEditText().setText(_PASSWORD);
        fullName.getEditText().setText(_NAME);
    }

    public void update(View view) {
        if (isNameChanged() || isPasswordChanged() || isEmailChanged()) {
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data is same and cannot be updated", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmailChanged() {
        if (!_EMAIL.equals(email.getEditText().getText().toString())) {
            reference.child(_EMAIL).child("email").setValue(email.getEditText().getText().toString());
            _EMAIL = email.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }


    private boolean isPasswordChanged() {
        if (!_PASSWORD.equals(password.getEditText().getText().toString())) {
            reference.child(_PASSWORD).child("password").setValue(password.getEditText().getText().toString());
            _PASSWORD = password.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }


    private boolean isNameChanged() {
        if (!_NAME.equals(fullName.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("name").setValue(fullName.getEditText().getText().toString());
            _NAME = fullName.getEditText().getText().toString();
            return true;
        } else {
            return false;
        }
    }
}