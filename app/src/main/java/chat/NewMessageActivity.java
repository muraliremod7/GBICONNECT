package chat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.brain.revanth.sampleapplication2.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

public class NewMessageActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public Bundle getBundle;
    EditText creatingmess;
    String Id,Leadname;
    SharedPreferences teamID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        toolbar = (Toolbar) findViewById(R.id.myteamtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        creatingmess = (EditText)findViewById(R.id.chatting);

        getBundle = this.getIntent().getExtras();
        Id = getBundle.getString("ID");
        Leadname = getBundle.getString("LEADNAME");
    }
    public void createmessage(View view){
         teamID = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String FromId = teamID.getString("teamid","0");
        String Message = creatingmess.getText().toString().replace(" ", "%20");
        String Toid = Id;
        Ion.with(getApplicationContext())
                .load("GET","http://www.gbiconnect.com/walletbabaservices/sentMsgtoteam?fromId=" +FromId+ "&toId=" +Toid+ "&message=" +Message)
//                     http://www.gbiconnect.com/walletbabaservices/sentMsgtoteam?fromId=15&toId=16&message=hii
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

    private void CreateMessage(String fromId, String message, String toid) {

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
