package ru.tikodvlp.madridistaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class AudioActivity extends AppCompatActivity {

    TextView playerPosition, playerDuration;
    SeekBar seekBar;
    ImageView btRew, btPlay, btPause, btFf;

    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    Runnable runnable;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        playerDuration = findViewById(R.id.player_duration);
        playerPosition = findViewById(R.id.player_position);
        seekBar = findViewById(R.id.seek_bar);
        btRew = findViewById(R.id.bt_rew);
        btFf = findViewById(R.id.bt_ff);
        btPause = findViewById(R.id.bt_pause);
        btPlay = findViewById(R.id.bt_play);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Real Madrid Anthem");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mediaPlayer = MediaPlayer.create(this, R.raw.anthem);

        runnable = new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());

                handler.postDelayed(this, 500);
            }
        };

        int duration = mediaPlayer.getDuration();
        String sDuration = convertFormat(duration);
        playerDuration.setText(sDuration);

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPlay.setVisibility(View.GONE);
                btPause.setVisibility(View.VISIBLE);

                mediaPlayer.start();

                seekBar.setMax(mediaPlayer.getDuration());

                handler.postDelayed(runnable, 0);
            }
        });
        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btPause.setVisibility(View.GONE);
                btPlay.setVisibility(View.VISIBLE);

                mediaPlayer.pause();

                handler.removeCallbacks(runnable);
            }
        });
        btFf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get current position of player
                int currentPosition = mediaPlayer.getCurrentPosition();
                // get duration of player
                int duration = mediaPlayer.getDuration();
                // check condition
                if (mediaPlayer.isPlaying() && duration != currentPosition) {
                    // when media is playing and duration is not equal to current position
                    // Fast forward for 5 seconds
                    currentPosition = currentPosition + 5000;
                    // set current position on text view
                    playerPosition.setText(convertFormat(currentPosition));
                    // Set progress on seek bar
                    mediaPlayer.seekTo(currentPosition);
                }
            }
        });
        btRew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get current position of player
                int currentPosition = mediaPlayer.getCurrentPosition();
                // check condition
                if (mediaPlayer.isPlaying() && currentPosition > 5000) {
                    // when media is playing and current position is greater than 5 sec
                    // rewind for 5 sec
                    currentPosition = currentPosition - 5000;
                    // set current position on text view
                    playerPosition.setText(convertFormat(currentPosition));
                    // Set progress on seek bar
                    mediaPlayer.seekTo(currentPosition);
                }
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // check condition
                if (fromUser) {
                    // when drag the seek bar
                    // set progress on seek bar
                    mediaPlayer.seekTo(progress);
                }
                // set current position on text view
                playerPosition.setText(convertFormat(mediaPlayer.getCurrentPosition()));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // hide pause button
                btPause.setVisibility(View.GONE);
                // show play button
                btPlay.setVisibility(View.VISIBLE);
                // set media player to initial position
                mediaPlayer.seekTo(0);
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private String convertFormat(int duration) {
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
