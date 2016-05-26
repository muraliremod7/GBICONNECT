package discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Model.ServicesCommonClass;


public class ServicesFragment extends Fragment{

    ListView serviceslistview;
    private List<ServicesCommonClass> arrayList = new ArrayList<ServicesCommonClass>();
    ServicesCommonClass service;
    ServicesLisrow servicesLisrow;
    String ServiceId,ServiceName,ServiceDesc,OwnerName,Phone,Pin;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_services, container, false);
        service = new ServicesCommonClass();
        serviceslistview = (ListView) view.findViewById(R.id.serviceslist);
            getservices();
        servicesLisrow = new ServicesLisrow(getActivity(),arrayList);

        return view;
    }

    private void getservices() {
        Ion.with(getContext())
                .load("http://www.gbiconnect.com/walletbabaservices/getServiceProviders")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {

                        try {
                            JSONObject jSONObject = new JSONObject(result);
                            int status = jSONObject.getInt("status");
                            if (status == 1) {
                                JSONArray array = jSONObject.getJSONArray("serviceProviderList");
                                for(int i =0;i<array.length();i++){
                                    JSONObject j = array.getJSONObject(i);
                                    ServicesCommonClass servicesCommonClass = new ServicesCommonClass();
                                    if(j.has("id")){
                                        servicesCommonClass.setId(j.getString("id"));
                                    }
                                    if(j.has("serviceName")){
                                        servicesCommonClass.setServiceName(j.getString("serviceName"));
                                    }
                                    if(j.has("ownerName")){
                                        servicesCommonClass.setOwnerName(j.getString("ownerName").toString());
                                    }
                                    if(j.has("serviceDesc")||!j.isNull("serviceDesc")){

                                        servicesCommonClass.setServiceDesk(j.getString("serviceDesc"));
                                    }
                                    if(j.has("phone")){
                                        servicesCommonClass.setPhone(j.getString("phone"));
                                    }
                                    if(j.has("pin")) {
                                        servicesCommonClass.setPin(j.getString("pin"));
                                    }
                                arrayList.add(servicesCommonClass);
                                }
                                serviceslistview.setAdapter(servicesLisrow);
                            } else {

                            }
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                        serviceslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View servicesview, int i, long l) {
                                ServiceId = ((TextView)servicesview.findViewById(R.id.serviceid)).getText().toString();
                                ServiceName = ((TextView)servicesview.findViewById(R.id.servicename)).getText().toString();
                                OwnerName = ((TextView)servicesview.findViewById(R.id.ownername)).getText().toString();
                                ServiceDesc = ((TextView)servicesview.findViewById(R.id.servicedesc)).getText().toString();
                                Phone = ((TextView)servicesview.findViewById(R.id.phone)).getText().toString();
                                 Intent singleservice = new Intent(getContext(), SingleServiceActivity.class);
                                Bundle servicesbundle = new Bundle();
                                servicesbundle.putString("Serviceid",ServiceId);
                                servicesbundle.putString("ServiceName",ServiceName);
                                servicesbundle.putString("OwnerName", OwnerName);
                                servicesbundle.putString("ServiceDesc",ServiceDesc);
                                servicesbundle.putString("PhoneNumber", Phone);
                                singleservice.putExtras(servicesbundle);
                                startActivity(singleservice);
                            }
                        });
                    }

                });
    }
    }
