package com.brain.revanth.sampleapplication2.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.brain.revanth.sampleapplication2.R;

public class QuestionFive extends Fragment {
    public static EditText qu21,qu22,qu23,qu24,qu25;
    public static String Q21,Q22,Q23,Q24,Q25;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View Qfive = inflater.inflate(R.layout.fragment_question_five, container,false);
        qu21 = (EditText) Qfive.findViewById(R.id.question21);
        qu22 = (EditText) Qfive.findViewById(R.id.question22);
        qu23 = (EditText) Qfive.findViewById(R.id.question23);
        qu24 = (EditText) Qfive.findViewById(R.id.question24);
        qu25 = (EditText) Qfive.findViewById(R.id.question25);
        return Qfive;
    }

}
