package shedule;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;

import java.util.List;


/**
 * Created by Hari Prahlad on 07-05-2016.
 */
public class Eventslistrow extends BaseAdapter {
    TextView eventname,date,eventLocation;
    private final Activity activity;
    public List<EventCommonClass> eventslist;
    public Eventslistrow(Activity activity, List<EventCommonClass> eventslist) {
        this.activity = activity;
        this.eventslist = eventslist;
    }
    @Override
    public int getCount() {
        return eventslist.size();
    }

    @Override
    public Object getItem(int location  ) {
        return eventslist.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertview= inflater.inflate(R.layout.eventslistrow, null, true);
        eventname = (TextView) convertview.findViewById(R.id.eventname);
        date = (TextView) convertview.findViewById(R.id.eventdate);
        eventLocation = (TextView)convertview.findViewById(R.id.eventLocation);

        EventCommonClass eventCommonClass = (EventCommonClass) getItem(position);
        eventname.setText(eventCommonClass.getEventName());
        date.setText(eventCommonClass.getEventDate());
        eventLocation.setText(eventCommonClass.getEventLocation());
        return convertview;
    }
}
