package com.example.umpparcel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class UserProfileDetailActivity extends AppCompatActivity {
    Button btn_cancel, btn_update, btn_delete;
    DAOUserProfile dao = new DAOUserProfile();
    UserModel userModelReceive;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile_detail);
        btn_cancel = (Button) findViewById(R.id.btnBack);
        btn_update = (Button) findViewById(R.id.btn_update_user_profile);
        btn_delete = (Button) findViewById(R.id.btn_user_profile_delete);


        userModelReceive = (UserModel) getIntent().getSerializableExtra("sendUserModel");
        EditText username = findViewById(R.id.Username);
        EditText password = findViewById(R.id.Password);
        EditText userType = findViewById(R.id.userType);
        EditText name = findViewById(R.id.NameUser);
        EditText email = findViewById(R.id.userEmail);

        name.setText(userModelReceive.getName());
        email.setText(userModelReceive.getEmail());
        username.setText(userModelReceive.getUsername());
        password.setText(userModelReceive.getPassword());
        userType.setText(userModelReceive.getUserType());

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.remove(userModelReceive.getKey()).addOnSuccessListener(suc->{
                    Toast.makeText(UserProfileDetailActivity.this, "Record user is deleted", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->{
                    Toast.makeText(UserProfileDetailActivity.this, er.getMessage(), Toast.LENGTH_SHORT).show();
                });
                startActivity(new Intent(UserProfileDetailActivity.this, ViewAddUserProfileHomePage.class));
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, Object> user = new HashMap<>();
                user.put("name", username.getText().toString());
                user.put("email", email.getText().toString());
                user.put("username", username.getText().toString());
                user.put("password", password.getText().toString());
                user.put("userType", userType.getText().toString());

                dao.update(userModelReceive.getKey(),user).addOnSuccessListener(suc->{
                    Toast.makeText(UserProfileDetailActivity.this, "Record user is updated", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->{
                    Toast.makeText(UserProfileDetailActivity.this, er.getMessage(), Toast.LENGTH_SHORT).show();
                });

                startActivity(new Intent(UserProfileDetailActivity.this, ViewAddUserProfileHomePage.class));
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( UserProfileDetailActivity.this, ViewAddUserProfileHomePage.class));
            }
        });

    }
}
