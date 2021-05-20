package com.example.cloneapp.Models;

public class UserLocation {
    String zipcode;
    String usermail;
    String location;

    public UserLocation(String usermail, String location) {
        this.usermail = usermail;
        this.location = location;
    }
    public  UserLocation(){}

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
