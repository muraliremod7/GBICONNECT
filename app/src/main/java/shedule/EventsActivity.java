package shedule;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;

public class EventsActivity extends AppCompatActivity {
    ListView eventlistView;
    private Toolbar toolbar;
    String[] Eventname = {"Event1","Event2","Event3","Event4"};
    String[] Fromdate = {"03-11-2013","10-12-2015","12-05-2015","03-03-2016"};
    String[] Todate = {"07-11-2013","15-12-2015","17-05-2015","07-03-2016"};
    private static FragmentManager fragmentManager;
    private static SingleEventFragment singleEventFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        toolbar = (Toolbar) findViewById(R.id.myteamtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentManager = getSupportFragmentManager();
        eventlistView = (ListView)findViewById(R.id.eventslistView);
        Eventslistrow eventslistrow = new Eventslistrow(EventsActivity.this,Eventname,Fromdate,Todate);
        eventlistView.setAdapter(eventslistrow);
        eventlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String EventName = ((TextView)view.findViewById(R.id.eventname)).getText().toString();
                String FromDate = ((TextView)view.findViewById(R.id.fromdate)).getText().toString();
                String ToDate = ((TextView)view.findViewById(R.id.todate)).getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("EventName", EventName);
                bundle.putString("FromDate", FromDate);
                bundle.putString("ToDate", ToDate);
                singleEventFragment = new SingleEventFragment();
                singleEventFragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer, singleEventFragment).addToBackStack(null).commit();
            }
        });
    }
}
