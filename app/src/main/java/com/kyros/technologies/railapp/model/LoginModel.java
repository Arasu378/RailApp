package com.kyros.technologies.railapp.model;

/**
 * Created by kyros on 21-07-2017.
 */

public class LoginModel {
    private String UserName;
    private String UserEmail;
    private String MobileNumber;
    private String UserPassword;
    private int ProfileId;
    public LoginModel(){

    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public int getProfileId() {
        return ProfileId;
    }

    public void setProfileId(int profileId) {
        ProfileId = profileId;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getUserEmail() {

        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }
}
