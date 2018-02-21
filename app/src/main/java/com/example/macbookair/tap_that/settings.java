package com.example.macbookair.tap_that;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
//Coded By Ahmed F. Alzubairi

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        CheckBox musicBox = (CheckBox) findViewById(R.id.musicBox);
        musicBox.setChecked(status.musicStatus);
       // status.undertale =MediaPlayer.create(this,R.raw.undertale);


    }

    public  void toggleMusic (View view){
        CheckBox checkBox = (CheckBox) view;
        if(checkBox.isChecked()){
            status.undertale.setVolume(1,1);
            status.musicStatus=true;
        }
        else{
            status.undertale.setVolume(0,0);
            status.musicStatus = false;
        }
    }

    public void sendTOGame (View view){
        Intent intent = new Intent (this,tapThat.class);
        startActivity(intent);
    }
    public void goToMainScreen (View view){
        Intent intent = new Intent (this,mainScreen.class);
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

    }}
