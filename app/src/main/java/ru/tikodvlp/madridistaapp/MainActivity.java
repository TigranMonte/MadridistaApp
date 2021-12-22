package ru.tikodvlp.madridistaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int SPLASH_SCREEN = 3000;

    Animation topAnimation;
    ImageView ivLogo;
    TextView tvWelcome, tvMadridista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        ivLogo = (ImageView) findViewById(R.id.ivLogo);
        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        tvMadridista = (TextView) findViewById(R.id.tvMadridista);

        ivLogo.setAnimation(topAnimation);
        tvWelcome.setAnimation(topAnimation);
        tvMadridista.setAnimation(topAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, TopLevelActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}