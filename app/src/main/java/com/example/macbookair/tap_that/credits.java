package com.example.macbookair.tap_that;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//Coded By Ahmed F. Alzubairi
public class credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
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
