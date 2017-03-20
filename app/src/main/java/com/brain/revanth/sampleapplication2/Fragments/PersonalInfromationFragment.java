package com.brain.revanth.sampleapplication2.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.brain.revanth.sampleapplication2.R;
import com.brain.revanth.sampleapplication2.events.EventCommonClass;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PersonalInfromationFragment extends Fragment{
    public static EditText ideaTitle,ideaDesc,ideaReferalcode;
    public static Spinner eventname;
    public static String ideatitle, ideadescr,idearefcode,eveName;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    public  PersonalInfromationFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.personalinformation, container, false);
        ideaTitle = (EditText)view.findViewById(R.id.idearegTitle);
        ideaDesc = (EditText)view.findViewById(R.id.idearegDesc);
        ideaReferalcode = (EditText)view.findViewById(R.id.idearegReferal);
        eventname = (Spinner)view.findViewById(R.id.eventid);
        ideatitle = ideaTitle.getText().toString().replace("","%20");
        ideadescr = ideaDesc.getText().toString().replace("","%20");
        idearefcode = ideaReferalcode.getText().toString().replace("","%20");
        getEvents();
        return view;
    }

    private void getEvents() {
        Ion.with(getActivity())
                .load("GET","http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/getevents")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else {
                            try{
                                JSONObject jSONObject = new JSONObject(result);
                                JSONArray array = jSONObject.getJSONArray("events");
                                for(int i=0;i<=jSONObject.length();i++){
                                    JSONObject j = array.getJSONObject(i);
                                    EventCommonClass commonClass = new EventCommonClass();
//                                    if(j.has("_id")||!j.isNull("_id")){
//                                        commonClass.setEventId(j.getString("_id"));
//                                    }
                                    if(j.has("eventname")||!j.isNull("eventname")){
                                        commonClass.setEventName(j.getString("eventname"));
                                        String evename = j.getString("eventname");
                                        arrayList.add(evename);
                                    }

//                                    if(j.has("eventlocation")||!j.isNull("eventlocation")){
//                                        commonClass.setEventLocation(j.getString("eventlocation"));
//                                    }
//                                    if(j.has("ecollege")||!j.isNull("ecollege")){
//                                        commonClass.setCollegeName(j.getString("ecollege"));
//                                    }
//                                    if(j.has("startdate")||!j.isNull("startdate")){
//                                        commonClass.setStartDate(j.getString("startdate"));
//                                    }
//                                    if(j.has("enddate")||!j.isNull("enddate")){
//                                        commonClass.setEndDate(j.getString("enddate"));
//                                    }
//                                    if(j.has("eventdescription")||!j.isNull("eventdescription")){
//                                        commonClass.setEventDescription(j.getString("eventdescription"));
//                                    }
                                }
                                String[] locationname = arrayList.toArray(new String[arrayList.size()]);
                                adapter = new ArrayAdapter<String>(getActivity(), R.layout.spinnerstyle);
                                adapter.add("Choose Event");
                                adapter.addAll(locationname);
                                eventname.setAdapter(adapter);
                            }catch (Exception exz){
                                exz.printStackTrace();
                            }
                        }
                    }
                });
    }

}