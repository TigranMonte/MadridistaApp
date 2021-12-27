package ru.tikodvlp.madridistaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout reg_name, reg_username, reg_email, reg_password, reg_phoneNum;
    Button btnEnter, btnToLogIn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        reg_name = findViewById(R.id.reg_name);
        reg_username = findViewById(R.id.reg_username);
        reg_email = findViewById(R.id.reg_email);
        reg_password = findViewById(R.id.reg_password);
        reg_phoneNum = findViewById(R.id.reg_phoneNum);
        btnEnter = findViewById(R.id.btnEnter);
        btnToLogIn = findViewById(R.id.btnToLogIn);

        // save data in FB on click
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                // get all the values
                String name = reg_name.getEditText().getText().toString();
                String username = reg_username.getEditText().getText().toString();
                String email = reg_email.getEditText().getText().toString();
                String password = reg_password.getEditText().getText().toString();
                String phoneNum = reg_phoneNum.getEditText().getText().toString();

                UserHelperClass userHelperClass = new UserHelperClass(name, username, email, password, phoneNum);
                reference.child(phoneNum).setValue(userHelperClass);
            }
        });
    }
}