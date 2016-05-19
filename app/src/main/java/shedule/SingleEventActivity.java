package shedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;

public class SingleEventActivity extends AppCompatActivity {
    TextView ename,elocation,edate,JoinEvent;
    private Toolbar toolbar;
    public Bundle getBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_event);
        toolbar = (Toolbar) findViewById(R.id.myteamtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ename = (TextView)findViewById(R.id.eventname);
        edate = (TextView)findViewById(R.id.eventdate);
        elocation = (TextView)findViewById(R.id.eventlocation);
        JoinEvent = (TextView)findViewById(R.id.JointEvent);
        getBundle = this.getIntent().getExtras();

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
                JoinEvent.setText("Will Be sent Id");
            }
        });
    }
}
