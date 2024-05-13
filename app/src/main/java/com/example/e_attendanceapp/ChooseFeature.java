package com.example.e_attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChooseFeature extends AppCompatActivity {


    Button buttonLogout;
    Button buttonTeachers;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_feature);

        auth= FirebaseAuth.getInstance();
        buttonLogout=findViewById(R.id.btn_logout);
        buttonTeachers=findViewById(R.id.buttonTeachers);
        user=auth.getCurrentUser();
        if(user== null) {
            Intent intent= new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
            finish();

        }
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonTeachers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),Teachers.class);
                startActivity(intent);
                finish();
            }
        });

    }








}