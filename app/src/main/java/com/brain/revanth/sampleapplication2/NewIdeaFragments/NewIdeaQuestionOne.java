package com.brain.revanth.sampleapplication2.NewIdeaFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.brain.revanth.sampleapplication2.R;

public class NewIdeaQuestionOne extends Fragment {
	public  static EditText qu1,qu2,qu3,qu4,qu5;
	public static String Q1,Q2,Q3,Q4,Q5;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
			View view = inflater.inflate(R.layout.newideaquestionone,container,false);
			qu1 = (EditText) view.findViewById(R.id.question1);
			qu2 = (EditText) view.findViewById(R.id.question2);
			qu3 = (EditText) view.findViewById(R.id.question3);
			qu4 = (EditText) view.findViewById(R.id.question4);
			qu5 = (EditText) view.findViewById(R.id.question5);
		Q1 = qu1.getText().toString().trim();
		Q2 = qu2.getText().toString().trim();
		Q3 = qu3.getText().toString().trim();
		Q4 = qu4.getText().toString().trim();
		Q5 = qu5.getText().toString().trim();
			return view;
	}

}
