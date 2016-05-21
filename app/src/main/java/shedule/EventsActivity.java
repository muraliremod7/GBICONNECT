package shedule;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Model.AlertDialogManager;
import Model.EventCommonClass;

public class EventsActivity extends AppCompatActivity {
    ListView eventlistView;
    private Toolbar toolbar;
    EditText neweventname,neweventlocation,neweventdate;
    String EventName,EventLocation,EventDate;
    EventCommonClass eventCommonClass = new EventCommonClass();
    ArrayList<String> eventname = new ArrayList<>();
    private List<EventCommonClass> arrayList = new ArrayList<EventCommonClass>();
    Eventslistrow eventslist;
    Spinner neweventdays;
    private int mYear, mMonth, mDay;
    Button neweventsubmit;
    AlertDialogManager alert = new AlertDialogManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        toolbar = (Toolbar) findViewById(R.id.myteamtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        neweventname = (EditText)findViewById(R.id.neweventname);
        neweventlocation = (EditText)findViewById(R.id.neweventlocation);
        neweventdate = (EditText)findViewById(R.id.neweventdate);
        neweventdays = (Spinner) findViewById(R.id.eventnofdays);
        eventlistView = (ListView)findViewById(R.id.eventslistView);
        neweventsubmit = (Button)findViewById(R.id.neweventcreate);
        eventslist =new Eventslistrow(this,arrayList);
//
//        findViewById(R.id.neweventcreate).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        findViewById(R.id.closeForgotinevents).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View forgotLayout = findViewById(R.id.neweventlayout);
                //forgotLayout.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
                forgotLayout.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        findViewById(R.id.eventlayout).setVisibility(View.VISIBLE);
                    }
                }, 200);
            }
        });
        getEvents();
    }

    public void createEvent(View view) {
        String EventNamee = neweventname.getText().toString();
        String EventLocationn = neweventlocation.getText().toString();
        String EventDatee = neweventdate.getText().toString();
        String EventDayss = neweventdays.getSelectedItem().toString();
        if(EventDatee==null||EventLocationn==null||EventNamee==null){
            alert.showAlertDialog(EventsActivity.this,"Please Enter The Fields",false);
        }
        Ion.with(EventsActivity.this)
                .load("http://www.gbiconnect.com/walletbabaservices/createEvent?eventName="+EventNamee+"&eventLocation="+EventLocationn+"&eventDate="+EventDatee+"&noOfDays="+EventDayss)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {

                        try {
                            JSONObject jSONObject = new JSONObject(result);
                            int status = jSONObject.getInt("status");
                            if (status == 1) {
                                View forgotLayout = findViewById(R.id.neweventlayout);
                                //forgotLayout.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
                                forgotLayout.setVisibility(View.GONE);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        findViewById(R.id.eventlayout).setVisibility(View.VISIBLE);
                                    }
                                }, 200);

                            } else {
                                JSONArray array = jSONObject.getJSONArray("errors");
                                JSONObject j = array.getJSONObject(0);
                                String error = j.getString("message");
                                alert.showAlertDialog(EventsActivity.this,error,false);
                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        eventslist.notifyDataSetChanged();
                    }
                });
    }


    private void getEvents() {
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

    public void setDate(View view){
        getCurrentDate();
    }
    public String getCurrentDate(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        neweventdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        dpd.show();
        return null;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.eventmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.newevent:

                try{

                    View editprofileLayout = findViewById(R.id.neweventlayout);
                    editprofileLayout.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
                    editprofileLayout.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            findViewById(R.id.eventlayout).setVisibility(View.GONE);
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
