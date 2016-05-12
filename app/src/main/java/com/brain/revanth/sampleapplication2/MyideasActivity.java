package com.brain.revanth.sampleapplication2;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import leaderboard.LbListRow;

public class MyideasActivity extends AppCompatActivity {
    ListView idealistview;

    String[] ideanamess;
    String[] ideadescc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myideas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        idealistview = (ListView)findViewById(R.id.ideaslistview);
        ideanamess = getResources().getStringArray(R.array.lbideanames);
        ideadescc = getResources().getStringArray(R.array.lbideanames);
        IdeaListRow ideaListRow = new IdeaListRow(MyideasActivity.this,ideanamess,ideadescc);
        idealistview.setAdapter(ideaListRow);
        findViewById(R.id.newideasubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View forgotLayout = findViewById(R.id.updateideadlayout);
                //forgotLayout.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
                forgotLayout.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        findViewById(R.id.newideaslayout).setVisibility(View.VISIBLE);
                    }
                }, 200);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.newidea, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.myidea:
                try{
                    findViewById(R.id.myideasprogresslayout).setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return true;
                        }
                    });
                    findViewById(R.id.myidea).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            View editprofileLayout = findViewById(R.id.updateideadlayout);
                            editprofileLayout.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
                            editprofileLayout.setVisibility(View.VISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    findViewById(R.id.newideaslayout).setVisibility(View.GONE);
                                }
                            }, 500);
                        }
                    });


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
