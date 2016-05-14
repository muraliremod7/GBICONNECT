package discover;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;


public class SingleServiceFragment extends Fragment {
    TextView ownername,elocation, ownerphone,servicedesc,edesc;
    ImageView backbuttonservice;

    FragmentTransaction fragmentTransaction;
    public static ServicesFragment servicesFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_single, container, false);
        // Inflate the layout for this fragment
    servicesFragment = new ServicesFragment();
        backbuttonservice = (ImageView)view.findViewById(R.id.backbutton);
        ownername = (TextView)view.findViewById(R.id.ownername);
        ownerphone = (TextView)view.findViewById(R.id.ownerphone);

        String OwnerName = getArguments().getString("OwnerName");
        String OwnerPhone = getArguments().getString("PhoneNumber");
        ownername.setText(OwnerName);
        ownerphone.setText(OwnerPhone);

        backbuttonservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

}
