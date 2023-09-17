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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText etname,etemail,etpass,etcontact,etusername;
    private String name,email,pass,contact,username,usertype;
    private DatabaseReference reference;
    private FirebaseAuth auth ;
    private Button btnstudent,btnstaff,btnca,btnsignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reference= FirebaseDatabase.getInstance().getReference("Credentials");

        auth=FirebaseAuth.getInstance();

        etname=findViewById(R.id.etfullname);
        etemail=findViewById(R.id.etemail);
        etpass=findViewById(R.id.etpassword);
        etcontact=findViewById(R.id.etphone);
        etusername=findViewById(R.id.etusername);

        btnstudent=findViewById(R.id.btnstd);
        btnstaff=findViewById(R.id.btnsta);

        btnstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usertype="student";
                btnstudent.setBackgroundColor(getResources().getColor(R.color.MBYes));
                btnstaff.setBackgroundColor(getResources().getColor(R.color.MB));
            }
        });
        btnstaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usertype="staff";
                btnstaff.setBackgroundColor(getResources().getColor(R.color.MBYes));
                btnstudent.setBackgroundColor(getResources().getColor(R.color.MB));
            }
        });

        btnca=findViewById(R.id.btncreateaccount);
        btnsignIn=findViewById(R.id.btnsignin);


    }

    public void signin(View view) {
        startActivity(new Intent(RegisterActivity.this,SignInActivity.class));
    }

    public void createaccount(View view) {
        name=etname.getText().toString();
        email=etemail.getText().toString();
        pass=etpass.getText().toString();
        contact=etcontact.getText().toString();
        username=etusername.getText().toString().toLowerCase();

        auth.createUserWithEmailAndPassword(email,pass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Student stu=new Student(auth.getCurrentUser().getUid(),name,email,pass,contact,username,usertype);
                        reference.child(usertype).child(username).setValue(stu)
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                                    }
                                })
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(RegisterActivity.this,"Account Created",Toast.LENGTH_LONG).show();
                                        startActivity(new Intent( RegisterActivity.this, SignInActivity.class));
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, e.toString(), Toast.LENGTH_LONG).show();
                    }
                });

    }
}