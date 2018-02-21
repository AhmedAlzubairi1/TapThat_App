package com.example.macbookair.tap_that;

import android.app.Application;
//Coded By Ahmed F. Alzubairi

/**
 * Created by macbookair on 6/19/16.
 */
public class MyApplication extends Application {

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    private static boolean activityVisible;
}