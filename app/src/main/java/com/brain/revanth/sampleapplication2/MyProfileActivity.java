package com.brain.revanth.sampleapplication2;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

public class MyProfileActivity extends AppCompatActivity {
    private Toolbar toolbar;
    EditText epname,epphone,epemail,epcompany,epposition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        toolbar = (Toolbar) findViewById(R.id.myteamtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        epname = (EditText)findViewById(R.id.epnewname);
        epphone = (EditText)findViewById(R.id.epnewnum);
        epemail = (EditText)findViewById(R.id.epnewemail);
        epcompany = (EditText)findViewById(R.id.epcompany);
        epposition = (EditText)findViewById(R.id.epposition);

        findViewById(R.id.closeForgotinmyprofile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View forgotLayout = findViewById(R.id.editprofileLayout);
                //forgotLayout.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
                forgotLayout.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        findViewById(R.id.myprofilelayout).setVisibility(View.VISIBLE);
                        findViewById(R.id.imagelayout).setVisibility(View.VISIBLE);
                    }
                }, 200);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profileedit, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.myprofile:

                try{
                            View editprofileLayout = findViewById(R.id.editprofileLayout);
                            editprofileLayout.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
                            editprofileLayout.setVisibility(View.VISIBLE);
                            View imagelayout = findViewById(R.id.imagelayout);
                            imagelayout.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
                            imagelayout.setVisibility(View.INVISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    findViewById(R.id.myprofilelayout).setVisibility(View.GONE);
                                }
                            }, 500);


                }catch (NullPointerException e){

                }
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
