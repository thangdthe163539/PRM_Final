package com.example.prm_final;

import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class FilmDetail extends AppCompatActivity {
    TextView filmName, duration, score, date, detail;
    Button category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        } else {
            Film film = (Film) bundle.get("film");

            //Video
            VideoView videoView = findViewById(R.id.videoView);
            String videopath = "android.resource://" + getPackageName() + "/" + film.getVideo();
            Uri uri = Uri.parse(videopath);
            videoView.setVideoURI(uri);
            MediaController mediaController = new MediaController(this);
            videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);
            videoView.start();

            //Get duration
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(this, uri);
            String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            long timeInMillisec = Long.parseLong(time );
            long hours = (timeInMillisec / 1000) / 3600;
            long minutes = (timeInMillisec / 1000) / 60 ;
            String videoDurations = "";
            if (hours > 0) {
                videoDurations = hours + " hours " + minutes + " minutes";
            }
            else {
                videoDurations =  minutes + " minutes";
            }
            retriever.release();
            filmName = findViewById(R.id.filmName);
            filmName.setText(film.getName());
            duration = findViewById(R.id.time);
            duration.setText(videoDurations);
            score = findViewById(R.id.score);
            score.setText(film.getScore()+"(IMDb)");
            date = findViewById(R.id.date);
            date.setText(film.getDate());
            category = findViewById(R.id.type);
            category.setText(film.getCategory());
            detail = findViewById(R.id.detail);
            detail.setText(film.getContent());
            videoView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), watchFilmFullScreen.class);
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("filmbest", film);
                intent.putExtras(bundle1);
                startActivity(intent);
            });
        }
    }
}