package com.example.umpparcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TrackingParcelDetailActivity extends AppCompatActivity {

    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_parcel_detail);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ParcelModel parcelModelReceive = (ParcelModel) getIntent().getSerializableExtra("sendParcelModel");
        TextView tvParcelName = findViewById(R.id.tvParcelName);
        TextView name = findViewById(R.id.Name);
        TextView id = findViewById(R.id.Id);
        TextView RackNo = findViewById(R.id.RackNo);
        TextView TrackingNo = findViewById(R.id.TrackingNo);
        TextView TakenStatus = findViewById(R.id.TakenStatus);

        tvParcelName.setText(parcelModelReceive.getParcelName());
        name.setText(parcelModelReceive.getStudentName());
        id.setText(String.valueOf(parcelModelReceive.getStudentID()));
        RackNo.setText(parcelModelReceive.getRackNo());
        TrackingNo.setText(parcelModelReceive.getTrackingNo());
        if(parcelModelReceive.isTaken()){
            TakenStatus.setText("Taken");
        }else{
            TakenStatus.setText("Not Taken");
        }
    }
}