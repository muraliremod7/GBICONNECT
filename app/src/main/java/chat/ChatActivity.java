package chat;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.ComplaintsActivity;
import com.brain.revanth.sampleapplication2.MyProfileActivity;
import com.brain.revanth.sampleapplication2.MyideasActivity;
import com.brain.revanth.sampleapplication2.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Services.SessionManager;

public class ChatActivity extends AppCompatActivity {
    private Toolbar toolbar;
    ListView chatlistview;
    String ID,LEADNAME;
    ArrayList<ChatCommonClass> arrayList = new ArrayList<ChatCommonClass>();
    Chatlistrow chatlistrow;
    SessionManager sessionManager;
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
    public void showAlertDialog1(Context context, String message, final Boolean status) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle).create();
        // Setting Dialog Message
        alertDialog.setMessage("Confirm Logout");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                sessionManager.logoutUser();
                finish();
                return;

            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                alertDialog.cancel();
                return;
            }
        });
        // Showing Alert Message
        alertDialog.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.logout:
                showAlertDialog1(ChatActivity.this,"Confirm Logout",false);
                return true;
            case R.id.myprofile:
                Intent myprofile = new Intent(ChatActivity.this,MyProfileActivity.class);
                startActivity(myprofile);
                return true;
            case R.id.myideas:
                Intent myideas = new Intent(ChatActivity.this,MyideasActivity.class);
                startActivity(myideas);
                return true;
            case R.id.complaints:
                Intent Complaints = new Intent(ChatActivity.this,ComplaintsActivity.class);
                startActivity(Complaints);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
