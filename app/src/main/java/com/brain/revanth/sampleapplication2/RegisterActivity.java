package com.brain.revanth.sampleapplication2;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import com.brain.revanth.sampleapplication2.Services.AlertDialogManager;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpPost;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.http.body.MultipartFormDataBody;
import com.koushikdutta.ion.Ion;

import org.json.JSONObject;

import java.io.File;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText userName,userMobile,userEmail,userCollege,userPin,userConPin,userLocation,userdec;
    private ImageView iView;
    private Button upimage,regPersInfo;
    private Cursor cursor;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1,MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2;
    private String uploadImagePath = "";
    private ProgressDialog pDialog;
    private String KEY_IMAGE = "uploadfile";
    AlertDialogManager dialogManager;
    private String filename;
    private String REG_URL ="http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/register";
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
        userdec = (EditText)findViewById(R.id.reguserdesc);
        regPersInfo = (Button)findViewById(R.id.regperInfo);
        iView = (ImageView) findViewById(R.id.UploadImage);
        upimage = (Button) findViewById(R.id.uploadimage);
        regPersInfo.setOnClickListener(this);
        iView.setOnClickListener(this);
        upimage.setOnClickListener(this);

        dialogManager = new AlertDialogManager();
        if (ContextCompat.checkSelfPermission(RegisterActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(RegisterActivity.this,
                    Manifest.permission.CAMERA)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(RegisterActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

 }
    private void UploadImage() {

        final File fileToUpload = new File(uploadImagePath);
        pDialog = new ProgressDialog(RegisterActivity.this);
        pDialog.setMessage("Attempting Login");
        pDialog.setCancelable(false);
        pDialog.show();
        Ion.with(RegisterActivity.this)
                .load("POST","http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/uploadimage")
                .setHeader("encType","multipart/form-data")
                .setMultipartFile("pfimage","image/jpeg", fileToUpload)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        if(result.equals("Image is uploaded successfully!")){
                            if(pDialog.isShowing())
                                pDialog.dismiss();
                        }
                    }
                });

    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.MyAlertDialogStyle);
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
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 3: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
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
                     filename = uploadImagePath.substring(uploadImagePath.lastIndexOf("/")+1);
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
                filename = uploadImagePath.substring(uploadImagePath.lastIndexOf("/")+1);

            }
        }
    }
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

                String username = userName.getText().toString();
                String mobilenum = userMobile.getText().toString();
                String email = userEmail.getText().toString();
                String college = userCollege.getText().toString();
                String pin = userPin.getText().toString();
                String conpin = userConPin.getText().toString();
                String location = userLocation.getText().toString();
                String userDesc = userdec.getText().toString();
                if(pin.equals(conpin)){
                    registerUser(username,mobilenum,email,college,pin,location,userDesc);
                }
                else {
                    dialogManager.showAlertDialog(RegisterActivity.this,"Confirm Doesn't match with Pin",false);
                }
        }
    }

    private void registerUser(String username, String mobilenum, String email, String college, String pin, String location,String profile) {
        Ion.with(this)
                .load("POST", REG_URL)
                .setBodyParameter("username",username)
                .setBodyParameter("mobile",mobilenum)
                .setBodyParameter("email",email)
                .setBodyParameter("pin",pin)
                .setBodyParameter("college",college)
                .setBodyParameter("location",location)
                .setBodyParameter("description",profile)
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
                            try{
                                JSONObject j = new JSONObject(result);
                                int status = j.getInt("status");
                                if(status==1){
                                    JSONObject object = j.getJSONObject("data");
                                    String userid = object.getString("_id");
                                    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                    SharedPreferences.Editor editor = settings.edit();
                                    editor = settings.edit();
                                    editor.putString("_id", userid);
                                    editor.commit();
                                    Intent intent = new Intent(RegisterActivity.this,IdeasRegActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(RegisterActivity.this, "Registartion Successfull", Toast.LENGTH_LONG).show();
                                }
                                else {
                            dialogManager.showAlertDialog(RegisterActivity.this,j.getString("response"),false);
                                }
                            }catch (Exception ex){

                            }

                        }

                    }
                });
    }


}
