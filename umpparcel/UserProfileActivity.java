package com.example.umpparcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserProfileActivity extends AppCompatActivity implements SensorEventListener {

    Button setting, back;
    TextView Name, Id, Email;
    UserModel userModel = new UserModel("1", "CA19098", "1234", "student","Ong Wei Cheng", "Ca19098@student.ump.edu.my");

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private boolean isAccelerometerSensorAvailable, itIsNotFirstTime = false;
    private TextView xTextView, yTextView, zTextView;
    private float currentX, currentY, currentZ, lastX, lastY, lastZ, xDifference, yDifference ,zDifference;
    private float shakeThreshold = 3f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        setting = findViewById(R.id.btn_setting);
        back = findViewById(R.id.btnBack);
        Name = findViewById(R.id.Name);
        Id = findViewById(R.id.Id);
        Email = findViewById(R.id.Email);

        Name.setText(userModel.getName());
        Id.setText(userModel.getUsername());
        Email.setText(userModel.getEmail());

        xTextView = findViewById(R.id.xTextView);
        yTextView = findViewById(R.id.yTextView);
        zTextView = findViewById(R.id.zTextView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) !=null){
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isAccelerometerSensorAvailable = true;
        }
        else{
            xTextView.setText("Accelerometer sensor is not available");
            isAccelerometerSensorAvailable = false;
        }


        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(UserProfileActivity.this,UserSettingActivity.class);
                startActivity(i);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });



    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        xTextView.setText(sensorEvent.values[0]+"m/s2");
        yTextView.setText(sensorEvent.values[1]+"m/s2");
        zTextView.setText(sensorEvent.values[2]+"m/s2");

        currentX = sensorEvent.values[0];
        currentY = sensorEvent.values[1];
        currentZ = sensorEvent.values[2];

        if(itIsNotFirstTime){
            xDifference = Math.abs(lastX - currentX);
            yDifference = Math.abs(lastY - currentY);
            zDifference = Math.abs(lastZ - currentZ);

            if ((xDifference > shakeThreshold && yDifference > shakeThreshold ) ||
                    (xDifference > shakeThreshold && zDifference > shakeThreshold ) ||
                    (yDifference > shakeThreshold && zDifference > shakeThreshold ))
            {
                Intent i = new Intent(UserProfileActivity.this,UserSettingActivity.class);
                startActivity(i);
            }

        }

        lastX = currentX;
        lastY = currentY;
        lastZ = currentZ;
        itIsNotFirstTime = true;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    @Override
    protected void onResume() {
        super.onResume();

        if(isAccelerometerSensorAvailable)
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();

        if(isAccelerometerSensorAvailable)
            sensorManager.unregisterListener(this);
    }

}