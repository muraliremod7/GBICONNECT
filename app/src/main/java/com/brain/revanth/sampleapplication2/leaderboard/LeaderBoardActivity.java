package com.brain.revanth.sampleapplication2.leaderboard;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.brain.revanth.sampleapplication2.Services.AlertDialogManager;
import com.brain.revanth.sampleapplication2.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LeaderBoardActivity extends AppCompatActivity {
    private ListView listView;
    private Spinner spinner;
    private AlertDialogManager manager;
    private LbListRow lbListRow;
    private ArrayList<LeaderBoardCommonClass> arrayList = new ArrayList<LeaderBoardCommonClass>();
    private ArrayList<String> sparray = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private LeaderBoardCommonClass boardCommonClass;
    private ProgressDialog pDialog;
    private String Eventname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.ledarboardtoolbar);
        setSupportActionBar(toolbar);
        listView = (ListView)findViewById(R.id.leaderboardListview);
        spinner = (Spinner)findViewById(R.id.spinnerlb);
        manager = new AlertDialogManager();
        getEvents();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Eventname = parent.getItemAtPosition(position).toString().replace(" ","%20");
                arrayList.clear();
                leaderboard(Eventname);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void getEvents() {
        Ion.with(getApplicationContext())
                .load("GET","http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/getevents")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else {
                            try{
                                JSONObject jSONObject = new JSONObject(result);
                                JSONArray array = jSONObject.getJSONArray("events");
                                for(int i=0;i<array.length();i++){
                                    JSONObject j = array.getJSONObject(i);
                                    if(j.has("eventname")){
                                        String evename = j.getString("eventname");
                                        sparray.add(evename);
                                    }
                                }
                                adapter = new ArrayAdapter<String>(LeaderBoardActivity.this, R.layout.spinnerstyle);
                                adapter.add("Choose Event");
                                adapter.addAll(sparray);
                                spinner.setAdapter(adapter);
                                adapter.notifyDataSetChanged();

                            }catch (Exception exz){
                                exz.printStackTrace();
                            }
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void leaderboard(String eventname) {
        Ion.with(getApplicationContext())
                .load("http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/ideamarks?eventname="+eventname)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else {
                            try {
                                JSONObject object1 = new JSONObject(result);

                                JSONArray array = object1.getJSONArray("idea");
                                for(int i = 0;i<array.length();i++){
                                    LeaderBoardCommonClass commonClass = new LeaderBoardCommonClass();
                                    JSONObject object = array.getJSONObject(i);
                                    if(object.has("ideaid")){
                                        commonClass.setIdeaId(object.getString("ideaid"));
                                        String ideaTitle = object.getString("ideaid");
                                    }
                                    if(object.has("marks")){
                                        commonClass.setMarks(object.getString("marks"));
                                    }
                                    arrayList.add(commonClass);
                                }
                                listView.setAdapter(lbListRow);
                                lbListRow = new LbListRow(LeaderBoardActivity.this,arrayList);
                                lbListRow.notifyDataSetChanged();

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                });
    }

}
