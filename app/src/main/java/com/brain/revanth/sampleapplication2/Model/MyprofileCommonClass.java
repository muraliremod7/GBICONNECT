package com.brain.revanth.sampleapplication2.Model;

/**
 * Created by Hari Prahlad on 23-05-2016.
 */
public class MyprofileCommonClass {

    private String leadname;
    private String phone;
    private String ideaname;
    private String ideaDescription;
    private String profileImage;
    private String teamMembers;

    public MyprofileCommonClass() {
    }

    public MyprofileCommonClass(String leadname, String phone,String ideaname,String ideaDescription,String teamMembers, String profileImage) {
        this.leadname = leadname;
        this.phone = phone;
        this.ideaname = ideaname;
        this.ideaDescription = ideaDescription;
        this.teamMembers = teamMembers;
        this.profileImage = profileImage;
    }

    public String getLeadname() {
        return leadname;
    }

    public void setLeadname(String leadname) {
        this.leadname = leadname;
    }

    public String getIdeaDescription() {
        return ideaDescription;
    }

    public void setIdeaDescription(String ideaDescription) {
        this.ideaDescription = ideaDescription;
    }

    public String getIdeaname() {
        return ideaname;
    }

    public void setIdeaname(String ideaname) {
        this.ideaname = ideaname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(String teamMembers) {
        this.teamMembers = teamMembers;
    }
}
