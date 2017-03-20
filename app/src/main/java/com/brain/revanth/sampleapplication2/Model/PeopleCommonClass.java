package com.brain.revanth.sampleapplication2.Model;

import android.graphics.Bitmap;

/**
 * Created by Hari Prahlad on 15-05-2016.
 */
public class PeopleCommonClass {


    private String Name;
    private Bitmap Image;
    private String IdeaDescription;
    private String PhoneNumber;
    private String collegename,location;
    private String Email;
    public PeopleCommonClass(){

    }
    public PeopleCommonClass(String Name, Bitmap Image, String IdeaDesc, String Phonenumber, String collegename, String location, String Email) {
        this.Name = Name;
        this.Image = Image;
        this.IdeaDescription = IdeaDesc;
        this.PhoneNumber = Phonenumber;
        this.collegename = collegename;
        this.location = location;
        this.Email = Email;
    }
    public  String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Bitmap getImage() {
        return Image;
    }

    public void setImage(Bitmap Image) {
        this.Image = Image;
    }
    public String getIdeaDescription() {
        return IdeaDescription;
    }

    public void setIdeaDescription(String IdeaDescription) {
        this.IdeaDescription = IdeaDescription;
    }
    public String getPhoneNumber(){
        return PhoneNumber;
    }
    public void setPhoneNumber(String PhoneNumber){
        this.PhoneNumber = PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCollegename() {
        return collegename;
    }

    public void setCollegename(String collegename) {
        this.collegename = collegename;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
