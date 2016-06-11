package com.brain.revanth.sampleapplication2.NewIdeaFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.brain.revanth.sampleapplication2.R;

public class NewIdeaFragment extends Fragment {
    public static EditText IN, ID;

    public NewIdeaFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.newidea, container, false);

        IN = (EditText) view.findViewById(R.id.ideaname);
        ID = (EditText) view.findViewById(R.id.ideadescription);

        return view;
    }
    }