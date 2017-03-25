package com.brain.revanth.sampleapplication2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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

public class IdeasActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<IdeasCommonClass> arrayList = new ArrayList<IdeasCommonClass>();
    private IdeasCommonClass ideasCommonClass;
    private IdeaListRow ideasListRow;
    AlertDialogManager alert;
    private ProgressDialog pDialog;
    String ideaTitle,ideaId,eventName,status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideas);
        Toolbar toolbar = (Toolbar)findViewById(R.id.ideastoolbar);
        setSupportActionBar(toolbar);
        alert = new AlertDialogManager();
        listView = (ListView)findViewById(R.id.listviewideas);
        ideasCommonClass = new IdeasCommonClass();
        ideasListRow = new IdeaListRow(IdeasActivity.this,arrayList);
        ideasListRow.notifyDataSetChanged();
        peoples();
    }

    private void peoples() {
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please Wait");
        pDialog.setCancelable(false);
        pDialog.show();
        timerDelayRemoveDialog(10*1000,pDialog);
        Ion.with(getApplicationContext())
                .load("http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/allideas")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else{
                            try {
                                JSONArray jSONObject = new JSONArray(result);
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
                                    String statuss = ideasCommonClass.getStatus().toString();
                                        if(statuss.equals("Approved")){
                                            arrayList.add(ideasCommonClass);
                                        }
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

                                Intent singlpeople = new Intent(IdeasActivity.this, SingleIdeaActivity.class);
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


}
