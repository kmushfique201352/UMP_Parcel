package com.example.umpparcel;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOParcel {
    private DatabaseReference databaseReference;

    public DAOParcel() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(ParcelModel.class.getSimpleName());
    }

    public Task<Void> add(ParcelModel parcel){
        return databaseReference.push().setValue(parcel);
    }

    public Task<Void> update(String key, HashMap<String, Object> parcel){
        return databaseReference.child(key).updateChildren(parcel);
    }

    public Task<Void> remove(String key){
        return databaseReference.child(key).removeValue();
    }

    //get all record
    public Query get(String key){
        if(key == null){
            return databaseReference.orderByKey().limitToFirst(8);
        }
        return databaseReference.orderByKey().startAfter(key).limitToFirst(8);
    }
    //get all record and live change
    public Query get(){
        return databaseReference;
    }

    public Query getByStudentId(String studentID){
        return databaseReference.orderByChild("studentID").equalTo(studentID);
    }

    public Query getByTrackingNo(String trackingNo){
        return databaseReference.orderByChild("trackingNo").equalTo(trackingNo);
    }

}
