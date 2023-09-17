package com.example.umpparcel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {

    Button register;

    private EditText etloginname,etloginpass;
    private Button btnlstudent,btnlstaff;
    private DatabaseReference reference;
    private String usertype,name,pass;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        reference= FirebaseDatabase.getInstance().getReference("Credentials");

        auth=FirebaseAuth.getInstance();

        etloginname=findViewById(R.id.etloginusername);
        etloginpass=findViewById(R.id.etloginpassword);

        btnlstudent=findViewById(R.id.btnloginstd);
        btnlstaff=findViewById(R.id.btnloginsta);

        register = findViewById(R.id.btnregisteraccount);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        btnlstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usertype="student";
                btnlstudent.setBackgroundColor(getResources().getColor(R.color.MBYes));
                btnlstaff.setBackgroundColor(getResources().getColor(R.color.MB));
            }
        });
        btnlstaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usertype="staff";
                btnlstaff.setBackgroundColor(getResources().getColor(R.color.MBYes));
                btnlstudent.setBackgroundColor(getResources().getColor(R.color.MB));
            }
        });

    }

    public void Login(View view) {
        if(!usertype.isEmpty()){
            name=etloginname.getText().toString().toLowerCase();
            pass=etloginpass.getText().toString();
            reference.child(usertype).child(name).addListenerForSingleValueEvent(listener);
        }
        else{
            Toast.makeText(SignInActivity.this, "Select User", Toast.LENGTH_LONG).show();
        }
    }
    ValueEventListener listener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if(snapshot.exists()){
                String password=snapshot.child("pass").getValue(String.class);
                String email=snapshot.child("email").getValue(String.class);

                if(password.equals(pass)){
                    auth.signInWithEmailAndPassword(email,password)
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    if (usertype.equals("student")){
                                        Intent i=new Intent(SignInActivity.this,UserHomeActivity.class);
                                        startActivity(i);
                                    }else{
                                        Intent i=new Intent(SignInActivity.this,AdminHomeActivity.class);
                                        startActivity(i);
                                    }

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignInActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                                }
                            });

                }else{
                    Toast.makeText(SignInActivity.this, "Wrong password", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(SignInActivity.this, "Record not found", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
            Toast.makeText(SignInActivity.this, error.toString(), Toast.LENGTH_LONG).show();
        }
    };
}