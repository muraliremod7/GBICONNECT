package com.brain.revanth.sampleapplication2.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

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

import com.brain.revanth.sampleapplication2.Model.PeopleCommonClass;

public class PeopleFragment extends Fragment {
    private ListView listView;
    private List<PeopleCommonClass> arrayList = new ArrayList<PeopleCommonClass>();
    PeopleCommonClass peopleCommonClass;
    PeoplesListRow peoplesListRow;
    String Profilename,IdeaName,IdeaDesc,PhoneNum,Email;
    AlertDialogManager alert;
    ConnectionDetector cd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_people,null);
        peopleCommonClass =new PeopleCommonClass();
        // Inflate the layout for this fragment
        //peoples();
        cd = new ConnectionDetector(getActivity());
        listView = (ListView) view.findViewById(R.id.list);
        peoplesListRow = new PeoplesListRow(getActivity(),arrayList);
        peoplesListRow.notifyDataSetChanged();
        alert = new AlertDialogManager();
        return view;
    }
    private void peoples() {
        Ion.with(getContext())
                .load("http://www.gbiconnect.com/walletbabaservices/getTeams")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){
                            if (!cd.isNetworkOn(getActivity())) {
                                // Internet Connection is Present
                                // make HTTP requests
                                alert.showAlertDialog(getActivity(),"Sorry There is Network Problem",false);
                            }

                        }else{
                            try {
                                JSONObject jSONObject = new JSONObject(result);
                                int status = jSONObject.getInt("status");
                                if (status == 1) {
                                    JSONArray array = jSONObject.getJSONArray("teams");
                                    for(int i =0;i<array.length();i++){
                                        JSONObject j = array.getJSONObject(i);
                                        PeopleCommonClass peopleCommonClass = new PeopleCommonClass();
                                        if(j.has("leadName")||!j.isNull("leadName")){
                                            peopleCommonClass.setName(j.getString("leadName"));
                                        }
                                        if(j.has("ideaName")||!j.isNull("ideaName")){
                                            peopleCommonClass.setIdeaName(j.getString("ideaName").toString());
                                        }
                                        if(j.has("ideaDescription")||!j.isNull("ideaDescription")){
                                            peopleCommonClass.setIdeaDescription(j.getString("ideaDescription").toString());
                                        }
                                        if(j.has("profile")||!j.isNull("profile")){
                                            peopleCommonClass.setImage(j.getString("profile"));
                                        }
                                        if(j.has("phone")||!j.isNull("phone")){
                                            peopleCommonClass.setPhoneNumber(j.getString("phone"));
                                        }
                                        if(j.has("email")||!j.isNull("email")){
                                            peopleCommonClass.setEmail(j.getString("email"));
                                        }
                                        arrayList.add(peopleCommonClass);
                                    }
                                    listView.setAdapter(peoplesListRow);

                                } else {

                                }

                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                        }
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Profilename = ((TextView)view.findViewById(R.id.ProfileName)).getText().toString();
                            IdeaName = ((TextView)view.findViewById(R.id.IdeaName)).getText().toString();
                            IdeaDesc = ((TextView)view.findViewById(R.id.Ideadesc)).getText().toString();
                            PhoneNum = ((TextView)view.findViewById(R.id.phonenumber)).getText().toString();
                                Email = ((TextView)view.findViewById(R.id.ProfileEmail)).getText().toString();
                                Intent singlpeople = new Intent(getActivity(), SinglePeopleActivity.class);
                                Bundle peoplessbundle = new Bundle();
                                peoplessbundle.putString("ProfileName", Profilename);
                                peoplessbundle.putString("IdeaName", IdeaName);
                                peoplessbundle.putString("IdeaDesc", IdeaDesc);
                                peoplessbundle.putString("ideaDescription",PhoneNum);
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

}



