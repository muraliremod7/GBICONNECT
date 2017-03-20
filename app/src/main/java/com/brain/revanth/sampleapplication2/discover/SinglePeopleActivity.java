package com.brain.revanth.sampleapplication2.discover;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;

public class SinglePeopleActivity extends AppCompatActivity {
    private Toolbar toolbar;
    TextView leadname,ideaname,phonenum,ideadesc,ProfileEmail,location;
    public Bundle getBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_people);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        leadname = (TextView)findViewById(R.id.Leadname);
        ideaname = (TextView)findViewById(R.id.collegename);
        ideadesc = (TextView)findViewById(R.id.IdeaDescription);
        phonenum = (TextView)findViewById(R.id.Phonenumber);
        ProfileEmail = (TextView)findViewById(R.id.profileEmail);
        location = (TextView)findViewById(R.id.location);

        getBundle = this.getIntent().getExtras();
        String Leadname = getBundle.getString("ProfileName");
        String ideaName = getBundle.getString("collegeName");
        String ideaDesc = getBundle.getString("IdeaDesc");
        String Phone = getBundle.getString("ideaDescription");
        String Email = getBundle.getString("Email");
        String locatioN = getBundle.getString("location");
        leadname.setText(Leadname);
        ideaname.setText(ideaName);
        ideadesc.setText(ideaDesc);
        phonenum.setText(Phone);
        ProfileEmail.setText(Email);
        location.setText(locatioN);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
