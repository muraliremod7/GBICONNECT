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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import shedule.EventCommonClass;

public class MyideasActivity extends AppCompatActivity {
    ListView idealistview;
    EditText Ideaname,IdeaDescription;
    Button submitidea;
    String[] ideanamess;
    String[] ideadescc;
    ArrayList<String> ideanameslist = new ArrayList<String>();
    ArrayList<String> ideadesclist = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myideas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.myteamtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Ideaname = (EditText)findViewById(R.id.newidea);
        IdeaDescription = (EditText)findViewById(R.id.newideadesc);
        submitidea = (Button)findViewById(R.id.newideasubmit);

        idealistview = (ListView)findViewById(R.id.ideaslistview);

        Ion.with(getApplicationContext())
                .load("http://www.gbiconnect.com/walletbabaservices/GetIdeas")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        try {
                            JSONObject jSONObject = new JSONObject(result);
                                JSONArray array = jSONObject.getJSONArray("ideas");
                                for(int i =0;i<array.length();i++){
                                    JSONObject j = array.getJSONObject(i);
                                    String Ideaname = j.getString("ideaName");
                                    String IdeaDesc = j.getString("ideaDesc");
                                    ideanameslist.add(Ideaname);
                                    ideadesclist.add(IdeaDesc);
                                    ideanamess = ideanameslist.toArray(new String[ideanameslist.size()]);
                                    ideadescc = ideadesclist.toArray(new String[ideadesclist.size()]);
                                }


                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        IdeaListRow ideaListRow = new IdeaListRow(MyideasActivity.this,ideanamess,ideadescc);
                        idealistview.setAdapter(ideaListRow);

                    }
                });
        submitidea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NewIdea = Ideaname.getText().toString();
                String NewIdeaDesc = IdeaDescription.getText().toString();
                updateIdea();
            }
        });

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

    private void updateIdea() {

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
