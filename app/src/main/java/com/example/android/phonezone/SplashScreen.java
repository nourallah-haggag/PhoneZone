package com.example.android.phonezone;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = findViewById(R.id.imageView);



        // timer
       new CountDownTimer(2000 , 1 ){

            @Override
            public void onTick(long l) {
                //Toast.makeText(SplashScreenActivity.this, "timeeee", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(SplashScreen.this , MainActivity.class);
                // transistion
                Pair[] pairs = new Pair[1];
                pairs[0]= new Pair<View, String>(imageView , "imgTrans");

                //pairs[3]= new Pair<View , String>(layout1 , "layoutTrans");
                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this, pairs).toBundle());


            }
        }.start();

    }

}
