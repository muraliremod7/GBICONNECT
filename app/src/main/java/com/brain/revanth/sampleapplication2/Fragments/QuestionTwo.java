package com.brain.revanth.sampleapplication2.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.brain.revanth.sampleapplication2.R;

public class QuestionTwo extends Fragment {
	public static EditText qu6, qu7, qu8, qu9, qu10;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View Qtwo = inflater.inflate(R.layout.questiontwo, container, false);
		qu6 = (EditText) Qtwo.findViewById(R.id.question6);
		qu7 = (EditText) Qtwo.findViewById(R.id.question7);
		qu8 = (EditText) Qtwo.findViewById(R.id.question8);
		qu9 = (EditText) Qtwo.findViewById(R.id.question9);
		qu10 = (EditText) Qtwo.findViewById(R.id.question10);
		return Qtwo;

	}
}
