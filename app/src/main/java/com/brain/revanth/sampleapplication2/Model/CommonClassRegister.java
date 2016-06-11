package com.brain.revanth.sampleapplication2.Model;

import java.util.ArrayList;

/**
 * Created by Hari Prahlad on 11-05-2016.
 */
public class CommonClassRegister {
    public static String Name;
    public static String PhoneNumber;
    public static String Email;
    public static String IdeaName;
    public static String IdeaDesc;
    public static ArrayList<String> Questions;
    public static String Pin;
    public static String ConfirmPin;

    public CommonClassRegister(String Name, String PhoneNumber, String Email, String IdeaName, String IdeaDesc, ArrayList<String> Questions, String Pin, String ConfirmPin){
            this.Name = Name;
            this.PhoneNumber = PhoneNumber;
            this.Email = Email;
            this.IdeaName = IdeaName;
            this.IdeaDesc = IdeaDesc;
            this.Questions = Questions;
            this.Pin = Pin;
            this.ConfirmPin = ConfirmPin;
    }
    public static String getPhoneNumber() {
        return PhoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public static String getEmail() {
        return Email;
    }

    public static void setEmail(String email) {
        Email = email;
    }

    public static String getConfirmPin() {
        return ConfirmPin;
    }

    public static void setConfirmPin(String confirmPin) {
        ConfirmPin = confirmPin;
    }

    public static String getIdeaDesc() {
        return IdeaDesc;
    }

    public static void setIdeaDesc(String ideaDesc) {
        IdeaDesc = ideaDesc;
    }

    public static String getIdeaName() {
        return IdeaName;
    }

    public static void setIdeaName(String ideaName) {
        IdeaName = ideaName;
    }

    public static String getName() {
        return Name;
    }

    public static void setName(String name) {
        Name = name;
    }

    public static String getPin() {
        return Pin;
    }

    public static void setPin(String pin) {
        Pin = pin;
    }

    public static ArrayList<String> getQuestions() {
        return Questions;
    }

    public static void setQuestions(ArrayList<String> questions) {
        Questions = questions;
    }

}
