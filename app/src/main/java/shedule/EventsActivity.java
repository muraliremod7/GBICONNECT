package shedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity {
    ListView eventlistView;
    private Toolbar toolbar;
    String EventName,EventLocation,EventDate;
    EventCommonClass eventCommonClass = new EventCommonClass();
    ArrayList<String> eventname = new ArrayList<>();
    private List<EventCommonClass> arrayList = new ArrayList<EventCommonClass>();
    Eventslistrow eventslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        toolbar = (Toolbar) findViewById(R.id.myteamtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        eventlistView = (ListView)findViewById(R.id.eventslistView);
        eventslist =new Eventslistrow(this,arrayList);
        Ion.with(EventsActivity.this)
                .load("GET","http://www.gbiconnect.com/walletbabaservices/getEvents")
                .asString()//(new TypeToken<List<EventCommonClass>>(){})
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {

                        try {
                            JSONObject jSONObject = new JSONObject(result);
                            int status = jSONObject.getInt("status");
                            if (status == 1) {
                                JSONArray array = jSONObject.getJSONArray("eventList");
                                for(int i =0;i<array.length();i++){
                                    JSONObject j = array.getJSONObject(i);
                                    EventCommonClass event  = new EventCommonClass();
                                    String eventName = j.getString("eventName");
                                    event.setEventName(eventName);
                                    event.setEventLocation(j.getString("eventLocation"));
                                    event.setEventDate(j.getString("eventDate"));
                                    arrayList.add(event);
                                }

                                eventlistView.setAdapter(eventslist);
                            } else {

                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
//                        arrayList = result;
                        eventslist.notifyDataSetChanged();
                    }
                });
            eventlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    EventName = ((TextView)view.findViewById(R.id.eventname)).getText().toString();
                    EventDate = ((TextView)view.findViewById(R.id.eventdate)).getText().toString();
                    EventLocation = ((TextView)view.findViewById(R.id.eventLocation)).getText().toString();
                    Intent singlevent = new Intent(getApplicationContext(), SingleEventActivity.class);
                    Bundle servicesbundle = new Bundle();
                    servicesbundle.putString("EventName", EventName);
                    servicesbundle.putString("EventDate", EventDate);
                    servicesbundle.putString("EventLocation", EventLocation);
                    singlevent.putExtras(servicesbundle);
                    startActivity(singlevent);
                }
            });

    }
}
