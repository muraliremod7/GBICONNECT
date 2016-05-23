package com.brain.revanth.sampleapplication2;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
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

import Model.AlertDialogManager;
import Services.ConnectionDetector;
import Services.SessionManager;

public class LoginActivity extends AppCompatActivity {
    ImageView closebutton;
    Button login, Getpin;
    private ProgressDialog pDialog;
    public static TextInputLayout phoneNumber,pinNumber,forgotPin;
    TextView register;
    String phone,pin;
    AlertDialogManager alert = new AlertDialogManager();
    // Connection detector class
    ConnectionDetector cd;
    // Session Manager Class
    SessionManager session;
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
        Getpin = (Button)findViewById(R.id.request);


        if (!cd.isNetworkOn(getApplicationContext())) {
            // Internet Connection is Present
            // make HTTP requests
            showAlertDialog1(LoginActivity.this, "No Internet Connection","You don't have internet connection.", false);
        }
                //After Clicking Register Button Navigating Register Activity....
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(register);
            }
        });
            try{
                findViewById(R.id.progressLayout).setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return true;
                    }
                });
                //This IS For FofgotLayout....
                findViewById(R.id.forgotpassword).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View forgotLayout = findViewById(R.id.forgotLayout);
                        forgotLayout.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
                        forgotLayout.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                findViewById(R.id.SignInLayout).setVisibility(View.GONE);
                            }
                        }, 500);
                    }
                });

                findViewById(R.id.closeForgot).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View forgotLayout = findViewById(R.id.forgotLayout);
                        //forgotLayout.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
                        forgotLayout.setVisibility(View.GONE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                findViewById(R.id.SignInLayout).setVisibility(View.VISIBLE);
                            }
                        }, 200);
                    }
                });
            }catch (NullPointerException e){

            }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone =  phoneNumber.getEditText().getText().toString();
                pin = pinNumber.getEditText().getText().toString();
                view = LoginActivity.this.getCurrentFocus();
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
            }
        });
        Getpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Forgot = forgotPin.getEditText().getText().toString();

                forgotPin(Forgot);

            }
        });
    }

    private void forgotPin(String phone) {
        Ion.with(getApplicationContext())
                .load("http://www.gbiconnect.com/walletbabaservices/forgotpassword?phone="+phone)
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

    public void showAlertDialog1(Context context, String title, String message, final Boolean status) {
        final AlertDialog alertDialog = new AlertDialog.Builder(context,R.style.MyAlertDialogStyle).create();
        // Setting Dialog Title
        alertDialog.setTitle(title);
        // Setting Dialog Message
        alertDialog.setMessage("If you Want connect click below");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Mobile Data", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
                return;

            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Wifi", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                return;
            }
        });

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Cancel", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                finish();
                System.exit(0);
            }
        });
        // Showing Alert Message
        alertDialog.show();
    }
    public void timerDelayRemoveDialog(long time, final AlertDialog d){
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
                .load("POST", "http://gbiconnect.com/walletbabaservices/Teamlogin?phone=" + phone + "&pin=" + pin)
                        .asString()
                        .setCallback(new FutureCallback<String>() {
                            @Override
                            public void onCompleted(Exception e, String result) {
                                // do stuff with the result or error
//                        pDialog.cancel();
                                //stop progress
                                if (e != null) {

                                } else {

                                    try {
                                        JSONObject jSONObject = new JSONObject(result);
                                        int status = jSONObject.getInt("status");
                                        if (status == 1) {
                                            if(pDialog.isShowing())
                                                pDialog.dismiss();
                                            session.createLoginSession(phone,pin);
                                            JSONObject object = jSONObject.getJSONObject("team");
                                            registerationId = object.getString("id");
                                            Toast.makeText(getApplicationContext(), "Welcome To GBI Connect", Toast.LENGTH_LONG).show();
                                            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            // Add new Flag to start new Activity
                                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(i);
                                            finish();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
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

}
