package com.brain.revanth.sampleapplication2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.Services.AlertDialogManager;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyIdeasActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<IdeasCommonClass> arrayList = new ArrayList<IdeasCommonClass>();
    private IdeasCommonClass ideasCommonClass;
    private IdeaListRow ideasListRow;
    AlertDialogManager alert;
    private ProgressDialog pDialog;
    String ideaTitle,ideaId,eventName,status;
    public static String registerationId,ideaid;
    public static SharedPreferences regid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ideas);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        alert = new AlertDialogManager();
        listView = (ListView)findViewById(R.id.listviewmyideas);
        ideasCommonClass = new IdeasCommonClass();
        ideasListRow = new IdeaListRow(MyIdeasActivity.this,arrayList);
        ideasListRow.notifyDataSetChanged();
        regid = PreferenceManager.getDefaultSharedPreferences(this);
        registerationId = regid.getString("user_id","0");
        ideaid = regid.getString("ideaId","0");
        peoples();
    }
    private void peoples() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please Wait");
        pDialog.setCancelable(false);
        pDialog.show();
        timerDelayRemoveDialog(10*1000,pDialog);
        Ion.with(getApplicationContext())
                .load("http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/getideabyuserid?userid="+registerationId)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else{
                            try {
                                JSONObject object = new JSONObject(result);
                                JSONArray jSONObject = object.getJSONArray("idea");
                                for(int i =0;i<jSONObject.length();i++){
                                    JSONObject j = jSONObject.getJSONObject(i);
                                    IdeasCommonClass ideasCommonClass = new IdeasCommonClass();

                                    if(j.has("ideatitle")){
                                        ideasCommonClass.setIdeaTitle(j.getString("ideatitle"));
                                    }
                                    if(j.has("_id")){
                                        ideasCommonClass.setId(j.getString("_id"));
                                    }
                                    if(j.has("eventid")){
                                        ideasCommonClass.setEventName(j.getString("eventid").toString());
                                    }
                                    if(j.has("status")){
                                        ideasCommonClass.setStatus(j.getString("status").toString());
                                    }
                                    arrayList.add(ideasCommonClass);
                                }
                                listView.setAdapter(ideasListRow);
                                if(pDialog.isShowing())
                                    pDialog.dismiss();

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                ideaTitle = ((TextView)view.findViewById(R.id.myideaname)).getText().toString();
                                ideaId = ((TextView)view.findViewById(R.id.myideaid)).getText().toString();
                                eventName = ((TextView)view.findViewById(R.id.myeventname)).getText().toString();
                                status = ((TextView)view.findViewById(R.id.mystatus)).getText().toString();

                                Intent singlpeople = new Intent(MyIdeasActivity.this, MyIdeaViewActivity.class);
                                Bundle peoplessbundle = new Bundle();

                                peoplessbundle.putString("ideatitle", ideaTitle);
                                peoplessbundle.putString("ideaid", ideaId);
                                peoplessbundle.putString("eventname", eventName);
                                peoplessbundle.putString("status",status);
                                singlpeople.putExtras(peoplessbundle);
                                if(singlpeople!=null){
                                    startActivity(singlpeople);
                                }
                            }
                        });
                    }

                });
    }

    public void timerDelayRemoveDialog(long time, final ProgressDialog d){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                d.dismiss();
            }
        }, time);

    }
    @Override
    protected void onRestart() {
        ideasListRow.notifyDataSetChanged();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        ideasListRow.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
