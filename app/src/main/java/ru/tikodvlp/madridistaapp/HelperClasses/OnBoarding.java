package ru.tikodvlp.madridistaapp.HelperClasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.LinearLayout;

import ru.tikodvlp.madridistaapp.R;

public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dots;

    SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        viewPager = findViewById(R.id.slider);
        dots = findViewById(R.id.dots);

        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
    }
}