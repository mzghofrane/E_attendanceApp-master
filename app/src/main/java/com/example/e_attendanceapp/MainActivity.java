package com.example.e_attendanceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonProfessor ;
    Button buttonStudent;
    Button buttonAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonProfessor =findViewById(R.id.buttonProfessor);
        buttonStudent=findViewById(R.id.buttonStudent);
        buttonAdmin=findViewById(R.id.buttonAdmin);




        buttonProfessor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openLogin();

            }
        });

        buttonStudent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openLogin();

            }


        });
        buttonAdmin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openLogin();

            }


        });




    }


    public void  openLogin(){

        Intent intent= new Intent(this, Login.class);
        startActivity(intent);

    }


}