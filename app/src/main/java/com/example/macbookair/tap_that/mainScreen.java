package com.example.macbookair.tap_that;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
//Coded By Ahmed F. Alzubairi

public class mainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
      if(status.firstTimeMenu) {
          status.undertale = MediaPlayer.create(this, R.raw.victory);
          status.undertale.start();
          status.undertale.setLooping(true);
      status.firstTimeMenu=false;
      }
        SharedPreferences highScoreFinalPrim = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        highScoreFinalPrim = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        score.highScore=highScoreFinalPrim.getInt("HighScore",0);







    }
    public void goToHighScore (View view){
        score.currentScoreTemp = 0;
        if (score.currentScore > score.highScore) {
            score.highScore = score.currentScore;
        }
        Intent intent= new Intent(this,highScoreScreen.class);
        startActivity(intent);

    }

    public void goToInstructions (View view){
        Intent intent= new Intent(this,instructions.class);
        startActivity(intent);
    }
    public void goToSettings (View view){
        Intent intent= new Intent(this,settings.class);
        startActivity(intent);
    }
public void goToTapThat(View view){
    Intent intent = new Intent (this,tapThat.class);
    startActivity(intent);
}
    public void goToCredits(View view){
        Intent intent = new Intent (this,credits.class);
        startActivity(intent);
    }
    protected void onResume() {
        super.onResume();
        MyApplication.activityResumed();
        if(status.musicStatus){
            status.undertale.setVolume(1,1);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MyApplication.activityPaused();
if(status.musicStatus){
    status.undertale.setVolume(0,0);
}

    }
}
