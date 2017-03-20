package com.brain.revanth.sampleapplication2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

import com.brain.revanth.sampleapplication2.Model.ViewPagerAdapter;
import com.brain.revanth.sampleapplication2.NewIdeaFragments.NewIdeaFragment;
import com.brain.revanth.sampleapplication2.NewIdeaFragments.NewIdeaQuestionFive;
import com.brain.revanth.sampleapplication2.NewIdeaFragments.NewIdeaQuestionFour;
import com.brain.revanth.sampleapplication2.NewIdeaFragments.NewIdeaQuestionOne;
import com.brain.revanth.sampleapplication2.NewIdeaFragments.NewIdeaQuestionThree;
import com.brain.revanth.sampleapplication2.NewIdeaFragments.NewIdeaQuestionTwo;

public class NewIdeaActivity extends AppCompatActivity {
    private PersonalInfromationFragment pi;
    private QuestionOne qone;
    private QuestionTwo qtwo;
    private QuestionThree qthree;
    private QuestionFour qfour;
    private QuestionFive qfive;
    private ViewPager viewPager;
    public String ideaTitle, ideaDescription,ideaRefcode,eventid;
    Button Prev, Next, Submit;
    ArrayList<String> Questions = new ArrayList<String>();
    public String IdeaName, IdeaDescription;
    public static String registerationId;
    public static SharedPreferences regid;
    String Questionsarraylist = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_idea);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.newpager);
        pi = new PersonalInfromationFragment();
        qone = new QuestionOne();
        qtwo = new QuestionTwo();
        qthree = new QuestionThree();
        qfour = new QuestionFour();
        qfive = new QuestionFive();
        intializepaging();
        Prev = (Button)findViewById(R.id.newprevious);
        Prev.setOnClickListener(btnListener);
        Next = (Button)findViewById(R.id.newnext);
        Next.setOnClickListener(btnListener);
        Submit = (Button)findViewById(R.id.newsubmit);
        Submit.setOnClickListener(btnListener);
        Prev.setVisibility(View.INVISIBLE);
        Submit.setVisibility(View.INVISIBLE);
        Next.setVisibility(View.VISIBLE);
        regid = PreferenceManager.getDefaultSharedPreferences(this);
        registerationId = regid.getString("user_id","0");
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
                    ideaTitle = pi.ideaTitle.getText().toString();
                    ideaDescription = pi.ideaDesc.getText().toString();
                    ideaRefcode = pi.ideaReferalcode.getText().toString();
                    eventid = pi.eventname.getSelectedItem().toString();
                    String que1 = qone.qu1.getText().toString().replace(" ", "%20");
                    String que2 = qone.qu2.getText().toString().replace(" ", "%20");
                    String que3 = qone.qu3.getText().toString().replace(" ", "%20");
                    String que4 = qone.qu4.getText().toString().replace(" ", "%20");
                    String que5 = qone.qu5.getText().toString().replace(" ", "%20");
                    String que6 = qtwo.qu6.getText().toString().replace(" ", "%20");
                    String que7 = qtwo.qu7.getText().toString().replace(" ", "%20");
                    String que8 = qtwo.qu8.getText().toString().replace(" ", "%20");
                    String que9 = qtwo.qu9.getText().toString().replace(" ", "%20");
                    String que10 = qtwo.qu10.getText().toString().replace(" ", "%20");
                    String que11 = qthree.qu11.getText().toString().replace(" ", "%20");
                    String que12 = qthree.qu12.getText().toString().replace(" ", "%20");
                    String que13 = qthree.qu13.getText().toString().replace(" ", "%20");
                    String que14 = qthree.qu14.getText().toString().replace(" ", "%20");
                    String que15 = qthree.qu15.getText().toString().replace(" ", "%20");
                    String que16 = qfour.qu16.getText().toString().replace(" ", "%20");
                    String que17 = qfour.qu17.getText().toString().replace(" ", "%20");
                    String que18 = qfour.qu18.getText().toString().replace(" ", "%20");
                    String que19 = qfour.qu19.getText().toString().replace(" ", "%20");
                    String que20 = qfour.qu20.getText().toString().replace(" ", "%20");
                    String que21 = qfive.qu21.getText().toString().replace(" ", "%20");
                    String que22 = qfive.qu22.getText().toString().replace(" ", "%20");
                    String que23 = qfive.qu23.getText().toString().replace(" ", "%20");
                    String que24 = qfive.qu24.getText().toString().replace(" ", "%20");
                    String que25 = qfive.qu25.getText().toString().replace(" ", "%20");

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

                    if(ideaTitle.equals("")){
                        Toast.makeText(NewIdeaActivity.this,"Enter IdeaTitle",Toast.LENGTH_LONG).show();
                    }
                    else {
                        idearegistration(registerationId,ideaTitle,eventid,ideaDescription,ideaRefcode,que1,que2,que3,que4,que5,que6,que7,que8,que9,que10,que11,que12,que13,que14,que15,que16,que17,que18,que19,que20,que21,que22,que23,que24,que25);

                    }
            }

        }
    };

    private void idearegistration(String registerationId, String ideaTitle,String eventId, String ideaDescription, String ideaRefcode, String que1, String que2, String que3, String que4, String que5, String que6, String que7, String que8, String que9, String que10, String que11, String que12, String que13, String que14, String que15, String que16, String que17, String que18, String que19, String que20, String que21, String que22, String que23, String que24, String que25) {
        Ion.with(getApplicationContext())
                .load("POST", "http://gbi-app.wmiqmh97e9.us-east-1.elasticbeanstalk.com/ideas")
                .setBodyParameter("ideacode", ideaRefcode)
                .setBodyParameter("ideatitle", ideaTitle)
                .setBodyParameter("eventid", eventId)
                .setBodyParameter("userid", registerationId)
                .setBodyParameter("ideadescription", ideaDescription)
                .setBodyParameter("ideaqu1", que1)
                .setBodyParameter("ideaqu2", que2)
                .setBodyParameter("ideaqu3", que3)
                .setBodyParameter("ideaqu4", que4)
                .setBodyParameter("ideaqu5", que5)
                .setBodyParameter("ideaqu6", que6)
                .setBodyParameter("ideaqu7", que7)
                .setBodyParameter("ideaqu8", que8)
                .setBodyParameter("ideaqu9", que9)
                .setBodyParameter("ideaqu10", que10)
                .setBodyParameter("ideaqu11", que11)
                .setBodyParameter("ideaqu12", que12)
                .setBodyParameter("ideaqu13", que13)
                .setBodyParameter("ideaqu14", que14)
                .setBodyParameter("ideaqu15", que15)
                .setBodyParameter("ideaqu16", que16)
                .setBodyParameter("ideaqu17", que17)
                .setBodyParameter("ideaqu18", que18)
                .setBodyParameter("ideaqu19", que19)
                .setBodyParameter("ideaqu20", que20)
                .setBodyParameter("ideaqu21", que21)
                .setBodyParameter("ideaqu22", que22)
                .setBodyParameter("ideaqu23", que23)
                .setBodyParameter("ideaqu24", que24)
                .setBodyParameter("ideaqu25", que25)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        try {
                            if (e != null) {
                                Toast.makeText(NewIdeaActivity.this, result, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(NewIdeaActivity.this, "Idea Registered Successfully", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception ex) {

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

