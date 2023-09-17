package com.example.umpparcel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdminManageParcelDetailActivity extends AppCompatActivity {

    Button btnUpdate, btnDelete, btnCancel;
    EditText etParcelName, etStudentName, etStudentID, etTrackNo,etRackNo;
    Switch switchStatusParcel;
    DAOParcel dao = new DAOParcel();
    ParcelModel parcelModelReceive;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_parcel_detail);

        reference = FirebaseDatabase.getInstance().getReference("trackingNo");

        etParcelName=findViewById(R.id.etParcelName);
        etStudentName=findViewById(R.id.etStudentName);
        etStudentID=findViewById(R.id.etStudentID);
        etTrackNo=findViewById(R.id.etTrackNo);
        etRackNo=findViewById(R.id.etRackNo);
        switchStatusParcel=findViewById(R.id.switchStatusParcel);



        parcelModelReceive = (ParcelModel) getIntent().getSerializableExtra("sendParcelModel");
        EditText etParcelName = findViewById(R.id.etParcelName);
        EditText etStudentName = findViewById(R.id.etStudentName);
        EditText etStudentID = findViewById(R.id.etStudentID);
        EditText etTrackNo = findViewById(R.id.etTrackNo);
        EditText etRackNo = findViewById(R.id.etRackNo);
        Switch switchStatusParcel = findViewById(R.id.switchStatusParcel);

        etParcelName.setText(parcelModelReceive.getParcelName());
        etStudentName.setText(parcelModelReceive.getStudentName());
        etStudentID.setText(String.valueOf(parcelModelReceive.getStudentID()));
        etRackNo.setText(parcelModelReceive.getRackNo());
        etTrackNo.setText(parcelModelReceive.getTrackingNo());
        switchStatusParcel.setChecked(parcelModelReceive.isTaken());
        if(parcelModelReceive.isTaken()){
            switchStatusParcel.setText("Taken");
        }else{
            switchStatusParcel.setText("Not Taken");
        }

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> parcel = new HashMap<>();
                parcel.put("parcelName", etParcelName.getText().toString());
                parcel.put("studentName", etStudentName.getText().toString());
                parcel.put("studentID", etStudentID.getText().toString());
                parcel.put("trackingNo", etTrackNo.getText().toString());
                parcel.put("rackNo", etRackNo.getText().toString());
                parcel.put("taken", switchStatusParcel.isChecked());

                dao.update(parcelModelReceive.getKey(),parcel).addOnSuccessListener(suc->{
                    Toast.makeText(AdminManageParcelDetailActivity.this, "Record parcel is updated", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->{
                    Toast.makeText(AdminManageParcelDetailActivity.this, er.getMessage(), Toast.LENGTH_SHORT).show();
                });

                startActivity(new Intent(AdminManageParcelDetailActivity.this, AdminHomeActivity.class));
            }
        });

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.remove(parcelModelReceive.getKey()).addOnSuccessListener(suc->{
                    Toast.makeText(AdminManageParcelDetailActivity.this, "Record parcel is deleted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->{
                    Toast.makeText(AdminManageParcelDetailActivity.this, er.getMessage(), Toast.LENGTH_SHORT).show();
                });
                startActivity(new Intent(AdminManageParcelDetailActivity.this, AdminHomeActivity.class));
            }
        });

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminManageParcelDetailActivity.this, AdminHomeActivity.class));
            }
        });
    }
}