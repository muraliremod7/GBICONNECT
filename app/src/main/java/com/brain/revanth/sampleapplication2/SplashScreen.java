package com.brain.revanth.sampleapplication2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.os.Bundle;


import com.brain.revanth.sampleapplication2.Services.SessionManager;

public class SplashScreen extends Activity {
    private Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            Intent i = new Intent(SplashScreen.this, HomeActivity.class);
            SplashScreen.this.startActivity(i);
            SplashScreen.this.finish();
        }
    };
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("first_time", false))
        {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("first_time", true);
            editor.commit();
            Intent i = new Intent(SplashScreen.this, SplashScreen.class);
            this.startActivity(i);
            this.finish();
        }
        else
        {
            this.setContentView(R.layout.activity_splash_screen);
            handler.sendEmptyMessageDelayed(0, 2000);
        }

    }
}
