package discover;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;


public class ServicesFragment extends Fragment{

    ListView serviceslistview;
    private static FragmentManager fragmentManager;
    private static SingleServiceFragment singleServiceFragment;
    String[] names = {"Karthik","Abhi","Raghuveer","Murali"};
    String[] service = {"8801021219","7799334202","7207382240","8801344507"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_services, container, false);
        // Inflate the layout for this fragment
        fragmentManager = getFragmentManager();
        serviceslistview = (ListView) view.findViewById(R.id.serviceslist);

        Integer[] imageId = {
                R.drawable.ic_account_circle_black_36dp,
                R.drawable.ic_account_circle_black_36dp,
                R.drawable.ic_account_circle_black_36dp,
                R.drawable.ic_account_circle_black_36dp,
                R.drawable.ic_account_circle_black_36dp,
                R.drawable.ic_account_circle_black_36dp,
                R.drawable.ic_account_circle_black_36dp,
                R.drawable.ic_account_circle_black_36dp,
                R.drawable.ic_account_circle_black_36dp,
                R.drawable.ic_account_circle_black_36dp,
                R.drawable.ic_account_circle_black_36dp

        };
        ListRow listRow = new ListRow(getActivity(), names, service, imageId);
        serviceslistview.setAdapter(listRow);

        serviceslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View servicesview, int i, long l) {
                String OwnerName = ((TextView)servicesview.findViewById(R.id.listtext)).getText().toString();
                String PhoneNumber = ((TextView)servicesview.findViewById(R.id.listtextdate)).getText().toString();
                Bundle servicesbundle = new Bundle();
                servicesbundle.putString("OwnerName", OwnerName);
                servicesbundle.putString("PhoneNumber", PhoneNumber);
                singleServiceFragment = new SingleServiceFragment();
                singleServiceFragment.setArguments(servicesbundle);
                fragmentManager.beginTransaction().replace(R.id.servicescontainer, singleServiceFragment).addToBackStack(null).commit();
            }
        });

        return view;
    }

}
