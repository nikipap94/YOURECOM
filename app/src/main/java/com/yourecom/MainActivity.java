package com.yourecom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yourecom.data.model.Course;
import com.yourecom.data.model.Professor;
import com.yourecom.utils.CourseListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView simpleList;
    CourseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Construct the data source
        ArrayList<Course> list = getCourseList();

        // Create the adapter to convert the array to views
        adapter = new CourseListAdapter(this, list);

        // Attach the adapter to a ListView
        simpleList = findViewById(R.id.simpleListView);
        simpleList.setAdapter(adapter);

        setSearchBar();
        setFloatingButton();
    }

    private void setFloatingButton(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCourseActivity.class);
                MainActivity.this.startActivityForResult(intent, 0);
            }
        });
    }

    private void setSearchBar(){
        SearchView searchView = findViewById(R.id.search_course);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return true;
            }
        });

    }

    private ArrayList<Course> getCourseList(){
        ArrayList<Course> result = new ArrayList<>();
        Professor prof1 = new Professor("Benoit Huet");
        Professor prof2 = new Professor("Maurizio Filippone");
        Professor prof3 = new Professor("Raphael Troncy");
        Professor prof4 = new Professor("Melek Önen");


        result.add(new Course("Machine Learning", "MALIS", prof1));
        result.add(new Course("Deep Learning", "Deep", prof1));
        result.add(new Course("Speech Recognition", "Speech", prof2));
        result.add(new Course("Web Semantic", "WebSem", prof3));
        result.add(new Course("Web System Interaction", "WebInt", prof3));
        result.add(new Course("Statistics", "Stat", prof4));
        result.add(new Course("Security for Big Data", "BigSec", prof4));

        return result;
    }
}
