package com.brain.revanth.sampleapplication2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.brain.revanth.sampleapplication2.Fragments.PersonalInfromationFragment;
import com.brain.revanth.sampleapplication2.Fragments.QuestionFive;
import com.brain.revanth.sampleapplication2.Fragments.QuestionFour;
import com.brain.revanth.sampleapplication2.Fragments.QuestionOne;
import com.brain.revanth.sampleapplication2.Fragments.QuestionThree;
import com.brain.revanth.sampleapplication2.Fragments.QuestionTwo;
import com.brain.revanth.sampleapplication2.Services.AlertDialogManager;
import com.brain.revanth.sampleapplication2.Model.ViewPagerAdapter;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class IdeasRegActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ProgressDialog pDialog;
    private AlertDialogManager alert = new AlertDialogManager();
    private PersonalInfromationFragment pi;
    private QuestionOne qone;
    private QuestionTwo qtwo;
    private QuestionThree qthree;
    private QuestionFour qfour;
    private QuestionFive qfive;
    private Button Prev,Next,Submit;
    public String ideaTitle, ideaDescription,ideaRefcode;
    public static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ArrayList<String> Questions = new ArrayList<String>();
    String Questionsarraylist = "";
    public static String registerationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideas_reg);
        pi = new PersonalInfromationFragment();
        qone = new QuestionOne();
        qtwo = new QuestionTwo();
        qthree = new QuestionThree();
        qfour = new QuestionFour();
        qfive = new QuestionFive();
        viewPager =(ViewPager)findViewById(R.id.pager);
        intializepaging();
        Prev = (Button)findViewById(R.id.previous);
        Prev.setOnClickListener(btnListener);
        Next = (Button)findViewById(R.id.next);
        Next.setOnClickListener(btnListener);
        Submit = (Button)findViewById(R.id.submit);
        Submit.setOnClickListener(btnListener);
        Prev.setVisibility(View.INVISIBLE);
        Submit.setVisibility(View.INVISIBLE);
        Next.setVisibility(View.VISIBLE);

        //Coverting Array List To String Object........
        for (String s : Questions)
        {
            Questionsarraylist += s + "\t";
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                switch (arg0){
                    case 0:
                        Prev.setVisibility(View.INVISIBLE);
                        Next.setVisibility(View.VISIBLE);
                        Submit.setVisibility(View.GONE);
                        break;
                    case 1:
                        Prev.setVisibility(View.VISIBLE);
                        Next.setVisibility(View.VISIBLE);
                        Submit.setVisibility(View.GONE);
                        break;
                    case 2:
                        Prev.setVisibility(View.VISIBLE);
                        Next.setVisibility(View.VISIBLE);
                        Submit.setVisibility(View.GONE);
                        break;
                    case 3:
                        Prev.setVisibility(View.VISIBLE);
                        Next.setVisibility(View.VISIBLE);
                        Submit.setVisibility(View.GONE);
                        break;
                    case 4:
                        Prev.setVisibility(View.VISIBLE);
                        Next.setVisibility(View.VISIBLE);
                        Submit.setVisibility(View.GONE);
                        break;
                    case 5:
                        Prev.setVisibility(View.VISIBLE);
                        Next.setVisibility(View.INVISIBLE);
                        Submit.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }
    private void intializepaging() {
        // TODO Auto-generated method stub
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PersonalInfromationFragment(), "");
        adapter.addFragment(new QuestionOne(), "");
        adapter.addFragment(new QuestionTwo(), "");
        adapter.addFragment(new QuestionThree(), "");
        adapter.addFragment(new QuestionFour(), "");
        adapter.addFragment(new QuestionFive(), "");
        viewPager.setAdapter(adapter);
    }
    public View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {

            switch(v.getId()) {
                case R.id.previous:
                    viewPager.setCurrentItem(viewPager.getCurrentItem()-1);
                    break;
                case R.id.next:
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                    break;
                case R.id.submit:
                    ideaTitle = pi.ideatitle;
                    ideaDescription = pi.ideadescr;
                    ideaRefcode = pi.idearefcode;
                    Questions.add(qone.qu1.getText().toString().replace(" ", "%20"));
                    Questions.add(qone.qu2.getText().toString().replace(" ", "%20"));
                    Questions.add(qone.qu3.getText().toString().replace(" ", "%20"));
                    Questions.add(qone.qu4.getText().toString().replace(" ", "%20"));
                    Questions.add(qone.qu5.getText().toString().replace(" ", "%20"));
                    Questions.add(qtwo.qu6.getText().toString().replace(" ", "%20"));
                    Questions.add(qtwo.qu7.getText().toString().replace(" ", "%20"));
                    Questions.add(qtwo.qu8.getText().toString().replace(" ", "%20"));
                    Questions.add(qtwo.qu9.getText().toString().replace(" ", "%20"));
                    Questions.add(qtwo.qu10.getText().toString().replace(" ", "%20"));
                    Questions.add(qthree.qu11.getText().toString().replace(" ", "%20"));
                    Questions.add(qthree.qu12.getText().toString().replace(" ", "%20"));
                    Questions.add(qthree.qu13.getText().toString().replace(" ", "%20"));
                    Questions.add(qthree.qu14.getText().toString().replace(" ", "%20"));
                    Questions.add(qthree.qu15.getText().toString().replace(" ", "%20"));
                    Questions.add(qfour.qu16.getText().toString().replace(" ", "%20"));
                    Questions.add(qfour.qu17.getText().toString().replace(" ", "%20"));
                    Questions.add(qfour.qu18.getText().toString().replace(" ", "%20"));
                    Questions.add(qfour.qu19.getText().toString().replace(" ", "%20"));
                    Questions.add(qfour.qu20.getText().toString().replace(" ", "%20"));
                    Questions.add(qfive.qu21.getText().toString().replace(" ", "%20"));
                    Questions.add(qfive.qu22.getText().toString().replace(" ", "%20"));
                    Questions.add(qfive.qu23.getText().toString().replace(" ", "%20"));
                    Questions.add(qfive.qu24.getText().toString().replace(" ", "%20"));
                    Questions.add(qfive.qu25.getText().toString().replace(" ", "%20"));

                    Intent homeintent = new Intent(IdeasRegActivity.this,LoginActivity.class);
                    startActivity(homeintent);
                    finish();
                    idearegistration(registerationId,ideaTitle,ideaDescription,ideaRefcode,Questionsarraylist);
//
//                   if(PinNum.length() >4 || PinNum.length() <4){
//
//                        alert.showAlertDialog(IdeasRegActivity.this,"Enter 4 digit PinNum Number",false);
//                    }
//                    else {
//
//                        registeruser(ideaTitle, ideaDescription,Email,IdeaName,IdeaDescription,PinNum,Teammembers,Questionsarraylist);
//                    }
            }

        }
    };

    private void idearegistration(String registerationId, String ideaTitle, String ideaDescription, String ideaRefcode, String questionsarraylist) {
    }

    private void registeruser(String leadName, String phone, String email, String ideaName, String ideaDescription, String pinNum, String teammembers,String answers) {
        Ion.with(getApplicationContext())
                .load("http://www.gbiconnect.com/walletbabaservices/createTeam?leadName="+leadName+"&phone="+phone+"&ideaName="+ideaName+"&description="+ideaDescription+"&email="+email+"&pin="+pinNum+"&teamMenbers="+teammembers+"&answers="+answers)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null) {

                        }else {

                            try {
                                JSONObject jSONObject = new JSONObject(result);
                                int status = jSONObject.getInt("status");
                                if (status == 1) {
                                    JSONObject object = jSONObject.getJSONObject("team");
                                    registerationId = object.getString("id");
                                    Intent intent = new Intent(IdeasRegActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(getApplicationContext(), "registered Successfull", Toast.LENGTH_LONG).show();

                                } else {
                                    JSONArray array = jSONObject.getJSONArray("errors");
                                    JSONObject j = array.getJSONObject(0);
                                    String error = j.getString("message");

                                    alert.showAlertDialog(IdeasRegActivity.this,error,false);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
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