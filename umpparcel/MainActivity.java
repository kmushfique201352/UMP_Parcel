package com.example.umpparcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnUserHome, btnAdminHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUserHome = (Button) findViewById(R.id.btnUserHome);
        btnUserHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( MainActivity.this, UserHomeActivity.class));
            }
        });

        btnAdminHome = (Button) findViewById(R.id.btnAdminHome);
        btnAdminHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( MainActivity.this, AdminHomeActivity.class));
            }
        });

    }
}