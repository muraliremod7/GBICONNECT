package com.brain.revanth.sampleapplication2.discover;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.brain.revanth.sampleapplication2.LoginActivity;
import com.brain.revanth.sampleapplication2.Services.AlertDialogManager;
import com.brain.revanth.sampleapplication2.R;
import com.brain.revanth.sampleapplication2.Services.ConnectionDetector;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.brain.revanth.sampleapplication2.Model.PeopleCommonClass;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class PeopleFragment extends Fragment {

    private ListView listView;
    private ArrayList<PeopleCommonClass> arrayList = new ArrayList<PeopleCommonClass>();
    PeopleCommonClass peopleCommonClass;
    PeoplesListRow peoplesListRow;
    String Profilename, collegeName,IdeaDesc,PhoneNum,Email,location;
    AlertDialogManager alert;
    ConnectionDetector cd;
    private ProgressDialog pDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people,null);
        peopleCommonClass =new PeopleCommonClass();
        // Inflate the layout for this fragment
        peoples();
        cd = new ConnectionDetector(getActivity());
        listView = (ListView) view.findViewById(R.id.list);
        peoplesListRow = new PeoplesListRow(getActivity(),arrayList);
        alert = new AlertDialogManager();
        return view;
    }
    public void timerDelayRemoveDialog(long time, final ProgressDialog d){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                d.dismiss();
            }
        }, time);

    }
    private void peoples() {
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please Wait");
        pDialog.setCancelable(false);
        pDialog.show();
        timerDelayRemoveDialog(10*1000,pDialog);
        Ion.with(getContext())
                .load("http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else{
                            try {
                                JSONObject jSONObject = new JSONObject(result);
                                    JSONArray array = jSONObject.getJSONArray("gbis");
                                    for(int i =0;i<array.length();i++){
                                        JSONObject j = array.getJSONObject(i);
                                        PeopleCommonClass peopleCommonClass = new PeopleCommonClass();

                                        if(j.has("username")||!j.isNull("username")){
                                            peopleCommonClass.setName(j.getString("username"));
                                        }
                                        if(j.has("description")||!j.isNull("description")){
                                            peopleCommonClass.setIdeaDescription(j.getString("description").toString());
                                        }
                                        if(j.has("img")||!j.isNull("img")){
                                            // any other views you want associated with a profile.
                                            // You're finding the view based from the inflated layout, not the activity layout
                                            String imgname = j.getString("img");
                                            String url = "http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/getimage?filename="+imgname;
                                            try {
                                                Bitmap image = Ion.with(getActivity())
                                                        .load(url)
                                                        .withBitmap()
                                                        .asBitmap()
                                                        .get(10, TimeUnit.SECONDS);
                                                peopleCommonClass.setImage(image);
                                            } catch (InterruptedException e1) {
                                                e1.printStackTrace();
                                            } catch (ExecutionException e1) {
                                                e1.printStackTrace();
                                            } catch (TimeoutException e1) {
                                                e1.printStackTrace();
                                            }


                                        }
                                        if(j.has("mobile")||!j.isNull("mobile")){
                                            peopleCommonClass.setPhoneNumber(j.getString("mobile"));
                                        }
                                        if(j.has("college")||!j.isNull("college")){
                                            peopleCommonClass.setCollegename(j.getString("college"));
                                        }
                                        if(j.has("location")||!j.isNull("location")){
                                            peopleCommonClass.setLocation(j.getString("location"));
                                        }
                                        if(j.has("email")||!j.isNull("email")){
                                            peopleCommonClass.setEmail(j.getString("email"));
                                        }
                                        arrayList.add(peopleCommonClass);
                                    }
                                if(pDialog.isShowing())
                                    pDialog.dismiss();
                                    listView.setAdapter(peoplesListRow);

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Profilename = ((TextView)view.findViewById(R.id.ProfileName)).getText().toString();
                            collegeName = ((TextView)view.findViewById(R.id.Profilecollege)).getText().toString();
                            IdeaDesc = ((TextView)view.findViewById(R.id.Ideadesc)).getText().toString();
                            PhoneNum = ((TextView)view.findViewById(R.id.phonenumber)).getText().toString();
                            Email = ((TextView)view.findViewById(R.id.ProfileEmail)).getText().toString();
                            location = ((TextView)view.findViewById(R.id.Profilelocation)).getText().toString();
                                Intent singlpeople = new Intent(getActivity(), SinglePeopleActivity.class);
                                Bundle peoplessbundle = new Bundle();
                                peoplessbundle.putString("ProfileName", Profilename);
                                peoplessbundle.putString("collegeName", collegeName);
                                peoplessbundle.putString("IdeaDesc", IdeaDesc);
                                peoplessbundle.putString("ideaDescription",PhoneNum);
                                peoplessbundle.putString("location",location);
                                peoplessbundle.putString("Email",Email);
                                singlpeople.putExtras(peoplessbundle);
                                if(singlpeople!=null){
                                    startActivity(singlpeople);
                                }
                            }
                        });
                    }

                });
                }

    @Override
    public void onResume() {
        super.onResume();
                getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
               // adapter.notifyDataSetChanged();
                peoplesListRow.notifyDataSetChanged();
            }
        });
    }
}



