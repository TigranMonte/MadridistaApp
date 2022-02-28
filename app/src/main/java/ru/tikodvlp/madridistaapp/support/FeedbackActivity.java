package ru.tikodvlp.madridistaapp.support;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import ru.tikodvlp.madridistaapp.R;

public class FeedbackActivity extends AppCompatActivity {

    TextInputLayout editTextName, editTextFeedback;
    Button btnSendFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        editTextFeedback = findViewById(R.id.editTextFeedback);
        editTextName = findViewById(R.id.editTextName);
        btnSendFeedback = findViewById(R.id.btnSendFeedback);

        btnSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/html");
                intent.putExtra(Intent.EXTRA_EMAIL, new String("tigranma@gmail.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from App");
                intent.putExtra(Intent.EXTRA_TEXT, "Name: " + editTextName.getEditText() +
                        "\n Message: " + editTextFeedback.getEditText());
                try {
                    startActivity(Intent.createChooser(intent, "Please select Email"));
                } catch (android.content.ActivityNotFoundException e) {
                    Toast.makeText(FeedbackActivity.this, "There are no Email clients", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}