package discover;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;

import org.w3c.dom.Text;

public class SinglePeopleActivity extends AppCompatActivity {
    private Toolbar toolbar;
    TextView leadname,ideaname,phonenum,ideadesc,ProfileEmail;
    public Bundle getBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_people);
        toolbar = (Toolbar) findViewById(R.id.myteamtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        leadname = (TextView)findViewById(R.id.Leadname);
        ideaname = (TextView)findViewById(R.id.IdeaNamee);
        ideadesc = (TextView)findViewById(R.id.IdeaDescription);
        phonenum = (TextView)findViewById(R.id.Phonenumber);
        ProfileEmail = (TextView)findViewById(R.id.profileEmail);
        getBundle = this.getIntent().getExtras();
        String Leadname = getBundle.getString("ProfileName");
        String ideaName = getBundle.getString("IdeaName");
        String ideaDesc = getBundle.getString("IdeaDesc");
        String Phone = getBundle.getString("PhoneNumber");
        String Email = getBundle.getString("Email");
        leadname.setText(Leadname);
        ideaname.setText(ideaName);
        ideadesc.setText(ideaDesc);
        phonenum.setText(Phone);
        ProfileEmail.setText(Email);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profileedit, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.myprofile:

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
