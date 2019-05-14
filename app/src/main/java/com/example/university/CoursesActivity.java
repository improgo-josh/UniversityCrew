package com.example.university;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

public class CoursesActivity extends AppCompatActivity {

    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.coursesActivity_name);

//        String title = actionBar.getTitle().toString();

        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setBackgroundDrawable();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_side_navigation, menu);
        return true;
    }
}
