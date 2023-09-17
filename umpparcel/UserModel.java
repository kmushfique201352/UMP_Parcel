package com.example.umpparcel;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class UserModel implements Serializable {
    @Exclude
    private String key; // key store in firebase
    private String username; //username for login, same as parcel.studentId
    private String password; //password for login
    private String userType; // type of user, determine login as admin or student
    private String name; //student Name, same as parcel.studentName
    private String email;//student email

    public UserModel(String key, String username, String password, String userType, String name, String email) {
        this.key = key;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "key='" + key + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
