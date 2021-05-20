package com.example.cloneapp.Models;

public class Users {
    String profilepic,username,mail,password,location,gender;


//    public Users(String profilepic,String mail, String password,int age, String gender,String location) {
//        this.profilepic=profilepic;
//        this.mail = mail;
//        this.password = password;
//        this.age=age;
//        this.gender=gender;
//        this.location=location;
//    }
    public Users(String password,String profilepic,String username) {
        this.profilepic=profilepic;
        this.username=username;
        this.password = password;
    }
    public  Users(){}

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
}
