package com.example.umpparcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;


public class AddParcelActivity extends AppCompatActivity {
    Button btnAdd, btnCancel;
    EditText etParcelName, etStudentName, etStudentID, etTrackNo,etRackNo;
    Switch switchStatusParcel;
    DAOParcel dao = new DAOParcel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parcel);

        btnAdd=findViewById(R.id.btnAdd);
        etParcelName=findViewById(R.id.etParcelName);
        etStudentName=findViewById(R.id.etStudentName);
        etStudentID=findViewById(R.id.etStudentID);
        etTrackNo=findViewById(R.id.etTrackNo);
        etRackNo=findViewById(R.id.etRackNo);
        switchStatusParcel=findViewById(R.id.switchStatusParcel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParcelModel parcelModel;
                try{
                    parcelModel = new ParcelModel(etParcelName.getText().toString(), etStudentName.getText().toString(),etStudentID.getText().toString(),etTrackNo.getText().toString(),etRackNo.getText().toString(), switchStatusParcel.isChecked());
                    dao.add(parcelModel).addOnSuccessListener(suc->{
                        Toast.makeText(AddParcelActivity.this, "Record parcel is insert", Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener(er->{
                        Toast.makeText(AddParcelActivity.this, er.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }catch (Exception e){
                    Toast.makeText(AddParcelActivity.this, "Error creating parcel", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(AddParcelActivity.this, AdminHomeActivity.class));

                //    public ParcelModel(int id, String parcelName, String studentName, String studentID, String trackingNo, String rackNo, boolean isTaken) {
//                HashMap<String, Object>hashMap=new HashMap<>();
//                hashMap.put("parcelName", parcelModel.getParcelName());
//                hashMap.put("studentName", parcelModel.getStudentName());
//                hashMap.put("studentID", parcelModel.getStudentID());
//                hashMap.put("trackingNo",parcelModel.getTrackingNo());
//                hashMap.put("rackNo", parcelModel.getRackNo());
//                hashMap.put("isTaken",String.valueOf(parcelModel.isTaken()));
//
//                dao.update("", hashMap).addOnSuccessListener(suc->{
//                    Toast.makeText(AddParcelActivity.this, "Record parcel is change", Toast.LENGTH_SHORT).show();
//                }).addOnFailureListener(er->{
//                    Toast.makeText(AddParcelActivity.this, er.getMessage(), Toast.LENGTH_SHORT).show();
//                });
            }
        });

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddParcelActivity.this, AdminManageParcelActivity.class));
            }
        });

    }
}