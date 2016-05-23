package chat;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Model.PeopleCommonClass;
import discover.PeoplesListRow;
import discover.SinglePeopleActivity;

public class ChatActivity extends AppCompatActivity {
    private Toolbar toolbar;
    ListView chatlistview;
    String ID,LEADNAME;
    ArrayList<ChatCommonClass> arrayList = new ArrayList<ChatCommonClass>();
    Chatlistrow chatlistrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        toolbar = (Toolbar) findViewById(R.id.myteamtoolbar);
        setSupportActionBar(toolbar);

        chatlistview = (ListView)findViewById(R.id.chatlistview);
        chatlistrow = new Chatlistrow(ChatActivity.this,arrayList);
        peoples();
    }
    private void peoples() {
        Ion.with(getApplicationContext())
                .load("http://www.gbiconnect.com/walletbabaservices/getTeams")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {

                        try {
                            JSONObject jSONObject = new JSONObject(result);
                            int status = jSONObject.getInt("status");
                            if (status == 1) {
                                JSONArray array = jSONObject.getJSONArray("teams");
                                for(int i =0;i<array.length();i++){
                                    JSONObject j = array.getJSONObject(i);
                                    ChatCommonClass chatClass = new ChatCommonClass();
                                    chatClass.setChatId(j.getString("id"));
                                    chatClass.setChatLeadName(j.getString("leadName"));
                                    arrayList.add(chatClass);
                                }
                                chatlistview.setAdapter(chatlistrow);
                            } else {

                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        chatlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View chatview, int i, long l) {
                                ID = ((TextView)chatview.findViewById(R.id.chatid)).getText().toString();
                                LEADNAME = ((TextView)chatview.findViewById(R.id.chatleadname)).getText().toString();
                                Intent newchat = new Intent(getApplicationContext(), NewMessageActivity.class);
                                Bundle chatbundle = new Bundle();
                                chatbundle.putString("ID", ID);
                                chatbundle.putString("LEADNAME", LEADNAME);
                                newchat.putExtras(chatbundle);
                                startActivity(newchat);
                            }
                        });
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

            case R.id.search:

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
