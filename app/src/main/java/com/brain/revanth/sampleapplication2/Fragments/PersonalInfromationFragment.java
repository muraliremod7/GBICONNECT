package com.brain.revanth.sampleapplication2.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.brain.revanth.sampleapplication2.R;

public class PersonalInfromationFragment extends Fragment{
    public static EditText ideaTitle,ideaDesc,ideaReferalcode;
    public static String ideatitle, ideadescr,idearefcode;
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
        ideatitle = ideaTitle.getText().toString().replace("","%20");
        ideadescr = ideaDesc.getText().toString().replace("","%20");
        idearefcode = ideaReferalcode.getText().toString().replace("","%20");
        return view;
    }

}