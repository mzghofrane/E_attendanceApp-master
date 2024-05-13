package com.example.e_attendanceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText name,course,email,turl;
    Button btn_add,btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name = (EditText)findViewById(R.id.txtName);
        course = (EditText)findViewById(R.id.txtCourse);
        email = (EditText)findViewById(R.id.txtEmail);
        turl = (EditText)findViewById(R.id.txtImageUrl);

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_back = (Button) findViewById(R.id.btn_back);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void insertData()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("course",course.getText().toString());
        map.put("email",email.getText().toString());
        map.put("turl",turl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Teachers").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this, "Data Inserted Successfully.", Toast.LENGTH_SHORT).show();
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this, "Error while Inserting.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void clearAll()
    {
        name.setText("");
        course.setText("");
        email.setText("");
        turl.setText("");
    }
}