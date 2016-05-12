package leaderboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.brain.revanth.sampleapplication2.R;

public class LeaderBoardActivity extends AppCompatActivity {
    ListView listView;

    String[] lbrank = {"1","2","5","4","3","6","9","8","50","25","13"};
    String[] lbideanames;
    String[] lbmarks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = (ListView)findViewById(R.id.lblist);
        lbideanames = getResources().getStringArray(R.array.lbideanames);
        lbmarks = getResources().getStringArray(R.array.lbranks);
        LbListRow lbListRow = new LbListRow(LeaderBoardActivity.this, lbrank,lbideanames, lbmarks);
        listView.setAdapter(lbListRow);
    }

}
