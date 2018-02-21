package com.example.macbookair.tap_that;
import android.content.Context;
import android.content.Intent;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class tapThat extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
//Coded By Ahmed F. Alzubairi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_that);
        RelativeLayout.LayoutParams parms = new RelativeLayout.LayoutParams(30, 40);
        parms.leftMargin = 50;
        parms.topMargin = 60;
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.backgroundGame);
        relativeLayout.setBackgroundColor(Color.argb(255, 0, 0, 255));
        //blue
        //change 0,0,255
        ImageButton playButton = (ImageButton) findViewById(R.id.buttonPrime);
        playButton.setImageResource(R.drawable.yellow);
        TextView scoreDisplay = (TextView) findViewById(R.id.scoreDisplay);
        scoreDisplay.setText(""+score.currentScoreTemp);

//change to yellow
  /*     String z = "" + playButton.getX();
        Log.i(z, "WORKER: ");
        z = "" + playButton.getX()+1;
        Log.i(z, "WORKER: ");
*/
    }


    public void lost(View view) {
        status.isLost = true;
        xol.cancel();
        xol30.cancel();
        xol45.cancel();
        score.currentScoreTemp = 0;
        if (score.currentScore > score.highScore) {
            score.highScore = score.currentScore;

        }
        Intent intent = new Intent(this, highScoreScreen.class);
        startActivity(intent);
        status.firstTime=true;


    }

    public void lostTimer() {
        status.ReasonOfLost="time";
        status.isLost = true;
        xol.cancel();
        xol30.cancel();
        xol45.cancel();

        score.currentScoreTemp = 0;
        if (score.currentScore > score.highScore) {
            score.highScore = score.currentScore;
        }
        status.firstTime=true;
        Intent intent = new Intent(this, highScoreScreen.class);
        startActivity(intent);


    }

   // TextView timeDisplay = (TextView) findViewById(R.id.timerDisplay);

    CountDownTimer xol = new CountDownTimer(4000, 1000) {
        public void onTick(long millisUnitlFinished) {
          /*  TextView timeDisplay = (TextView) findViewById(R.id.timerDisplay);
            timeDisplay.setText("" + millisUnitlFinished / 1000
                    + "." + millisUnitlFinished % 1000);*/
        }

        public void onFinish() {
            lostTimer();
        }
    };
    CountDownTimer xol30 = new CountDownTimer(2000, 1000) {
        public void onTick(long millisUnitlFinished) {
        }

        public void onFinish() {
            lostTimer();
        }
    };
    CountDownTimer xol45 = new CountDownTimer(1000, 1000) {
        public void onTick(long millisUnitlFinished) {
        }

        public void onFinish() {
            lostTimer();
        }
    };

    public void random(View view) {
        TextView startDisplay = (TextView) findViewById(R.id.startDisplay);
        if(startDisplay.getText().equals(status.startText)){
            startDisplay.setText("");
        }
        if (score.currentScoreTemp < 30) {
            xol.cancel();
            xol.start();
        } else if (score.currentScoreTemp >= 30 && score.currentScoreTemp<45) {
            xol.cancel();
            xol30.cancel();
            xol30.start();
            timerDecreaseNotification();
        }
        else if (score.currentScoreTemp >= 45) {
           xol.cancel();
            xol30.cancel();
            xol45.cancel();
            xol45.start();
            timerDecreaseNotification();
        }

        if (status.isLost = true) {
            score.currentScore = 1;
            status.isLost = false;
        }

        score.currentScoreTemp = score.currentScoreTemp + 1;
        score.currentScore = score.currentScoreTemp;

        TextView scoreDisplay = (TextView) findViewById(R.id.scoreDisplay);
        scoreDisplay.setText(""+score.currentScoreTemp);
        ImageButton playButton = (ImageButton) findViewById(R.id.buttonPrime);
        ImageButton dummyButton = (ImageButton) findViewById(R.id.buttonDummy);

        randomizer(playButton);
        randomizer(dummyButton);
        int buttonWidth = dummyButton.getWidth();
        int buttonHeight = dummyButton.getHeight();
        int dummyX = (int) dummyButton.getX();
        int dummyY = (int) dummyButton.getY();
        int playButtonX = (int) playButton.getX();
        int playButtonY = (int) playButton.getY();

        while (Math.abs(dummyX - playButtonX) < buttonWidth || Math.abs(dummyY - playButtonY) < buttonHeight) {
            randomizer(dummyButton);
            randomizer(playButton);
             dummyX = (int) dummyButton.getX();
             dummyY = (int) dummyButton.getY();
             playButtonX = (int) playButton.getX();
             playButtonY = (int) playButton.getY();
        }
        if(status.firstTime){
            playButton.setImageResource(R.drawable.blue);
            colorButton="blue";
            ifBlue(dummyButton);
            status.firstTime=false;


        }
        else {
            changePlayButton(playButton);
            changeDummyButton(dummyButton);

        }
        changeBackground();

/*
***********************************code to change button color
ImageButton playButton = (ImageButton) findViewById(R.id.buttonPrime);
            playButton.setImageResource(R.drawable.pink);
            int z =  ((ColorDrawable) relativeLayout.getBackground()).getColor();
            String newV = ""+ z;

            Log.i("Ahmed 1 ",newV);
*******************************code to change button color****************************************/



    }
    String colorButton = "yellow";
