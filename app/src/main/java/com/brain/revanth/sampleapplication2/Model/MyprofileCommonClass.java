package com.brain.revanth.sampleapplication2.Model;

import android.graphics.Bitmap;

/**
 * Created by Hari Prahlad on 23-05-2016.
 */
public class MyprofileCommonClass {

    private String userName;
    private String phone;
    private String email;
    private String college;
    private String location;
    private Bitmap profilepic;
        public MyprofileCommonClass(){

        }
    public MyprofileCommonClass(String userName, String phone, String email, String college, String location, Bitmap profilepic) {
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.college = college;
        this.location = location;
        this.profilepic = profilepic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Bitmap getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(Bitmap profilepic) {
        this.profilepic = profilepic;
    }
}
