package com.saurabhjadhav.farmhelper;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 3000;

    Animation topAnim, bottomAnim;
    ImageView mainActivityImage;
    TextView mainActivityText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.topanimation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottomanimation);

        mainActivityImage = findViewById(R.id.imageViewBottom);
        mainActivityText = findViewById(R.id.textViewTop);

        mainActivityText.setAnimation(topAnim);
        mainActivityImage.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Dashboard.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(mainActivityImage, "logoImage");
                pairs[1] = new Pair<View, String>(mainActivityText, "logoText");

                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                startActivity(intent, activityOptions.toBundle());
            }
        }, SPLASH_SCREEN);

    }
}