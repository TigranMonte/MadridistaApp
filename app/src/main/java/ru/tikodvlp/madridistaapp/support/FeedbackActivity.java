package ru.tikodvlp.madridistaapp.support;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;

import ru.tikodvlp.madridistaapp.R;

public class FeedbackActivity extends AppCompatActivity {

    private static final String EXTRA_EMAIL_FEEDBACK = "tigranma@gmail.com";
    private static final String EXTRA_EMAIL_FEEDBACK_SUBJECT = "Feedback from app";

    TextInputLayout editTextName, editTextFeedback;
    Button btnSendFeedback;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        editTextFeedback = findViewById(R.id.editTextFeedback);
        editTextName = findViewById(R.id.editTextName);
        btnSendFeedback = findViewById(R.id.btnSendFeedback);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Give us a feedback");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/html");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {EXTRA_EMAIL_FEEDBACK});
                intent.putExtra(Intent.EXTRA_SUBJECT, EXTRA_EMAIL_FEEDBACK_SUBJECT);
                intent.putExtra(Intent.EXTRA_TEXT, "Name: " + editTextName.getEditText().getText().toString() +
                        "\n Message: " + editTextFeedback.getEditText().getText().toString());
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