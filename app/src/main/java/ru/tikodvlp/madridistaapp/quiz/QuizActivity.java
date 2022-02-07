package ru.tikodvlp.madridistaapp.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.tikodvlp.madridistaapp.R;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}