package com.example.prm_final;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class watchFilmFullScreen extends AppCompatActivity {
    SimpleExoPlayer simpleExoPlayer;
    ImageView bt_fullscreen;
    boolean isFullScreen=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_film_full_screen);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        } else {
            Film film = (Film) bundle.get("filmbest");
            String videopath = "android.resource://" + getPackageName() + "/" + film.getVideo();;
            PlayerView playerView = findViewById(R.id.player);
            ProgressBar progressBar = findViewById(R.id.progress_bar);
            bt_fullscreen = findViewById(R.id.bt_fullscreen);
            simpleExoPlayer = new SimpleExoPlayer.Builder(this).setSeekBackIncrementMs(5000).setSeekForwardIncrementMs(5000).build();
            bt_fullscreen.setOnClickListener(view ->
            {
                if (!isFullScreen) {
                    bt_fullscreen.setImageDrawable(
                            ContextCompat
                                    .getDrawable(getApplicationContext(), R.drawable.ic_baseline_fullscreen_exit_24));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                } else {
                    bt_fullscreen.setImageDrawable(ContextCompat
                            .getDrawable(getApplicationContext(), R.drawable.ic_baseline_fullscreen_exit_24));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                isFullScreen = !isFullScreen;
            });
            playerView.setPlayer(simpleExoPlayer);
            playerView.setKeepScreenOn(true);
            simpleExoPlayer.addListener(new Player.Listener() {
                @Override
                public void onPlaybackStateChanged(int playbackState) {
                    if (playbackState == Player.STATE_BUFFERING) {
                        progressBar.setVisibility(View.VISIBLE);

                    } else if (playbackState == Player.STATE_READY) {
                        progressBar.setVisibility(View.GONE);
                    }

                }
            });
            Uri videoUrl = Uri.parse(videopath);
            MediaItem media = MediaItem.fromUri(videoUrl);
            simpleExoPlayer.setMediaItem(media);
            simpleExoPlayer.prepare();
            simpleExoPlayer.play();
        }
    }
    @Override
    public void onBackPressed()
    {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            bt_fullscreen.performClick();
        }
        else super.onBackPressed();
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        simpleExoPlayer.stop();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        simpleExoPlayer.release();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        simpleExoPlayer.pause();
    }
}