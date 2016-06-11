package com.brain.revanth.sampleapplication2.discover;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;

import java.util.List;

import com.brain.revanth.sampleapplication2.Model.PeopleCommonClass;


/**
 * Created by Hari Prahlad on 07-05-2016.
 */
public class PeoplesListRow extends BaseAdapter {
    TextView names,ideaname,ideadesc,phonenumber,email;
    ImageView profilepic;
    public ImageLoader imageLoader;
    private final Activity activity;
    public List<PeopleCommonClass> peoplelist;
    public PeoplesListRow(Activity activity,List<PeopleCommonClass> peoplelist) {
        this.activity = activity;
        this.peoplelist = peoplelist;
        imageLoader = new ImageLoader(activity.getApplicationContext());
    }

    @Override
    public int getCount() {
        return peoplelist.size();
    }

    @Override
    public Object getItem(int location) {
        return peoplelist.get(location);
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
        convertview= inflater.inflate(R.layout.peopleslistrow, null, true);
        names = (TextView) convertview.findViewById(R.id.ProfileName);
        profilepic = (ImageView)convertview.findViewById(R.id.Profilepicture);
        ideaname = (TextView)convertview.findViewById(R.id.IdeaName);
        ideadesc = (TextView)convertview.findViewById(R.id.Ideadesc);
        phonenumber = (TextView)convertview.findViewById(R.id.phonenumber);
        email = (TextView)convertview.findViewById(R.id.ProfileEmail);
        PeopleCommonClass peopleCommonClass =(PeopleCommonClass)getItem(position);
        names.setText(peopleCommonClass.getName());
        ideaname.setText(peopleCommonClass.getIdeaName());
        ideadesc.setText(peopleCommonClass.getIdeaDescription());
        phonenumber.setText(peopleCommonClass.getPhoneNumber());
        email.setText(peopleCommonClass.getEmail());
//       profilepic.setImageResource(peopleCommonClass.getImage());
        imageLoader.DisplayImage(peopleCommonClass.getImage(),profilepic);
        return convertview;
    }
}
