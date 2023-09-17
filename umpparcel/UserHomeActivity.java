package com.example.umpparcel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserHomeActivity extends AppCompatActivity {
    TextView textLightReading;
    EditText etSearch;
    Button btnTrackParcel, btnUserProfile, btnSearch;
    DAOParcel dao;
    private FirebaseUser user;
    private String uid;
    private FirebaseAuth auth;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        reference= FirebaseDatabase.getInstance().getReference("Credentials");
        auth=FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        uid=user.getUid();
        //Toast.makeText(UserHomeActivity.this, "uid: "+Student.class, Toast.LENGTH_SHORT).show();

        reference.child("student").addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
//                    for (DataSnapshot data : dataSnapshot.getChildren()) {
//                        Student stu = data.getValue();
//
//                    }

                }else{
                    Toast.makeText(UserHomeActivity.this, "Record not exist", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UserHomeActivity.this, "Fail to get data."+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnTrackParcel = (Button) findViewById(R.id.btnTrackParcel);
        btnUserProfile = (Button) findViewById(R.id.btnUserProfile);
        textLightReading = findViewById(R.id.textLightReading);
        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);

        SensorManager mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        Sensor lightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mySensorManager.registerListener(
                lightSensorListener,
                lightSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao = new DAOParcel();
                dao.getByTrackingNo(etSearch.getText().toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            DataSnapshot data = dataSnapshot.getChildren().iterator().next();
                            ParcelModel parcel = data.getValue(ParcelModel.class);
//                            Toast.makeText(UserHomeActivity.this, parcel.toString(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(UserHomeActivity.this, TrackingParcelDetailActivity.class);
                            i.putExtra("sendParcelModel", parcel);
                            startActivity(i);
                        }else{
                            Toast.makeText(UserHomeActivity.this, "Record not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(UserHomeActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btnTrackParcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( UserHomeActivity.this, RVTrackingParcelStudentActivity.class));
            }
        });


        btnUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( UserHomeActivity.this, UserProfileActivity.class));
            }
        });
    }
    private final SensorEventListener lightSensorListener = new SensorEventListener(){

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_LIGHT){
                textLightReading.setText("LIGHT: " + event.values[0]);
            }
        }

    };
}