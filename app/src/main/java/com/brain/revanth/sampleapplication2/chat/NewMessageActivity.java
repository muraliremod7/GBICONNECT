package com.brain.revanth.sampleapplication2.chat;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.brain.revanth.sampleapplication2.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class NewMessageActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public Bundle getBundle;
    EditText creatingmess;
    String Id,Leadname;
    SharedPreferences teamID;
    ListView msglistview;
    public static ArrayList<ChatMessage> chatlist;
    public static ChatAdapter chatAdapter;
    String Message,FromId,Toid;
    private Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        toolbar = (Toolbar) findViewById(R.id.myteamtoolbar);
        setSupportActionBar(toolbar);
        random = new Random();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        creatingmess = (EditText)findViewById(R.id.chatting);
        msglistview = (ListView)findViewById(R.id.msgListView);
        creatingmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teamID = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                FromId = teamID.getString("teamid","0");
                Message = creatingmess.getText().toString().replaceAll(" ","%20");
                Toid = Id;
                if(Message.equals("")) {
                    Toast.makeText(NewMessageActivity.this,"Enter Message Here",Toast.LENGTH_LONG).show();
                }
                else {
                    createmessage(FromId, Message, Toid);
                }
            }
        });
        msglistview.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        msglistview.setStackFromBottom(true);
        chatlist = new ArrayList<ChatMessage>();
        chatAdapter = new ChatAdapter(NewMessageActivity.this, chatlist);
        msglistview.setAdapter(chatAdapter);
        getBundle = this.getIntent().getExtras();
        Id = getBundle.getString("ID");
        Leadname = getBundle.getString("LEADNAME");
    }
    public void createmessage(String fromId, String message, String toid){
        Ion.with(getApplicationContext())
                .load("GET","http://www.gbiconnect.com/walletbabaservices/sentMsgtoteam?fromId=" +fromId+ "&toId=" +toid+ "&message=" +message)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null) {

                        } else {

                            try {
                                JSONObject jsonObject = new JSONObject(result);
                                int status = jsonObject.getInt("status");
                                if (status == 1) {
                                    JSONObject object = jsonObject.getJSONObject("message");
                                    String Message = object.getString("meaasge");
                                    final ChatMessage chatMessage = new ChatMessage(FromId, Toid,
                                            Message, "" + random.nextInt(1000), true);
                                    if(!Message.equalsIgnoreCase(" ")) {
                                        chatMessage.setMsgID();
                                        chatMessage.body = Message;
                                        chatMessage.Date = CommonMethods.getCurrentDate();
                                        chatMessage.Time = CommonMethods.getCurrentTime();
                                        creatingmess.setText("");
                                        chatAdapter.add(chatMessage);
                                        chatAdapter.notifyDataSetChanged();
                                    }
                                    Toast.makeText(NewMessageActivity.this, Message, Toast.LENGTH_LONG).show();
                                } else {

                                }
                            } catch (JSONException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

//            case R.id.search:
//
//                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
