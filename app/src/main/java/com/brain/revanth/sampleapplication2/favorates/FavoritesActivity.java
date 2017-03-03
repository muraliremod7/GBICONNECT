package com.brain.revanth.sampleapplication2.favorates;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.brain.revanth.sampleapplication2.R;

import com.brain.revanth.sampleapplication2.Model.ViewPagerAdapter;

public class FavoritesActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        toolbar = (Toolbar) findViewById(R.id.favtoolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.favviewpager);
        setupViewPagerfav(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.favtabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPagerfav(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FPeopleFragment(), "PEOPLE");
        adapter.addFragment(new FStartupsFragment(), "STARTUPS");
        viewPager.setAdapter(adapter);

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
