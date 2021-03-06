package com.brain.revanth.sampleapplication2;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONObject;

import com.brain.revanth.sampleapplication2.Services.AlertDialogManager;
import com.brain.revanth.sampleapplication2.Services.ConnectionDetector;
import com.brain.revanth.sampleapplication2.Services.SessionManager;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView closebutton;
    Button login, Getpin;
    private ProgressDialog pDialog;
    public static TextInputLayout phoneNumber,pinNumber,forgotPin;
    private TextView register;
    private String phone,pin;
    private AlertDialogManager alert = new AlertDialogManager();
    private ConnectionDetector cd;
    private SharedPreferences.Editor editor;
    private SessionManager session;
    public static String registerationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Session Manager
        session = new SessionManager(getApplicationContext());
        //CALL Connection Detector
        cd = new ConnectionDetector(getApplicationContext());
        closebutton = (ImageView)findViewById(R.id.closeForgot);
        register = (TextView)findViewById(R.id.accountcreate);
        phoneNumber = (TextInputLayout)findViewById(R.id.PhoneNum);
        pinNumber = (TextInputLayout)findViewById(R.id.PinNum);
        forgotPin = (TextInputLayout)findViewById(R.id.forgotpin);
        login = (Button)findViewById(R.id.signin);
        login.setOnClickListener(this);
        Getpin = (Button)findViewById(R.id.request);
        Getpin.setOnClickListener(this);
                //After Clicking Register Button Navigating Register Activity....
        register.setOnClickListener(this);
            try{
                //This IS For FofgotLayout....
                findViewById(R.id.forgotpassword).setOnClickListener(this);
                findViewById(R.id.closeForgot).setOnClickListener(this);
            }catch (NullPointerException e){

            }
    }

    private void forgotPin(String phone) {
        Ion.with(getApplicationContext())
                .load("","http://ec2-52-91-248-133.compute-1.amazonaws.com:8080/")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!= null){

                        }else {

                            try {
                                JSONObject jSONObject = new JSONObject(result);
                                int status = jSONObject.getInt("status");
                                if (status == 1) {

                                    alert.showAlertDialog(LoginActivity.this,"Your Pin Has Been Changed",false);
                                } else {
                                    JSONArray array = jSONObject.getJSONArray("errors");
                                    JSONObject j = array.getJSONObject(0);
                                    String error = j.getString("message");
                                    if(pDialog.isShowing())
                                        pDialog.dismiss();
                                    alert.showAlertDialog(LoginActivity.this,error,false);
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
    }
    public void timerDelayRemoveDialog(long time, final ProgressDialog d){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                d.dismiss();
            }
        }, time);

    }
    private void login(final String phone, final String pin) {
        pDialog = new ProgressDialog(LoginActivity.this);
        pDialog.setMessage("Attempting Login");
        pDialog.setCancelable(false);
        pDialog.show();
        timerDelayRemoveDialog(10*1000,pDialog);
        Ion.with(getApplicationContext())
                .load("POST","http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/login")
                .setBodyParameter("mobile",phone)
                .setBodyParameter("pin",pin)
                        .asString()
                        .setCallback(new FutureCallback<String>() {
                            @Override
                            public void onCompleted(Exception e, String result) {
                                if (e != null) {

                                } else {
                                    try {
                                        JSONObject jSONObject = new JSONObject(result);
                                            if(pDialog.isShowing())
                                                pDialog.dismiss();
                                            session.createLoginSession(phone,pin);
                                            registerationId = jSONObject.getString("user_id");
                                            SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                            editor = settings.edit();
                                            editor.putString("user_id", registerationId);
                                            editor.commit();
                                            Toast.makeText(getApplicationContext(), "Welcome To GBI Connect", Toast.LENGTH_LONG).show();
                                            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            // Add new Flag to start new Activity
                                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(i);
                                            finish();
//
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                        }
                    }
                });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();

            //moveTaskToBack(false);

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected void exitByBackKey() {

        AlertDialog alertbox = new AlertDialog.Builder(this,R.style.MyAlertDialogStyle)
                .setMessage("Do you want to exit application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                        //close();


                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    // do something when the button is clicked
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.cancel();
                    }
                })
                .show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!cd.isNetworkOn(getApplicationContext())) {
            // Internet Connection is Present
            // make HTTP requests
            alert.showAlertDialog1(LoginActivity.this, "No Internet Connection","You don't have internet connection.", false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.accountcreate:
                Intent register = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(register);
                break;
            case R.id.signin:

                phone =  phoneNumber.getEditText().getText().toString();
                pin = pinNumber.getEditText().getText().toString();
                View view = LoginActivity.this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                if( phone.length() == 0) {
                    alert.showAlertDialog(LoginActivity.this,"Enter Phone Number",false);
                }
                else if(phone.length()>10|| phone.length()<10){
                    alert.showAlertDialog(LoginActivity.this,"Enter corect Phone Number",false);
                }
                else if(pin.length() == 0){
                    alert.showAlertDialog(LoginActivity.this,"Enter PinNum Number",false);
                }
                else if(pin.length()>4|| phone.length()<4){
                    alert.showAlertDialog(LoginActivity.this,"Enter Correct PinNum Number",false);
                }
                else if(!cd.isNetworkOn(getApplicationContext())){
                    alert.showAlertDialog(LoginActivity.this,"There is Network error",false);
                }
                else {
                    login(phone,pin);

                }
                break;
            case R.id.request:
                String Forgot = forgotPin.getEditText().getText().toString();
                forgotPin(Forgot);
                break;
            case R.id.forgotpassword:
                View forgotLayout = findViewById(R.id.forgotLayout);
                forgotLayout.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
                forgotLayout.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        findViewById(R.id.SignInLayout).setVisibility(View.GONE);
                    }
                }, 500);
                break;
            case R.id.closeForgot:
                View forgotLayoutt = findViewById(R.id.forgotLayout);
                forgotLayoutt.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
                forgotLayoutt.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        findViewById(R.id.SignInLayout).setVisibility(View.VISIBLE);
                    }
                }, 200);
                break;
        }
    }
}
