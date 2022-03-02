package ru.tikodvlp.madridistaapp.support;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import ru.tikodvlp.madridistaapp.R;

public class RealMadridWebActivity extends AppCompatActivity {

    TextView tvVisitNews, tvVisitWebSite, tvVisitSocialMedia, tvHalaMadrid;
    Button btnVisitNews, btnVisitWebSite;
    ImageView ivLogo, ivInstagram, ivTwitter, ivYoutube;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_madrid_web);

        tvVisitNews = findViewById(R.id.tvVisitNews);
        tvVisitWebSite = findViewById(R.id.tvVisitWebsite);
        tvVisitSocialMedia = findViewById(R.id.tvVisitSocialMedia);
        tvHalaMadrid = findViewById(R.id.tvHalaMadrid);
        btnVisitNews = findViewById(R.id.btnVisitNews);
        btnVisitWebSite = findViewById(R.id.btnVisitWebsite);
        ivLogo = findViewById(R.id.ivLogo);
        ivInstagram = findViewById(R.id.ivInstagram);
        ivTwitter = findViewById(R.id.ivTwitter);
        ivYoutube = findViewById(R.id.ivYoutube);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Real Madrid Web Community");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btnVisitNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://www.marca.com/futbol/real-madrid.html?intcmp=MENUESCU&s_kw=realmadrid");
            }
        });
        btnVisitWebSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://www.realmadrid.com/aficion/madridistas/internacionales");
            }
        });
        ivTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://twitter.com/realmadrid");
            }
        });
        ivInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://www.instagram.com/realmadrid/");
            }
        });
        ivYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUrl("https://www.youtube.com/c/realmadrid");
            }
        });
    }

    private void goToUrl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}