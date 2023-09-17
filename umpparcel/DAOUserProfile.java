package com.example.umpparcel;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOUserProfile {
    private DatabaseReference databaseReference;

    public DAOUserProfile() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(UserModel.class.getSimpleName());
    }

    public Task<Void> add(UserModel user){
        return databaseReference.push().setValue(user);
    }

    public Task<Void> update(String key, HashMap<String, Object> user){
        return databaseReference.child(key).updateChildren(user);
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

    public Query getByUsername(String username){
        return databaseReference.orderByChild("username").equalTo(username);
    }
}
