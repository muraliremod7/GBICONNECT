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
import android.widget.EditText;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import com.brain.revanth.sampleapplication2.Model.AlertDialogManager;
import com.brain.revanth.sampleapplication2.Model.MyprofileCommonClass;

public class MyProfileActivity extends AppCompatActivity {
    private Toolbar toolbar;
    EditText epname,epphone,epemail,epcompany,epposition;
    AlertDialogManager alert;
    ArrayList<String> arrayList = new ArrayList<>();
    TextView leadname,Phonenumb,IdeaName,IdeaDesc,teamMembers;
    SharedPreferences teamID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        toolbar = (Toolbar) findViewById(R.id.myteamtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        leadname = (TextView)findViewById(R.id.myprofilename);
        Phonenumb = (TextView)findViewById(R.id.myprofilephone);
        IdeaName = (TextView)findViewById(R.id.myprofileideaname);
        IdeaDesc = (TextView)findViewById(R.id.myprofiledesc);
        teamMembers = (TextView)findViewById(R.id.myprofileteammem);
        epname = (EditText)findViewById(R.id.epnewname);
        epphone = (EditText)findViewById(R.id.epnewnum);
        epemail = (EditText)findViewById(R.id.epnewemail);
        epcompany = (EditText)findViewById(R.id.epcompany);
        epposition = (EditText)findViewById(R.id.epposition);

        getTeamProfile();
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

    private void getTeamProfile() {
        teamID = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String Id = teamID.getString("teamid","0");
        Ion.with(getApplicationContext())
                .load("http://www.gbiconnect.com/walletbabaservices/getTeamforProfile?teamId="+Id)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else{
                            try {
                                JSONObject jSONObject = new JSONObject(result);
                                int status = jSONObject.getInt("status");
                                if (status == 1) {
                                    JSONObject j = jSONObject.getJSONObject("team");
                                    MyprofileCommonClass aClass = new MyprofileCommonClass();
                                    aClass.setLeadname(j.getString("leadName"));
                                    aClass.setPhone(j.getString("phone"));
                                    aClass.setIdeaname(j.getString("ideaName"));
                                    aClass.setIdeaDescription(j.getString("ideaDescription"));
                                    JSONArray com = j.getJSONArray("teamMembers");
                                    for (int n = 0; n < com.length(); n++) {
                                        JSONObject jsonobject = com.getJSONObject(n);
                                        String membername = jsonobject.getString("memeberName");
                                        arrayList.add(membername);
                                    }
                                    String MemberName = "";
                                    for (String s : arrayList)
                                    {
                                        MemberName += s + "\t";
                                    }
                                    aClass.setTeamMembers(MemberName);

                                    leadname.setText(aClass.getLeadname());
                                    Phonenumb.setText(aClass.getPhone());
                                    IdeaName.setText(aClass.getIdeaname());
                                    IdeaDesc.setText(aClass.getIdeaDescription());
                                    teamMembers.setText(aClass.getTeamMembers());

                                } else {
                                    JSONArray array = jSONObject.getJSONArray("errors");
                                    JSONObject j = array.getJSONObject(0);
                                    String error = j.getString("message");

                                    alert.showAlertDialog(MyProfileActivity.this,error,false);
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
