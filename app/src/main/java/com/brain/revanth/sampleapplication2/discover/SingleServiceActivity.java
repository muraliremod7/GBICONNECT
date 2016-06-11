package com.brain.revanth.sampleapplication2.discover;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.brain.revanth.sampleapplication2.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

public class SingleServiceActivity extends AppCompatActivity {
    private Toolbar toolbar;
    String ServiceId;
    TextView ownername,servicename, ownerphone,servicedesc,sendrequest;
    public Bundle getBundle;
    SharedPreferences teamID;

    public SingleServiceActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_service);
        toolbar = (Toolbar) findViewById(R.id.myteamtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        servicename = (TextView)findViewById(R.id.serviceNAme);
        ownername = (TextView)findViewById(R.id.ownerNAme);
        ownerphone = (TextView)findViewById(R.id.ownerphonenumber);
        servicedesc = (TextView)findViewById(R.id.serviceDesc);
        sendrequest = (TextView)findViewById(R.id.serviceprovidersendrequest);
        getBundle = this.getIntent().getExtras();
        ServiceId = getBundle.getString("Serviceid");
        String ServiceNamee = getBundle.getString("ServiceName");
        String OwnerName = getBundle.getString("OwnerName");
        String ServiceDescc = getBundle.getString("ServiceDesc");
        String OwnerPhone =getBundle.getString("PhoneNumber");
        servicename.setText(ServiceNamee);
        ownername.setText(OwnerName);
        servicedesc.setText(ServiceDescc);
        ownerphone.setText(OwnerPhone);
        sendrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamID = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String TeamId = teamID.getString("teamid","0");
                sendservicerequest(ServiceId,TeamId);
                sendrequest.setText("Your Request Sent Successfully");
            }
        });
        ServicesFragment servicesFragment = new ServicesFragment();


    }

    private void sendservicerequest(String serviceId, String teamId) {
        Ion.with(getApplicationContext())
                .load("http://www.gbiconnect.com/walletbabaservices/addServiceMember?serviceId="+serviceId+"&teamId="+teamId)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null) {
                        }
                        else{
                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                int status = jsonObject.getInt("status");
                                if (status == 1) {
                                    Toast.makeText(SingleServiceActivity.this, "Your Request Sent Successfully", Toast.LENGTH_LONG).show();
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
        switch(item.getItemId()){
            case android.R.id.home:
                this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed()
    {
        this.finish();
    }
}
