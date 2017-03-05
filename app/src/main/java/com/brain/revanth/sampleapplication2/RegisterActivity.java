package com.brain.revanth.sampleapplication2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpPost;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.http.body.MultipartFormDataBody;
import com.koushikdutta.ion.Ion;

import java.io.File;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText userName,userMobile,userEmail,userCollege,userPin,userConPin,userLocation;
    private ImageView iView;
    private Button upimage,regPersInfo;
    private Cursor cursor;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String uploadImagePath = "";
    private ProgressDialog pDialog;
    private String KEY_IMAGE = "uploadfile";
    private String REG_URL ="http://ec2-52-91-248-133.compute-1.amazonaws.com:8080/register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName = (EditText)findViewById(R.id.reguserName);
        userMobile = (EditText)findViewById(R.id.reguserMobile);
        userEmail = (EditText)findViewById(R.id.reguserEmail);
        userCollege = (EditText)findViewById(R.id.reguserCollege);
        userPin = (EditText)findViewById(R.id.reguserPin);
        userConPin = (EditText)findViewById(R.id.reguserConPin);
        userLocation = (EditText)findViewById(R.id.reguserLocation);
        regPersInfo = (Button)findViewById(R.id.regperInfo);
        iView = (ImageView) findViewById(R.id.UploadImage);
        upimage = (Button) findViewById(R.id.uploadimage);
        regPersInfo.setOnClickListener(this);
        iView.setOnClickListener(this);
        upimage.setOnClickListener(this);

 }
    public void timerDelayRemoveDialog(long time, final android.app.AlertDialog d){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                d.dismiss();
            }
        }, time);

    }
    private void UploadImage() {
        final File fileToUpload = new File(uploadImagePath);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Uploading Image.....");
        pDialog.setCancelable(false);
        pDialog.show();
        timerDelayRemoveDialog(10*1000,pDialog);

    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(Environment
                            .getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Library")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"), SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();

    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                File f = new File(Environment.getExternalStorageDirectory()
                        .toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {

                    BitmapFactory.Options btmapOptions = new BitmapFactory.Options();

                    Bitmap bm = BitmapFactory.decodeFile(f.getAbsolutePath(), btmapOptions);

                    bm = Bitmap.createScaledBitmap(bm, 70, 70, true);
                    iView.setImageBitmap(bm);
                    uploadImagePath = f.getAbsolutePath();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();

                String tempPath = getPath(selectedImageUri, this);

                BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                Bitmap bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
                iView.setImageBitmap(bm);
                uploadImagePath = tempPath;

            }
        }
    }
    @SuppressWarnings("deprecation")
    public String getPath(Uri uri, Activity activity) {
        String[] projection = { MediaStore.MediaColumns.DATA };
        cursor = activity.managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.UploadImage:
                selectImage();
                break;
            case R.id.uploadimage:
                selectImage();
                break;
            case R.id.regperInfo:
                final File fileToUpload = new File(uploadImagePath);
                String username = userName.getText().toString();
                String mobilenum = userMobile.getText().toString();
                String email = userEmail.getText().toString();
                String college = userCollege.getText().toString();
                String pin = userPin.getText().toString();
                String conpin = userConPin.getText().toString();
                String location = userLocation.getText().toString();
                if(pin.equals(conpin)){
                    registerUser(username,mobilenum,email,college,pin,location);
                    //Toast.makeText(RegisterActivity.this,"Confirm Doesn't match with Pin",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(RegisterActivity.this,"Confirm Doesn't match with Pin",Toast.LENGTH_LONG).show();
                }
//                Intent personal = new Intent(RegisterActivity.this,IdeasRegActivity.class);
//                startActivity(personal);
        }
    }

    private void registerUser(String username, String mobilenum, String email, String college, String pin, String location) {

        Ion.with(this)
                .load("POST", REG_URL)
                .setBodyParameter("username",username)
                .setBodyParameter("mobile",mobilenum)
                .setBodyParameter("email",email)
                .setBodyParameter("pin",pin)
                .setBodyParameter("college",college)
                .setBodyParameter("location",location)
                .setBodyParameter("description","asasa")
                .setBodyParameter("referalid","")
                .setBodyParameter("confirmpin",pin)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null) {
                            pDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Error uploading file", Toast.LENGTH_LONG).show();
                            return;
                        }
                        else{
                            Intent intent = new Intent(RegisterActivity.this,IdeasRegActivity.class);
                            startActivity(intent);
                            Toast.makeText(RegisterActivity.this, "Registartion Successfull", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }


}
