package shedule;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;


/**
 * Created by Hari Prahlad on 07-05-2016.
 */
public class Eventslistrow extends ArrayAdapter<String> {
    TextView eventname,fromdate,todate,location,desc;
    private final Activity context;
    private final String[] Eventname;
    private final String[] Fromdate;
    private final String[] Todate;


    public Eventslistrow(Activity context, String[] Eventname, String[] Fromdate, String[] Todate) {
        super(context, R.layout.eventslistrow,Eventname);
        this.context = context;
        this.Eventname = Eventname;
        this.Fromdate = Fromdate;
        this.Todate = Todate;


    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.eventslistrow, null, true);
        eventname = (TextView) rowView.findViewById(R.id.eventname);
        fromdate = (TextView) rowView.findViewById(R.id.fromdate);
        todate = (TextView) rowView.findViewById(R.id.todate);
        eventname.setText(Eventname[position]);
        fromdate.setText(Fromdate[position]);
        todate.setText(Todate[position]);
        return rowView;
    }
}
