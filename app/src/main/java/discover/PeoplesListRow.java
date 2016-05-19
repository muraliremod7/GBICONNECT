package discover;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.brain.revanth.sampleapplication2.R;

import java.util.List;


/**
 * Created by Hari Prahlad on 07-05-2016.
 */
public class PeoplesListRow extends BaseAdapter {
    TextView names,ideaname,ideadesc,phonenumber;
    ImageView profilepic;

    private final Activity activity;
    ImageLoader imageloader;
    public List<PeopleCommonClass> peoplelist;
    NetworkImageView profilepict;
    public PeoplesListRow(Activity activity,List<PeopleCommonClass> peoplelist) {
        this.activity = activity;
        this.peoplelist = peoplelist;
//        imageloader = AppController.getInstance().getImageLoader();
    }

    @Override
    public int getCount() {
        return peoplelist.size();
    }

    @Override
    public Object getItem(int location) {
        return peoplelist.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertview= inflater.inflate(R.layout.peopleslistrow, null, true);
//        if (imageloader == null)
//            imageloader = AppController.getInstance().getImageLoader();
        names = (TextView) convertview.findViewById(R.id.ProfileName);
        profilepic = (ImageView)convertview.findViewById(R.id.Profilepicture);
//        profilepict = (NetworkImageView) convertview.findViewById(R.id.Profilepicture);
        ideaname = (TextView)convertview.findViewById(R.id.IdeaName);
        ideadesc = (TextView)convertview.findViewById(R.id.Ideadesc);
        phonenumber = (TextView)convertview.findViewById(R.id.phonenumber);
        PeopleCommonClass peopleCommonClass =(PeopleCommonClass)getItem(position);
        names.setText(peopleCommonClass.getName());
        ideaname.setText(peopleCommonClass.getIdeaName());
        ideadesc.setText(peopleCommonClass.getIdeaDescription());
        phonenumber.setText(peopleCommonClass.getPhoneNumber());
//        profilepict.setImageUrl(peopleCommonClass.getImage(), imageloader);
       profilepic.setImageResource(R.drawable.ic_account_circle_black_36dp);
        return convertview;
    }
}
