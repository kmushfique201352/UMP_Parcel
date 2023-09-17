package com.example.umpparcel;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class ParcelModel implements Serializable {
    @Exclude
    private String key; // key store in firebase
    private String parcelName; //name of parcel
    private String studentName; //name of user/student, same as user.name
    private String studentID; //same as user.username
    private String trackingNo;
    private String rackNo;
    private boolean isTaken;

    public ParcelModel(String parcelName, String studentName, String studentID, String trackingNo, String rackNo, boolean isTaken) {
        this.parcelName = parcelName;
        this.studentName = studentName;
        this.studentID = studentID;
        this.trackingNo = trackingNo;
        this.rackNo = rackNo;
        this.isTaken = isTaken;
    }

    public ParcelModel() {
    }

    @Override
    public String toString() {
        return "ParcelModel{" +
                "parcelName='" + parcelName + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentID='" + studentID + '\'' +
                ", trackingNo='" + trackingNo + '\'' +
                ", rackNo='" + rackNo + '\'' +
                ", isTaken=" + isTaken +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRackNo() {
        return rackNo;
    }

    public void setRackNo(String rackNo) {
        this.rackNo = rackNo;
    }

    public String getParcelName() {
        return parcelName;
    }

    public void setParcelName(String parcelName) {
        this.parcelName = parcelName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getTrackingNo() {
        return trackingNo;
    }

    public void setTrackingNo(String trackingNo) {
        this.trackingNo = trackingNo;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }}
