package com.brain.revanth.sampleapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.brain.revanth.sampleapplication2.Services.AlertDialogManager;

public class ComplaintsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    Spinner comspinner;
    EditText comeditext;
    Button comsubmit;
    String memberCom,comdesc;
    JSONObject jsonobject;
    ArrayList<String> arrayList = new ArrayList<String>();
    AlertDialogManager dialogManager = new AlertDialogManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        comspinner = (Spinner)findViewById(R.id.complaintsspinner);
        comeditext = (EditText)findViewById(R.id.complaintsmessage);
        comsubmit = (Button)findViewById(R.id.complaintssubmit);
        comsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memberCom = comspinner.getSelectedItem().toString();
                comdesc = comeditext.getText().toString();
                createComplaint(memberCom,comdesc);
            }
        });

        getTeams();


    }
    private void getTeams() {
        Ion.with(getApplicationContext())
                .load("http://www.gbiconnect.com/walletbabaservices/getTeams")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {

                        try {
                            JSONObject jSONObject = new JSONObject(result);
                            int status = jSONObject.getInt("status");
                            if (status == 1) {
                                JSONArray array = jSONObject.getJSONArray("teams");
                                for(int i =0;i<array.length();i++){
                                    JSONObject j = array.getJSONObject(i);
                                    String Leadname = j.getString("leadName");
                                    arrayList.add(Leadname);

                                }
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(ComplaintsActivity.this,android.R.layout.simple_dropdown_item_1line,arrayList);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                comspinner.setAdapter(adapter);


                            } else {

                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

                    }
                });
    }

    public void createComplaint(String toTeam,String Message) {
        Ion.with(getApplicationContext())
                .load("http://www.gbiconnect.com/walletbabaservices/createComplaint?toTeam"+toTeam+"&Message="+Message)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        try {
                            JSONObject jSONObject = new JSONObject(result);
                            int status = jSONObject.getInt("status");
                            if (status == 1) {
                                JSONObject object = jSONObject.getJSONObject("complaint");

                                    String Status = object.getString("status");

                        dialogManager.showAlertDialog(ComplaintsActivity.this,Status,false);

                            } else {

                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }


                    }
                });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
