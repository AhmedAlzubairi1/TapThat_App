package com.example.macbookair.tap_that;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
//Coded By Ahmed F. Alzubairi

public class instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        TextView rules = (TextView) findViewById(R.id.textView);
        rules.setText("To start the game tap the yellow square. After you start the game two buttons would appear. Click the button that matches the previous background's color. Sounds easy enough doesn't it, but becareful, there is a time constraint in each cycle so you have to click it fast.  To make things more tricky, the time constraint increases when you reach a certain score.  If you click the incorrect button, don't click the right button on time or the background, you lose. Good luck.");
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

