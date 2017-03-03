package com.brain.revanth.sampleapplication2.leaderboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.brain.revanth.sampleapplication2.Services.AlertDialogManager;
import com.brain.revanth.sampleapplication2.R;

import java.util.ArrayList;

public class LeaderBoardActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> lBrank = new ArrayList<String>();
    ArrayList<String> lBideaname = new ArrayList<String>();
    ArrayList<String> lBmarks = new ArrayList<String>();
    String[] lbrank;
    String[] lbideanames;
    String[] lbmarks;
    LbListRow lbListRow;
    private AlertDialogManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.ledarboardtoolbar);
        setSupportActionBar(toolbar);
        listView = (ListView)findViewById(R.id.lblist);
        manager = new AlertDialogManager();
        manager.showAlertDialog(LeaderBoardActivity.this,"Comming Soon",false);
//        Ion.with(getApplicationContext())
//                .load("http://www.gbiconnect.com/walletbabaservices/getLeaderBoard")
//                .asString()
//                .setCallback(new FutureCallback<String>() {
//                    @Override
//                    public void onCompleted(Exception e, String result) {
//
//                        try {
//                            JSONObject jSONObject = new JSONObject(result);
//                            int status = jSONObject.getInt("status");
//                            if (status == 1) {
//                                JSONArray array = jSONObject.getJSONArray("leaderList");
//                                for(int i =0;i<array.length();i++){
//                                    JSONObject j = array.getJSONObject(i);
//                                    String lbRank = j.getString("rank");
//                                    String lbIdeaname = j.getString("ideaName");
//                                    String lbMarks = j.getString("marks");
//                                    lBrank.add(lbRank);
//                                    lBideaname.add(lbIdeaname);
//                                    lBmarks.add(lbMarks);
//
//                                     lbrank = lBrank.toArray(new String[lBrank.size()]);
//                                    lbideanames = lBideaname.toArray(new String[lBideaname.size()]);
//                                    lbmarks = lBmarks.toArray(new String[lBmarks.size()]);
//                                }
//
//                            } else {
//
//                            }
//                        } catch (JSONException e1) {
//                            e1.printStackTrace();
//                        }
//                        lbListRow = new LbListRow(LeaderBoardActivity.this, lbrank,lbideanames, lbmarks);
//                        //listView.setAdapter(lbListRow);
//                    }
//                });


    }

}
