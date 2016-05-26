package discover;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;

public class SingleServiceActivity extends AppCompatActivity {
    private Toolbar toolbar;
    String ServiceId;
    TextView ownername,servicename, ownerphone,servicedesc;
    public Bundle getBundle;
    ServicesFragment servicesFragment = new ServicesFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_service);
        toolbar = (Toolbar) findViewById(R.id.myteamtoolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        servicename = (TextView)findViewById(R.id.serviceNAme);
        ownername = (TextView)findViewById(R.id.ownerNAme);
        ownerphone = (TextView)findViewById(R.id.ownerphonenumber);
        servicedesc = (TextView)findViewById(R.id.serviceDesc);
        getBundle = this.getIntent().getExtras();
        ServiceId = getBundle.getString("Serviceid");
        String ServiceNamee = getBundle.getString("ServiceName");
        String OwnerName = getBundle.getString("OwnerName");
        String ServiceDescc = getBundle.getString("ServiceDesc");
        String OwnerPhone =getBundle.getString("PhoneNumber");
        servicename.setText(ServiceNamee);
        ownername.setText(OwnerName);
        servicedesc.setText(ServiceDescc);
        ownerphone.setText(OwnerPhone);

    }
}
