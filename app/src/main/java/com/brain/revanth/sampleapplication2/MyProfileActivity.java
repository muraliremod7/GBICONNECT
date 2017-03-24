package com.brain.revanth.sampleapplication2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;

import com.brain.revanth.sampleapplication2.Services.AlertDialogManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfileActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private EditText epname, epcollege, eplocation, epdesc,eppin,epconpin;
    private CircleImageView imageView;
    private TextView imgPath;
    private Button uploadImage,submit;
    AlertDialogManager alert;
    ArrayList<String> arrayList = new ArrayList<>();
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private String uploadImagePath = "";
    private String filename;
    TextView leadname,Phonenumb,email, collegename,location, descri;
    SharedPreferences teamID;
    AlertDialogManager dialogManager;
    public static SharedPreferences regid;
    private String Id;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        regid = PreferenceManager.getDefaultSharedPreferences(this);

        leadname = (TextView)findViewById(R.id.myprofilename);
        Phonenumb = (TextView)findViewById(R.id.myprofilephone);
        email = (TextView)findViewById(R.id.myprofileemail);
        collegename = (TextView)findViewById(R.id.myprofilcollege);
        descri = (TextView)findViewById(R.id.myprofiledesc);
        location = (TextView)findViewById(R.id.myprofilelocation);
        imageView = (CircleImageView)findViewById(R.id.profile_image);

        epname = (EditText)findViewById(R.id.epnewname);
        epcollege = (EditText)findViewById(R.id.epnewcolle);
        eplocation = (EditText)findViewById(R.id.epnewloc);
        epdesc = (EditText)findViewById(R.id.epdesc);
        eppin = (EditText)findViewById(R.id.eppin);
        epconpin = (EditText)findViewById(R.id.epconpin);
        imgPath = (TextView)findViewById(R.id.imgPath);
        uploadImage = (Button)findViewById(R.id.uploadnewpic);
        ImageView view = (ImageView)findViewById(R.id.closeForgotinmyprofile);
        view.setOnClickListener(this);
        uploadImage.setOnClickListener(this);
        submit = (Button)findViewById(R.id.profileiupdate);
        submit.setOnClickListener(this);

        getTeamProfile();
    }
    public void timerDelayRemoveDialog(long time, final ProgressDialog d){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                d.dismiss();
            }
        }, time);

    }
    private void getTeamProfile() {
        teamID = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Id = teamID.getString("user_id","0");
        pDialog = new ProgressDialog(MyProfileActivity.this);
        pDialog.setMessage("Attempting Login");
        pDialog.setCancelable(false);
        pDialog.show();
        timerDelayRemoveDialog(10*1000,pDialog);
        Ion.with(getApplicationContext())
                .load("http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/getuserbyid?userid="+Id)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else{
                            try {
                                JSONObject jSONObject = new JSONObject(result);
                                    JSONArray j = jSONObject.getJSONArray("user");
                                for(int i=0;i<=j.length();i++){
                                    JSONObject jsonObject = j.getJSONObject(i);
                                    if(jsonObject.has("username")){
                                        leadname.setText(jsonObject.getString("username"));
                                    }
                                    if(jsonObject.has("mobile")){
                                        Phonenumb.setText(jsonObject.getString("mobile"));
                                    }
                                    if(jsonObject.has("email")){
                                        email.setText(jsonObject.getString("email"));
                                    }
                                    if(jsonObject.has("college")){
                                        collegename.setText(jsonObject.getString("college"));
                                    }
                                    if(jsonObject.has("location")){
                                        location.setText(jsonObject.getString("location"));
                                    }
                                    if(jsonObject.has("description")){
                                        descri.setText(jsonObject.getString("description"));
                                    }
                                    if(jsonObject.has("img")){
                                        Picasso.with(getApplicationContext())
                                                .load("http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/getimage?filename="+jsonObject.getString("img"))
                                                .into(imageView);
                                    }
                                }
                                if(pDialog.isShowing())
                                    pDialog.dismiss();
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
        getMenuInflater().inflate(R.menu.profileedit, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
            case R.id.myprofile:

                try{
                            View editprofileLayout = findViewById(R.id.editprofileLayout);
                    getSupportActionBar().hide();
                            editprofileLayout.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
                            editprofileLayout.setVisibility(View.VISIBLE);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    findViewById(R.id.myprofilelayout).setVisibility(View.GONE);
                                }
                            }, 500);


                }catch (NullPointerException e){

                }
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
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
                    uploadImagePath = f.getAbsolutePath();
                    filename = uploadImagePath.substring(uploadImagePath.lastIndexOf("/")+1);
                    imgPath.setText(filename);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();

                String tempPath = getPath(selectedImageUri, this);

                BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                Bitmap bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
                uploadImagePath = tempPath;
                filename = uploadImagePath.substring(uploadImagePath.lastIndexOf("/")+1);
                imgPath.setText(filename);
            }
        }
    }
    public String getPath(Uri uri, Activity activity) {
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    private void UploadImage() {
        final File fileToUpload = new File(uploadImagePath);

        Ion.with(MyProfileActivity.this)
                .load("POST","http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/uploadimage")
                .setHeader("encType","multipart/form-data")
                .setMultipartFile("pfimage","image/jpeg", fileToUpload)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                    }
                });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.uploadnewpic:
                selectImage();
                break;
            case R.id.profileiupdate:
                UploadImage();
                String newname = epname.getText().toString();
                String newcollege = epcollege.getText().toString();
                String newlocation = eplocation.getText().toString();
                String newdesc = epdesc.getText().toString();
                String newpin = eppin.getText().toString();
                String newconpin = epconpin.getText().toString();
                dialogManager = new AlertDialogManager();
                if(newname.equals("")||newcollege.equals("")||newlocation.equals("")||newdesc.equals("")||newpin.equals(""))
                {
                    dialogManager.showAlertDialog(MyProfileActivity.this,"Should Be Enter All fields",false);
                }
                else if(newpin.length()>4||newpin.length()<4||newconpin.length()>4||newconpin.length()<4){
                    dialogManager.showAlertDialog(MyProfileActivity.this,"Pin Number Should be 4 numbers",false);
                }
                else {
                    updateprofile(newname, newcollege, newlocation, newdesc, newpin, newconpin, filename);
                }
                break;
            case R.id.closeForgotinmyprofile:
                View forgotLayout = findViewById(R.id.editprofileLayout);
                getSupportActionBar().show();
                forgotLayout.setVisibility(View.GONE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        findViewById(R.id.myprofilelayout).setVisibility(View.VISIBLE);
                    }
                }, 200);
        }
    }

    private void updateprofile(String newname, String newcollege, String newlocation, String newdesc, String newconpin, String newpin, String filename) {
        pDialog = new ProgressDialog(MyProfileActivity.this);
        pDialog.setMessage("Attempting Login");
        pDialog.setCancelable(false);
        pDialog.show();
        timerDelayRemoveDialog(10*1000,pDialog);
        Ion.with(this)
                .load("PUT","http://sample-env.ibqeg2uyqh.us-east-1.elasticbeanstalk.com/userupdate")
                .setBodyParameter("userid",Id)
                .setBodyParameter("username",newname)
                .setBodyParameter("pin",newpin)
                .setBodyParameter("confirmpin",newpin)
                .setBodyParameter("college",newcollege)
                .setBodyParameter("location",newlocation)
                .setBodyParameter("description",newdesc)
                .setBodyParameter("imgname",filename)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if(e!=null){

                        }else {
                            try{
                                JSONObject j = new JSONObject(result);
                                JSONObject object = j.getJSONObject("found");
                                String responce = object.getString("response");
                                Toast.makeText(MyProfileActivity.this,responce,Toast.LENGTH_LONG).show();
                                View forgotLayout = findViewById(R.id.editprofileLayout);
                                getSupportActionBar().show();
                                //forgotLayout.setAnimation(AnimationUtils.makeInChildBottomAnimation(getApplicationContext()));
                                forgotLayout.setVisibility(View.GONE);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        findViewById(R.id.myprofilelayout).setVisibility(View.VISIBLE);
                                    }
                                }, 200);
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                                if(pDialog.isShowing())
                                    pDialog.dismiss();
                            }catch (Exception ex){
                                ex.printStackTrace();
                            }
                        }
                    }
                });
    }
}
