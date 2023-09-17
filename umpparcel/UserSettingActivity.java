package com.example.umpparcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class UserSettingActivity extends AppCompatActivity {

    Button about,logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);

        about = findViewById(R.id.btn_about);
        logout = findViewById(R.id.btn_logout);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserSettingActivity.this,AboutActivity.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(UserSettingActivity.this,SignInActivity.class);
                startActivity(i);
            }
        });

    }
}