package discover;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;

import java.util.List;

import Model.ServicesCommonClass;


/**
 * Created by Hari Prahlad on 07-05-2016.
 */
public class ServicesLisrow extends BaseAdapter {
    TextView id,servicename, ownername, servicedesk,phonenumber,pin;
    private final Activity activity;

    public List<ServicesCommonClass> servicesCommonClassList;

    public ServicesLisrow(Activity activity, List<ServicesCommonClass> servicesCommonClassList) {
        this.activity = activity;
        this.servicesCommonClassList = servicesCommonClassList;

    }

    @Override
    public int getCount() {
        return servicesCommonClassList.size();
    }

    @Override
    public Object getItem(int location) {
        return servicesCommonClassList.get(location);
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
        convertview= inflater.inflate(R.layout.serviceslistrow, null, true);
        id = (TextView) convertview.findViewById(R.id.serviceid);
        servicename = (TextView) convertview.findViewById(R.id.servicename);
        ownername = (TextView)convertview.findViewById(R.id.ownername);
        servicedesk = (TextView)convertview.findViewById(R.id.servicedesc);
        phonenumber = (TextView)convertview.findViewById(R.id.phone);
        pin = (TextView) convertview.findViewById(R.id.pin);
        ServicesCommonClass service =(ServicesCommonClass) getItem(position);
        id.setText(service.getId());
        servicename.setText(service.getServiceName());
        ownername.setText(service.getOwnerName());
        servicedesk.setText(service.getServiceDesk());
        phonenumber.setText(service.getPhone());
        pin.setText(service.getPin());
        return convertview;
    }
}
