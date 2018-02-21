package com.example.macbookair.tap_that;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
//Coded By Ahmed F. Alzubairi

public class highScoreScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score_screen);

        TextView highScoreNumber = (TextView) findViewById(R.id.highScoreNumber);
        TextView currentScoreNumber = (TextView) findViewById(R.id.currentScoreNumber);


       SharedPreferences HighScoreFinalPrim = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editorHigh = HighScoreFinalPrim.edit();
       editorHigh.putInt("HighScore",score.highScore);
        editorHigh.commit();



        String highNumber = ""+ score.highScore;
        String currentNumber = ""+ score.currentScore;
        highScoreNumber.setText(highNumber);
        currentScoreNumber.setText(currentNumber);
        if(!status.ReasonOfLost.equals("")) {
            reasonOfLost();
status.ReasonOfLost = "";
        }
        /*
        String highNumber = ""+ score.highScore;
        String currentNumber = ""+ score.currentScore;
        highScoreNumber.setText(highNumber);
        currentScoreNumber.setText(currentNumber);
*/


    }
    public void sendTOGame (View view){
        Intent intent = new Intent (this,tapThat.class);
        startActivity(intent);
    }
    public void goToMainScreen (View view){
        Intent intent = new Intent (this,mainScreen.class);
        startActivity(intent);
    }
    public void reasonOfLost(){
        Context context = getApplicationContext();

        CharSequence text = "";
        if (status.ReasonOfLost.equals("background")){
            text = "You lost because you touched the background.";
        }
        else if (status.ReasonOfLost.equals("time")){
            text = "You lost because you ran out of time.";
        }
        else  if (status.ReasonOfLost.equals("button")){
            text = "You lost because you touched the wrong button.";
        }
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(getApplicationContext(), text,
                Toast.LENGTH_SHORT).show();
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

    }}
