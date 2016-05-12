package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.brain.revanth.sampleapplication2.R;

public class QuestionThree extends Fragment {
	EditText qu11,qu12,qu13,qu14,qu15;
	String Q11,Q12,Q13,Q14,Q15;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View view = inflater.inflate(R.layout.questionthree,container,false);
	qu11 = (EditText) view.findViewById(R.id.question11);
	qu12 = (EditText) view.findViewById(R.id.question12);
	qu13 = (EditText) view.findViewById(R.id.question13);
	qu14 = (EditText) view.findViewById(R.id.question14);
	qu15 = (EditText) view.findViewById(R.id.question15);
	return  view;
}
}
