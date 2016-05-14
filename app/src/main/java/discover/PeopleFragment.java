package discover;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.brain.revanth.sampleapplication2.R;

import java.util.ArrayList;
import java.util.HashMap;

public class PeopleFragment extends Fragment{
ListView listView;

    String[] names;
    String[] service;
    ArrayList<HashMap<String, String>> contactList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people, container, false);
        // Inflate the layout for this fragment
        listView = (ListView)view.findViewById(R.id.list);
        contactList = new ArrayList<HashMap<String, String>>();
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
        names = getResources().getStringArray(R.array.listview);
        service = getResources().getStringArray(R.array.services);
        ListRow listRow = new ListRow(getActivity(),names,service,imageId);
        listView.setAdapter(listRow);
        return view;
    }

}
