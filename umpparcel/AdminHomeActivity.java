package com.example.umpparcel;

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

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class AdminHomeActivity extends AppCompatActivity {
    TextView textLightReading;
    EditText etSearch;
    Button btnTrackParcel, btnSearch;
    DAOParcel dao;
    private FirebaseUser user;
    private String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        //uncomment after join login process
//        user= FirebaseAuth.getInstance().getCurrentUser();
//        uid=user.getUid();

        btnTrackParcel = (Button) findViewById(R.id.btnTrackParcel);
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
                            for(DataSnapshot data : dataSnapshot.getChildren()){
                                ParcelModel parcel = data.getValue(ParcelModel.class);
                                Intent i = new Intent(AdminHomeActivity.this, AdminManageParcelDetailActivity.class);
                                i.putExtra("sendParcelModel", parcel);
                                startActivity(i);
                            }
                        }else{
                            Toast.makeText(AdminHomeActivity.this, "Record not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(AdminHomeActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btnTrackParcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( AdminHomeActivity.this, AdminManageParcelActivity.class));
            }
        });

        FloatingActionButton fab = findViewById(R.id.floatAddButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHomeActivity.this, AddParcelActivity.class));
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