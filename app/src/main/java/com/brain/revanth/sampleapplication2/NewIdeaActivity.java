package com.brain.revanth.sampleapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

import Model.ViewPagerAdapter;
import NewIdeaFragments.NewIdeaFragment;
import NewIdeaFragments.NewIdeaQuestionFive;
import NewIdeaFragments.NewIdeaQuestionFour;
import NewIdeaFragments.NewIdeaQuestionOne;
import NewIdeaFragments.NewIdeaQuestionThree;
import NewIdeaFragments.NewIdeaQuestionTwo;

public class NewIdeaActivity extends AppCompatActivity {
    NewIdeaFragment ni;
    NewIdeaQuestionOne nqone;
    NewIdeaQuestionTwo nqtwo;
    NewIdeaQuestionThree nqthree;
    NewIdeaQuestionFour nqfour;
    NewIdeaQuestionFive nqfive;
    ViewPager viewPager;
    Button Prev, Next, Submit;
    ArrayList<String> Questions = new ArrayList<String>();
    public String IdeaName, IdeaDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_idea);
        Toolbar toolbar = (Toolbar) findViewById(R.id.myteamtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ni = new NewIdeaFragment();
        nqone = new NewIdeaQuestionOne();
        nqtwo = new NewIdeaQuestionTwo();
        nqthree = new NewIdeaQuestionThree();
        nqfour = new NewIdeaQuestionFour();
        nqfive = new NewIdeaQuestionFive();
        viewPager = (ViewPager) findViewById(R.id.newIdeaviewpager);
        intializepaging();
        Prev = (Button) findViewById(R.id.previous);
        Prev.setOnClickListener(btnListener);
        Next = (Button) findViewById(R.id.next);
        Next.setOnClickListener(btnListener);
        Submit = (Button) findViewById(R.id.createnewIdea);
        Submit.setOnClickListener(btnListener);
        Prev.setVisibility(View.INVISIBLE);
        Submit.setVisibility(View.INVISIBLE);
        Next.setVisibility(View.VISIBLE);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                switch (arg0) {
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
        adapter.addFragment(new NewIdeaFragment(), "");
        adapter.addFragment(new NewIdeaQuestionOne(), "");
        adapter.addFragment(new NewIdeaQuestionTwo(), "");
        adapter.addFragment(new NewIdeaQuestionThree(), "");
        adapter.addFragment(new NewIdeaQuestionFour(), "");
        adapter.addFragment(new NewIdeaQuestionFive(), "");
        viewPager.setAdapter(adapter);
    }


    public View.OnClickListener btnListener = new View.OnClickListener() {
        public void onClick(View v) {

            IdeaName = ni.IN.getText().toString();
            IdeaDescription = ni.ID.getText().toString();


            switch (v.getId()) {
                case R.id.previous:
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                    break;
                case R.id.next:
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    break;
                case R.id.createnewIdea:

                    Questions.add(nqone.qu1.getText().toString());
                    Questions.add(nqone.qu2.getText().toString());
                    Questions.add(nqone.qu3.getText().toString());
                    Questions.add(nqone.qu4.getText().toString());
                    Questions.add(nqone.qu5.getText().toString());
                    Questions.add(nqtwo.qu6.getText().toString());
                    Questions.add(nqtwo.qu7.getText().toString());
                    Questions.add(nqtwo.qu8.getText().toString());
                    Questions.add(nqtwo.qu9.getText().toString());
                    Questions.add(nqtwo.qu10.getText().toString());
                    Questions.add(nqthree.qu11.getText().toString());
                    Questions.add(nqthree.qu12.getText().toString());
                    Questions.add(nqthree.qu13.getText().toString());
                    Questions.add(nqthree.qu14.getText().toString());
                    Questions.add(nqthree.qu15.getText().toString());
                    Questions.add(nqfour.qu16.getText().toString());
                    Questions.add(nqfour.qu17.getText().toString());
                    Questions.add(nqfour.qu18.getText().toString());
                    Questions.add(nqfour.qu19.getText().toString());
                    Questions.add(nqfour.qu20.getText().toString());
                    Questions.add(nqfive.qu21.getText().toString());
                    Questions.add(nqfive.qu22.getText().toString());
                    Questions.add(nqfive.qu23.getText().toString());
                    Questions.add(nqfive.qu24.getText().toString());
                    Questions.add(nqfive.qu25.getText().toString());

                    CeateNewIdea(IdeaName,IdeaDescription,Questions);

            }

        }
    };
    public void CeateNewIdea(String ideaName, String ideaDescription, ArrayList<String> questions){
        Ion.with(getApplicationContext())
                .load("http://www.gbiconnect.com/walletbabaservices/createIdea?ideaname="+ideaName+"&ideadesc="+ideaDescription+"&answers=")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                            try {
                                JSONObject jSONObject = new JSONObject(result);
                                JSONArray jsonArray = jSONObject.getJSONArray("ideas");
                                    JSONObject j = jsonArray.getJSONObject(0);
                                    String message = j.getString("status");
                                Toast.makeText(NewIdeaActivity.this,message,Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(NewIdeaActivity.this, MyideasActivity.class);
                                startActivity(intent);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                });
    }

}

