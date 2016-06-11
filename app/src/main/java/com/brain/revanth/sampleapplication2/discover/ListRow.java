package com.brain.revanth.sampleapplication2.discover;

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
public class ListRow extends ArrayAdapter<String> {
    TextView namess,description;
    private final Activity context;
    private final String[] names;
    private final String[] services;


    public ListRow(Activity context, String[] names, String[] services) {
        super(context, R.layout.peopleslistrow,names);
        this.context = context;
        this.names = names;
        this.services = services;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.peopleslistrow, null, true);
        namess = (TextView) rowView.findViewById(R.id.ProfileName);
        description = (TextView) rowView.findViewById(R.id.IdeaName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.Profilepicture);
        namess.setText(names[position]);
        description.setText(services[position]);
        return rowView;
    }
}
