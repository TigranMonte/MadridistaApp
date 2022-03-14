package ru.tikodvlp.madridistaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ru.tikodvlp.madridistaapp.quiz.StartingScreenQuizActivity;
import ru.tikodvlp.madridistaapp.support.FeedbackActivity;
import ru.tikodvlp.madridistaapp.support.RealMadridWebActivity;

public class UserProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String EXTRA_SHARE_LINK = "Your application link is here...";
    private static final String EXTRA_SHARE_SUBJECT = "Check out this application for Real Madrid supporters";
    private static final String EXTRA_SHARE_TITLE = "Share Via";

    TextInputLayout fullName, email, password;
    TextView fullNameLabel, usernameLabel;

    String _USERNAME, _NAME, _EMAIL, _PASSWORD;

    DatabaseReference reference;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

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
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My profile");

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.nav_open_drawer, R.string.nav_close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
          reference.child(_USERNAME).child("email").setValue(email.getEditText().getText().toString());
          _EMAIL = email.getEditText().getText().toString();
          return true;
        } else {
           return false;
        }
    }

    private boolean isPasswordChanged() {
        if (!_PASSWORD.equals(password.getEditText().getText().toString())) {
            reference.child(_USERNAME).child("password").setValue(password.getEditText().getText().toString());
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

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.nav_user_profile:
                break;
            case R.id.nav_music:
                intent = new Intent(UserProfileActivity.this, AudioActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_club:
                intent = new Intent(UserProfileActivity.this, WeAreRealActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_quiz:
                intent = new Intent(UserProfileActivity.this, StartingScreenQuizActivity.class);
                startActivity(intent);
                break;
            case R.id.webCommunity:
                intent = new Intent(UserProfileActivity.this, RealMadridWebActivity.class);
                startActivity(intent);
                break;
            case R.id.feedback:
                intent = new Intent(UserProfileActivity.this, FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.share:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, EXTRA_SHARE_SUBJECT);
                intent.putExtra(Intent.EXTRA_TEXT, EXTRA_SHARE_LINK);
                startActivity(Intent.createChooser(intent, EXTRA_SHARE_TITLE));
                break;
            case R.id.logOut:
                intent = new Intent(UserProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }
}