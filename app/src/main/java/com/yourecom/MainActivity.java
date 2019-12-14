package com.yourecom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;

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
        Professor prof1 = new Professor("John Smith");
        Professor prof2 = new Professor("Cathy Ammed");


        result.add(new Course("Machine Learning", "MALIS", prof1));
        result.add(new Course("Deep Learning", "Deep", prof1));
        result.add(new Course("Speech", "Spe", prof1));
        result.add(new Course("Web Semamtic", "WebSem", prof1));
        result.add(new Course("System Security", "Sys", prof2));
        result.add(new Course("Statistics", "Stat", prof2));
        result.add(new Course("Statistic Inf", "SI", prof2));

        return result;
    }
}
