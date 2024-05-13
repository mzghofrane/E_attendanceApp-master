package com.example.e_attendanceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class Teachers extends AppCompatActivity {

    RecyclerView recyclerView;
    TeacherAdapter teacherAdapter;

    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<TeachersModel> options =
                new FirebaseRecyclerOptions.Builder<TeachersModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Teachers"), TeachersModel.class)
                        .build();

        teacherAdapter = new TeacherAdapter(options);
        recyclerView.setAdapter(teacherAdapter);

        floatingActionButton= (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddActivity.class));
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        teacherAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        teacherAdapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str)
    {
        FirebaseRecyclerOptions<TeachersModel> options =
                new FirebaseRecyclerOptions.Builder<TeachersModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Teachers").orderByChild("name").startAt(str).endAt(str+"~"), TeachersModel.class)
                        .build();

        teacherAdapter = new TeacherAdapter(options);
        teacherAdapter.startListening();
        recyclerView.setAdapter(teacherAdapter);
    }
}