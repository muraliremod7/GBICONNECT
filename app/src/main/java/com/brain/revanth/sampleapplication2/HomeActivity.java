package com.brain.revanth.sampleapplication2;


import android.app.LocalActivityManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.brain.revanth.sampleapplication2.chat.ChatActivity;
import com.brain.revanth.sampleapplication2.discover.DiscoveriActivity;
import com.brain.revanth.sampleapplication2.favorates.FavoritesActivity;
import com.brain.revanth.sampleapplication2.Services.SessionManager;
import com.brain.revanth.sampleapplication2.leaderboard.LeaderBoardActivity;
import com.brain.revanth.sampleapplication2.shedule.SheduleActivity;


public class HomeActivity extends AppCompatActivity{

    // Session Manager Class
    SessionManager session;
    LocalActivityManager localActivityManager;
    public static TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        localActivityManager = new LocalActivityManager(this,false);
        localActivityManager.dispatchCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        if(session.checkLogin())
            finish();
        tabHost = (TabHost)findViewById(android.R.id.tabhost);
            tabHost.setup(localActivityManager);
        Resources resources = getResources();

        // Android tab
        Intent discoverintent = new Intent().setClass(this, DiscoveriActivity.class);
        TabHost.TabSpec discover = tabHost
                .newTabSpec("com/brain/revanth/sampleapplication2/discover")
                .setIndicator("", resources.getDrawable(R.drawable.ic_explore_white_18dp))
                .setContent(discoverintent);
        tabHost.addTab(discover);
        // Apple tab
        Intent chatintent = new Intent().setClass(this, FavoritesActivity.class);
        TabHost.TabSpec Chat = tabHost
                .newTabSpec("com/brain/revanth/sampleapplication2/favorates")

                .setIndicator("", resources.getDrawable(R.drawable.ic_star_rate_white_18dp))
                .setContent(chatintent);
        tabHost.addTab(Chat);

        Intent favoratesintent = new Intent().setClass(this, ChatActivity.class);
        TabHost.TabSpec favorates = tabHost
                .newTabSpec("com/brain/revanth/sampleapplication2/chat")
                .setIndicator("", resources.getDrawable(R.drawable.ic_chat_white_18dp))
                .setContent(favoratesintent);
        tabHost.addTab(favorates);

        Intent sheduleintent = new Intent().setClass(this, SheduleActivity.class);
        TabHost.TabSpec shedule = tabHost
                .newTabSpec("com/brain/revanth/sampleapplication2/shedule")
                .setIndicator("", resources.getDrawable(R.drawable.ic_date_range_white_18dp))
                .setContent(sheduleintent);
        tabHost.addTab(shedule);

        Intent moreintent = new Intent().setClass(this, LeaderBoardActivity.class);
        TabHost.TabSpec More = tabHost
                .newTabSpec("...")
                .setIndicator("", resources.getDrawable(R.drawable.ic_supervisor_account_white_18dp))
                .setContent(moreintent);
        tabHost.addTab(More);

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tab", tabHost.getCurrentTabTag());
    }
    public void showAlertDialog1(Context context,String message, final Boolean status) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle).create();
        // Setting Dialog Message
        alertDialog.setMessage("Confirm Logout");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                session.logoutUser();
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
            showAlertDialog1(HomeActivity.this,"Confirm Logout",false);
                return true;
            case R.id.myprofile:
                Intent myprofile = new Intent(HomeActivity.this,MyProfileActivity.class);
                startActivity(myprofile);
                return true;
            case R.id.myideas:
                Intent myideas = new Intent(HomeActivity.this,MyideasActivity.class);
                startActivity(myideas);
                return true;
            case R.id.complaints:
                Intent Complaints = new Intent(HomeActivity.this,ComplaintsActivity.class);
                startActivity(Complaints);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        localActivityManager.dispatchResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        localActivityManager.dispatchPause(isFinishing());
    }
    }
