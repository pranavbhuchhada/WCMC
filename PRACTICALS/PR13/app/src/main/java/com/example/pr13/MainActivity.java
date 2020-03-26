package com.example.pr13;

import android.app.PictureInPictureParams;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Rational;
import android.view.Display;

import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

    }
    @Override
    public void onUserLeaveHint () {
        Display d = getWindowManager()
                .getDefaultDisplay();
        Point p = new Point();
        d.getSize(p);
        int width = p.x;
        int height = p.y;

        Rational ratio
                = new Rational(height, width);
        PictureInPictureParams.Builder
                pip_Builder
                = new PictureInPictureParams
                .Builder();
        pip_Builder.setAspectRatio(ratio).build();
        enterPictureInPictureMode(pip_Builder.build());
    }

}