String colorDummy = "pink";
    public void changeBackground(){
        ArrayList<String> colorSafe=new ArrayList<>();
        colorSafe.add("blue");
        colorSafe.add("green");
        colorSafe.add("lightgreen");
        colorSafe.add("pink");
        colorSafe.add("red");
        colorSafe.add("yellow");
        colorSafe.remove(colorDummy);
        colorSafe.remove(colorButton);
        int randomColorNumber=(int)(Math.random()*colorSafe.size());
      String randomColor=  colorSafe.get(randomColorNumber);
        if(randomColor.equals("yellow")){
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.backgroundGame);
            relativeLayout.setBackgroundColor(Color.argb(255, 255, 255, 0));
            status.lastRed = 255;
            status.lastGreen = 255;
            status.lastBlue = 0;
        }
else if (randomColor.equals("red")){
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.backgroundGame);
            relativeLayout.setBackgroundColor(Color.argb(255, 255, 0, 0));
            status.lastRed = 255;
            status.lastGreen = 0;
            status.lastBlue = 0;
        }
        else if (randomColor.equals("pink")){
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.backgroundGame);
            relativeLayout.setBackgroundColor(Color.argb(255, 255, 0, 255));
            status.lastRed = 255;
            status.lastGreen = 0;
            status.lastBlue = 255;
        }
        else if (randomColor.equals("lightgreen")){
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.backgroundGame);
            relativeLayout.setBackgroundColor(Color.argb(255, 0, 255, 255));
            status.lastRed = 0;
            status.lastGreen = 255;
            status.lastBlue = 255;
        }
        else if (randomColor.equals("green")){
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.backgroundGame);
            relativeLayout.setBackgroundColor(Color.argb(255, 0, 255, 0));
            status.lastRed = 0;
            status.lastGreen = 255;
            status.lastBlue = 0;
        }
        else  {
            RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.backgroundGame);
            relativeLayout.setBackgroundColor(Color.argb(255, 0, 0, 255));
            status.lastRed = 0;
            status.lastGreen = 0;
            status.lastBlue = 255;
        }

colorSafe.add(colorButton);
        colorSafe.add(colorDummy);
    }

    public void lostViaBackground(View view){
        status.ReasonOfLost="background";
        lost(view);
    }
public void lostViaButton(View view){
    status.ReasonOfLost = "button";
    lost(view);
}
    public void randomizer(View view) {
 /*   int x1 = (int) (Math.random() * 680) + 1;
        int y1 = (int) (Math.random() * 1200) + 1;
        view.setX(x1);
        view.setY(y1);
*/
        int width;
        int height;
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
             width = size.x;
             height = size.y;

        int x1 = (int) (Math.random() * (width - view.getWidth()));
        int y1 = (int) (Math.random() * (height - view.getHeight() - getSupportActionBar().getHeight()));
        view.setX(x1);
        view.setY(y1);

    }
    public void changePlayButton(ImageButton playButton) {
        if (status.lastRed == 0 && status.lastGreen == 0 && status.lastBlue == 255) {
//blue
            playButton.setImageResource(R.drawable.blue);
            colorButton="blue";

        }
       else  if (status.lastRed == 255 && status.lastGreen == 255 && status.lastBlue == 0) {
            //yellow
            playButton.setImageResource(R.drawable.yellow);
            colorButton="yellow";

        } else if (status.lastRed == 255 && status.lastGreen == 0 && status.lastBlue == 0) {
//red
            playButton.setImageResource(R.drawable.red);
            colorButton="red";

        } else if (status.lastRed == 255 && status.lastGreen == 0 && status.lastBlue == 255) {
//pink
            playButton.setImageResource(R.drawable.pink);
            colorButton="pink";
        } else if (status.lastRed == 0 && status.lastGreen == 255 && status.lastBlue == 255) {
//lightgreen
            playButton.setImageResource(R.drawable.lightgreen);
            colorButton="lightgreen";
        } else if (status.lastRed == 0 && status.lastGreen == 255 && status.lastBlue == 0) {
//green
            playButton.setImageResource(R.drawable.green);
            colorButton="green";
        }
    }
    public void changeDummyButton(View view) {
        ImageButton dummyButton = (ImageButton) view;
        if (status.lastRed == 255 && status.lastGreen == 255 && status.lastBlue == 0) {
            //yellow
ifYellow(dummyButton);
        } else if (status.lastRed == 255 && status.lastGreen == 0 && status.lastBlue == 0) {
//red
ifRed(dummyButton);
        } else if (status.lastRed == 255 && status.lastGreen == 0 && status.lastBlue == 255) {
//pink
ifPink(dummyButton);
        } else if (status.lastRed == 0 && status.lastGreen == 255 && status.lastBlue == 255) {
//lightgreen
ifLightGreen(dummyButton);
        } else if (status.lastRed == 0 && status.lastGreen == 255 && status.lastBlue == 0) {
//green
ifGreen(dummyButton);
        } else if (status.lastRed == 0 && status.lastGreen == 0 && status.lastBlue == 255) {
//blue
ifBlue(dummyButton);
        }



    }
