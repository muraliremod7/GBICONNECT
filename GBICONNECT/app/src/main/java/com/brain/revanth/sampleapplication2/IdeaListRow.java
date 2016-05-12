package com.brain.revanth.sampleapplication2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Hari Prahlad on 08-05-2016.
 */
public class IdeaListRow extends ArrayAdapter<String> {
    TextView ideanames, ideadesc;
    private final Activity context;
    private final String[] ideanamess;
    private final String[] ideadescc;
    public IdeaListRow(Activity context, String[] ideanamess, String[] ideadescc) {
        super(context, R.layout.ideaslistrow,ideanamess);
        this.context = context;
        this.ideanamess = ideanamess;
        this.ideadescc = ideadescc;

    }
    @Override
    public View getView(int lbposition, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.ideaslistrow, null, true);
        ideanames = (TextView) rowView.findViewById(R.id.ideaname);
        ideadesc = (TextView) rowView.findViewById(R.id.myideadecription);
        ideanames.setText(ideanamess[lbposition]);
        ideadesc.setText(ideadescc[lbposition]);
        return rowView;
    }
}
