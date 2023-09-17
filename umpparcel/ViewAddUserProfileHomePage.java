package com.example.umpparcel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;


public class ViewAddUserProfileHomePage extends AppCompatActivity {

    Button btnAddUser, btnListUser, btnSearch;
    EditText etSearchID;
    DAOUserProfile dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_add_user_profile_home_page);
        btnAddUser = (Button) findViewById(R.id.btnAddUser);
        btnSearch = (Button) findViewById(R.id.btnSearchID);
        btnListUser = (Button) findViewById(R.id.btnListUser);
        etSearchID = findViewById(R.id.etSearchID);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao = new DAOUserProfile();
                dao.getByUsername(etSearchID.getText().toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            for(DataSnapshot data : dataSnapshot.getChildren()){
                                UserModel user = data.getValue(UserModel.class);
                                //search username base on user login
                                if(user.getUsername().equals(etSearchID)){
                                    Intent i = new Intent(ViewAddUserProfileHomePage.this, UserProfileDetailActivity.class);
                                    i.putExtra("sendUserModel", user);
                                    startActivity(i);
                                }
                            }
                        }else{
                            Toast.makeText(ViewAddUserProfileHomePage.this, "Record not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(ViewAddUserProfileHomePage.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        btnListUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewAddUserProfileHomePage.this, ViewAllStudentList.class));
            }
        });


        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( ViewAddUserProfileHomePage.this, AddUserProfileActivity.class));
            }
        });

    }
}
