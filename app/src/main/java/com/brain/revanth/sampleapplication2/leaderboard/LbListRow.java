package com.brain.revanth.sampleapplication2.leaderboard;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;

import java.util.ArrayList;

/**
 * Created by Hari Prahlad on 08-05-2016.
 */
public class LbListRow extends ArrayAdapter<LeaderBoardCommonClass> {
    private TextView lbideaTitle,lbmarks;
    public final Activity context;
    ArrayList<LeaderBoardCommonClass> boardCommonClasses;
    public LbListRow(Activity context,ArrayList<LeaderBoardCommonClass> leaderBoardCommonClasses) {
        super(context,0,leaderBoardCommonClasses);
        this.context = context;
        this.boardCommonClasses = leaderBoardCommonClasses;
    }
    @Override
    public int getCount() {
        return boardCommonClasses.size();
    }

    @Override
    public LeaderBoardCommonClass getItem(int location) {
        return boardCommonClasses.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int lbposition, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.leaderboardlist_row, null, true);
        lbideaTitle = (TextView) rowView.findViewById(R.id.lbideaTitle);
        lbmarks = (TextView) rowView.findViewById(R.id.lbmarks);

        LeaderBoardCommonClass boardCommonClass =(LeaderBoardCommonClass)getItem(lbposition);

        lbideaTitle.setText(boardCommonClass.getIdeaId());
        lbmarks.setText(boardCommonClass.getMarks());

        return rowView;
    }

    public void updateList(ArrayList<LeaderBoardCommonClass> arrayList) {
        this.boardCommonClasses= arrayList;

        //and call notifyDataSetChange
        notifyDataSetChanged();
    }
}
