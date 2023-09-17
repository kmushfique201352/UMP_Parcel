package com.example.umpparcel;

public class Student {
    public String name,email,pass,contact,username;
    public String userType,uid;

    public Student(String uid, String name, String email, String pass, String contact, String username, String userType){
        this.uid=uid;
        this.name=name;
        this.email=email;
        this.pass=pass;
        this.contact=contact;
        this.username=username;
        this.userType=userType;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", contact='" + contact + '\'' +
                ", username='" + username + '\'' +
                ", userType='" + userType + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
