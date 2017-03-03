package com.brain.revanth.sampleapplication2.shedule;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

public class SingleEventActivity extends AppCompatActivity {
    TextView ename,elocation,edate,JoinEvent;
    public Bundle getBundle;
    String EventID;
    SharedPreferences teamID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_event);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ename = (TextView)findViewById(R.id.eventname);
        edate = (TextView)findViewById(R.id.eventdate);
        elocation = (TextView)findViewById(R.id.eventlocation);
        JoinEvent = (TextView)findViewById(R.id.JointEvent);
        getBundle = this.getIntent().getExtras();
        EventID = getBundle.getString("EventId");
        String Ename = getBundle.getString("EventName");
        String Edate = getBundle.getString("EventDate");
        String Elocation = getBundle.getString("EventLocation");
        ename.setText(Ename);
        edate.setText(Edate);
        elocation.setText(Elocation);
        // Inflate the layout for this fragment
        JoinEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamID = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String FromId = teamID.getString("teamid","0");
                joinEvent(EventID,FromId);
            }
        });
    }

    private void joinEvent(String eventID, String fromId) {
        Ion.with(getApplicationContext())
                .load("http://www.gbiconnect.com/walletbabaservices/joinEvent?eventId="+eventID+"&teamid="+fromId)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                    if(e!=null){
                    }
                        else {
                        try {
                            JSONObject jsonObject = new JSONObject(result);
                            int status = jsonObject.getInt("status");
                            if (status == 1) {
                                JoinEvent.setText("We Will Send Id");
                            } else {

                            }
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                        }
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