public void ifYellow(View view){
    ImageButton dummyChange = (ImageButton) view;
    int wow = (int) (Math.random() * 5) + 1;

     if (wow==1) {
//red
         dummyChange.setImageResource(R.drawable.red);
         colorDummy="red";

    } else if (wow==2) {
//pink
         dummyChange.setImageResource(R.drawable.pink);
         colorDummy="pink";

    } else if (wow==3) {
//lightgreen
         dummyChange.setImageResource(R.drawable.lightgreen);
         colorDummy="lightgreen";

    } else if (wow==4) {
//green
         dummyChange.setImageResource(R.drawable.green);
         colorDummy="green";

    } else if (wow==5) {
//blue
         dummyChange.setImageResource(R.drawable.blue);
         colorDummy="blue";

    }
}
    public void ifRed(View view){
        ImageButton dummyChange = (ImageButton) view;
        int wow = (int) (Math.random() * 5) + 1;

        if (wow==1) {
//yellow
            dummyChange.setImageResource(R.drawable.yellow);
            colorDummy="yellow";

        } else if (wow==2) {
//pink
            dummyChange.setImageResource(R.drawable.pink);
            colorDummy="pink";

        } else if (wow==3) {
//lightgreen
            dummyChange.setImageResource(R.drawable.lightgreen);
            colorDummy="lightgreen";

        } else if (wow==4) {
//green
            dummyChange.setImageResource(R.drawable.green);
            colorDummy="green";

        } else if (wow==5) {
//blue
            dummyChange.setImageResource(R.drawable.blue);
            colorDummy="blue";

        }
    }
    public void ifPink(View view){
        ImageButton dummyChange = (ImageButton) view;
        int wow = (int) (Math.random() * 5) + 1;

        if (wow==1) {
//red
            dummyChange.setImageResource(R.drawable.red);
            colorDummy="red";
        } else if (wow==2) {
//pink
            dummyChange.setImageResource(R.drawable.yellow);
            colorDummy="yellow";
        } else if (wow==3) {
//lightgreen
            dummyChange.setImageResource(R.drawable.lightgreen);
            colorDummy="lightgreen";

        } else if (wow==4) {
//green
            dummyChange.setImageResource(R.drawable.green);
            colorDummy="green";

        } else if (wow==5) {
//blue
            dummyChange.setImageResource(R.drawable.blue);
            colorDummy="blue";

        }
    }
    public void ifLightGreen(View view){
        ImageButton dummyChange = (ImageButton) view;
        int wow = (int) (Math.random() * 5) + 1;

        if (wow==1) {
//red
            dummyChange.setImageResource(R.drawable.red);
            colorDummy="red";

        } else if (wow==2) {
//pink
            dummyChange.setImageResource(R.drawable.pink);
            colorDummy="pink";

        } else if (wow==3) {
//lightgreen
            dummyChange.setImageResource(R.drawable.yellow);
            colorDummy="yellow";

        } else if (wow==4) {
//green
            dummyChange.setImageResource(R.drawable.green);
            colorDummy="green";

        } else if (wow==5) {
//blue
            dummyChange.setImageResource(R.drawable.blue);
            colorDummy="blue";

        }
    }
    public void ifGreen(View view){
        ImageButton dummyChange = (ImageButton) view;
        int wow = (int) (Math.random() * 5) + 1;

        if (wow==1) {
//red
            dummyChange.setImageResource(R.drawable.red);
            colorDummy="red";
        } else if (wow==2) {
//pink
            dummyChange.setImageResource(R.drawable.pink);
            colorDummy="pink";

        } else if (wow==3) {
//lightgreen
            dummyChange.setImageResource(R.drawable.lightgreen);
            colorDummy="lightgreen";

        } else if (wow==4) {
//green
            dummyChange.setImageResource(R.drawable.yellow);
            colorDummy="yellow";

        } else if (wow==5) {
//blue
            dummyChange.setImageResource(R.drawable.blue);
            colorDummy="blue";

        }
    }
    public void ifBlue(View view){
        ImageButton dummyChange = (ImageButton) view;
        int wow = (int) (Math.random() * 5) + 1;

        if (wow==1) {
//red
            dummyChange.setImageResource(R.drawable.red);
            colorDummy="red";

        } else if (wow==2) {
//pink
            dummyChange.setImageResource(R.drawable.pink);
            colorDummy="pink";

        } else if (wow==3) {
//lightgreen
            dummyChange.setImageResource(R.drawable.lightgreen);
            colorDummy="lightgreen";

        } else if (wow==4) {
//green
            dummyChange.setImageResource(R.drawable.green);
            colorDummy="green";
        } else if (wow==5) {
//blue
            dummyChange.setImageResource(R.drawable.yellow);
            colorDummy="yellow";

        }
    }
    public void timerDecreaseNotification(){
        Context context = getApplicationContext();

        CharSequence text = " Timer Decreased \n Hurry up, you have less time";
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

    }
}
