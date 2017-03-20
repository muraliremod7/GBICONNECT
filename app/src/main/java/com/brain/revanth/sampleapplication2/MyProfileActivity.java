package com.brain.revanth.sampleapplication2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import com.brain.revanth.sampleapplication2.Services.AlertDialogManager;

public class MyProfileActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText epname, epcollege, eplocation, epdesc;
    private TextView imgPath;
    private Button uploadImage,submit;
    AlertDialogManager alert;
    ArrayList<String> arrayList = new ArrayList<>();
    TextView leadname,Phonenumb,email, collegename,location, descri,teamMembers;
    SharedPreferences teamID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        leadname = (TextView)findViewById(R.id.myprofilename);
        Phonenumb = (TextView)findViewById(R.id.myprofilephone);
        email = (TextView)findViewById(R.id.myprofileemail);
        collegename = (TextView)findViewById(R.id.myprofilcollege);
        descri = (TextView)findViewById(R.id.myprofiledesc);
        location = (TextView)findViewById(R.id.myprofilelocation);
        //teamMembers = (TextView)findViewById(R.id.myprofileteammem);
        epname = (EditText)findViewById(R.id.epnewname);
        epcollege = (EditText)findViewById(R.id.epnewcolle);
        eplocation = (EditText)findViewById(R.id.epnewloc);
        epdesc = (EditText)findViewById(R.id.epdesc);
        imgPath = (TextView)findViewById(R.id.imgPath);
        uploadImage = (Button)findViewById(R.id.uploadnewpic);
        submit = (Button)findViewById(R.id.profileiupdate);

        getTeamProfile();
        findViewById(R.id.closeForgotinmyprofile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View forgotLayout = findViewById(R.id.editprofileLayout);
                getSupportActionBar().show();
                //forgotLayout.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
                forgotLayout.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        findViewById(R.id.myprofilelayout).setVisibility(View.VISIBLE);
                    }
                }, 200);
            }
        });

    }

    private void getTeamProfile() {
        teamID = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String Id = teamID.getString("user_id","0");
        Ion.with(getApplicationContext())
                .load("http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/getuserbyid?userid="+Id)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else{
                            try {
                                JSONObject jSONObject = new JSONObject(result);
                                    JSONArray j = jSONObject.getJSONArray("user");
                                for(int i=0;i<=j.length();i++){
                                    JSONObject jsonObject = j.getJSONObject(i);
                                    leadname.setText(jsonObject.getString("username"));
                                    Phonenumb.setText(jsonObject.getString("mobile"));
                                    email.setText(jsonObject.getString("email"));
                                    collegename.setText("college");
                                    location.setText(jsonObject.getString("location"));
                                    descri.setText(jsonObject.getString("description"));
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

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
            case android.R.id.home:
                this.finish();
            case R.id.myprofile:

                try{
                            View editprofileLayout = findViewById(R.id.editprofileLayout);
                    getSupportActionBar().hide();
                            editprofileLayout.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
                            editprofileLayout.setVisibility(View.VISIBLE);
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
