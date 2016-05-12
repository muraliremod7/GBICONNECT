package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.brain.revanth.sampleapplication2.R;

public class QuestionFour extends Fragment {
    EditText qu16,qu17,qu18,qu19,qu20;
    String Q6,Q7,Q8,Q9,Q10;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
       View view = inflater.inflate(R.layout.fragment_question_four, container,false);
        qu16 = (EditText) view.findViewById(R.id.question16);
        qu17 = (EditText) view.findViewById(R.id.question17);
        qu18 = (EditText) view.findViewById(R.id.question18);
        qu19 = (EditText) view.findViewById(R.id.question19);
        qu20 = (EditText) view.findViewById(R.id.question20);
        return view;
    }

}
