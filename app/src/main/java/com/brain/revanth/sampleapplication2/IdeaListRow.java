package com.brain.revanth.sampleapplication2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Hari Prahlad on 07-05-2016.
 */
public class IdeaListRow extends ArrayAdapter<IdeasCommonClass> {
    TextView ideaTitle,ideaId,eventName,status;
    public final Activity activity;
    public ArrayList<IdeasCommonClass> ideasCommonClasses;

    public IdeaListRow(Activity activity, ArrayList<IdeasCommonClass> peoplelist) {
        super(activity,0,peoplelist);
        this.activity = activity;
        this.ideasCommonClasses = peoplelist;
    }

    @Override
    public int getCount() {
        return ideasCommonClasses.size();
    }

    @Override
    public IdeasCommonClass getItem(int location) {
        return ideasCommonClasses.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertview, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertview= inflater.inflate(R.layout.ideaslistrow, null, true);
        ideaTitle = (TextView) convertview.findViewById(R.id.myideaname);
        ideaId = (TextView)convertview.findViewById(R.id.myideaid);
        eventName = (TextView)convertview.findViewById(R.id.myeventname);
        status = (TextView)convertview.findViewById(R.id.mystatus);

        IdeasCommonClass commonClass =(IdeasCommonClass)getItem(position);

        ideaTitle.setText(commonClass.getIdeaTitle());
        ideaId.setText(commonClass.getId());
        eventName.setText(commonClass.getEventName());
        status.setText(commonClass.getStatus());

        return convertview;
    }
}
