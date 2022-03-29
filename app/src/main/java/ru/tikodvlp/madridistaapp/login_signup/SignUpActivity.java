package ru.tikodvlp.madridistaapp.login_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ru.tikodvlp.madridistaapp.R;
import ru.tikodvlp.madridistaapp.HelperClasses.UserHelperClass;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout regName, regUsername, regEmail, regPassword;
    Button btnEnter, btnToLogIn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPassword = findViewById(R.id.reg_password);
        btnEnter = findViewById(R.id.btnEnter);
        btnToLogIn = findViewById(R.id.btnToLogIn);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

    }

    private Boolean validateName(){
        String val = regName.getEditText().getText().toString();
        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else {
            regName.setError(null);
            return true;
        }
    }
    private Boolean validateUsername() {
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            regUsername.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regUsername.setError("White Spaces are not allowed");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail(){
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        }
        else {
            regName.setError(null);
            return true;
        }
    }

    // save data in FB on click
    public void registerUser(View view){
        if(!validateName() |!validatePassword() | !validateEmail() | !validateUsername()) {
            return;
        }
        String name = regName.getEditText().getText().toString();
        String username = regUsername.getEditText().getText().toString();
        String email = regEmail.getEditText().getText().toString();
        String password = regPassword.getEditText().getText().toString();

        // storing data in firebase
        UserHelperClass userHelperClass = new UserHelperClass(name, username, email, password);
        reference.child(username).setValue(userHelperClass);

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.putExtra("email", email);
        intent.putExtra("username", name);
        intent.putExtra("password", password);
        intent.putExtra("username", username);
        startActivity(intent);

        Toast.makeText(this, "New user created successfully! Enter your data to Login",
                Toast.LENGTH_SHORT).show();

    }

    public void callLoginActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}