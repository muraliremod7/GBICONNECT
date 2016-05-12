package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.brain.revanth.sampleapplication2.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionFive extends Fragment {
    public static EditText qu21,qu22,qu23,qu24,qu25,PIN,COP;
    public static String Q21,Q22,Q23,Q24,Q25,Pinnum,Conpin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_question_five, container,false);
        qu21 = (EditText) view.findViewById(R.id.question21);
        qu22 = (EditText) view.findViewById(R.id.question22);
        qu23 = (EditText) view.findViewById(R.id.question23);
        qu24 = (EditText) view.findViewById(R.id.question24);
        qu25 = (EditText) view.findViewById(R.id.question25);
        Q21 = qu21.getText().toString();
        Q22 = qu22.getText().toString();
        Q23 = qu23.getText().toString();
        Q24 = qu24.getText().toString();
        Q25 = qu25.getText().toString();
        PIN = (EditText) view.findViewById(R.id.pinnumber);
        COP = (EditText) view.findViewById(R.id.conformpin);
        Pinnum = PIN.getText().toString();
        Conpin = COP.getText().toString();
        return view;
    }

    // validating email id
    public static boolean isValidpin(String pin) {
        String PIN_PATTERN = "^[_0-9]";
        Pattern pattern = Pattern.compile(PIN_PATTERN);
        Matcher matcher = pattern.matcher(pin);
        return matcher.matches();
    }
}
