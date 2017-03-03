package com.brain.revanth.sampleapplication2.discover;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.brain.revanth.sampleapplication2.R;


public class MentorsFragment extends Fragment {
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mentors, container, false);
        // Inflate the layout for this fragment
        listView = (ListView) view.findViewById(R.id.speakerslist);
        return view;

    }
}
