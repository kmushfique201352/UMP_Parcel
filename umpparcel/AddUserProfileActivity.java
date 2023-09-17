package com.example.umpparcel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddUserProfileActivity extends AppCompatActivity {
    Button btnSave, btnCancel;
    EditText etName, etEmail, etUsername, etPassword, etUserType;
    DAOUserProfile dao = new DAOUserProfile();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_profile);

        btnSave=findViewById(R.id.btn_save_user_profile);
        btnCancel=findViewById(R.id.btn_cancel_user_profile);
        etName=findViewById(R.id.et_user_name);
        etEmail=findViewById(R.id.et_user_email);
        etUsername=findViewById(R.id.et_user_username);
        etPassword=findViewById(R.id.et_user_password);
        etUserType=findViewById(R.id.et_user_userType);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserModel userModel;
                try{
                    userModel = new UserModel("1", etUsername.getText().toString(), etPassword.getText().toString(),etUserType.getText().toString(),etName.getText().toString(),etEmail.getText().toString());
                    dao.add(userModel).addOnSuccessListener(suc->{
                        Toast.makeText(AddUserProfileActivity.this, "Record parcel is insert", Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener(er->{
                        Toast.makeText(AddUserProfileActivity.this, er.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }catch (Exception e){
                    Toast.makeText(AddUserProfileActivity.this, "Error creating parcel", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(AddUserProfileActivity.this, ViewAddUserProfileHomePage.class));

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

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddUserProfileActivity.this, ViewAddUserProfileHomePage.class));
            }
        });



    }

}
