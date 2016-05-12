package leaderboard;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;

/**
 * Created by Hari Prahlad on 08-05-2016.
 */
public class LbListRow extends ArrayAdapter<String> {
    TextView rank, ideanamelb,marks;
    private final Activity context;
    private final String[] lbrank;
    private final String[] lbideanames;
    private final String[] lbmarks;
    public LbListRow(Activity context, String[] lbrank,String[] lbideanames, String[] lbmarks) {
        super(context, R.layout.leaderboardlist_row,lbrank);
        this.context = context;
        this.lbrank = lbrank;
        this.lbideanames = lbideanames;
        this.lbmarks = lbmarks;
    }
    @Override
    public View getView(int lbposition, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.leaderboardlist_row, null, true);
        rank = (TextView) rowView.findViewById(R.id.lbrank);
        ideanamelb = (TextView) rowView.findViewById(R.id.lbideaname);
        marks = (TextView) rowView.findViewById(R.id.lbmarks);
        rank.setText(lbrank[lbposition]);
        ideanamelb.setText(lbideanames[lbposition]);
        marks.setText(lbmarks[lbposition]);
        return rowView;
    }
}
