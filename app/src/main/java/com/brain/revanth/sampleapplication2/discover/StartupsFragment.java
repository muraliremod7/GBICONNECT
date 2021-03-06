package com.brain.revanth.sampleapplication2.discover;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.brain.revanth.sampleapplication2.R;

public class StartupsFragment extends Fragment{
    ListView listView;
    String[] names;
    String[] service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_startups, container, false);
                // Inflate the layout for this fragment
                listView = (ListView) view.findViewById(R.id.startupslist);
                Integer[] imageId = {
                        R.drawable.ic_account_circle_black_36dp,
                        R.drawable.ic_account_circle_black_36dp,
                        R.drawable.ic_account_circle_black_36dp

                };
                names = getResources().getStringArray(R.array.startup);
                service = getResources().getStringArray(R.array.startuplocation);
                ListRow listRow = new ListRow(getActivity(), names, service);
                listView.setAdapter(listRow);
                return view;
    }

}
