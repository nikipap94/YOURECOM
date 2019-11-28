package com.yourecom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yourecom.data.model.Course;
import com.yourecom.data.model.Professor;
import com.yourecom.utils.CourseListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView simpleList;

    ArrayList<Course> dataModels;
//    private static CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_main_list, R.id.course_title, countryList);
//        simpleList.setAdapter(arrayAdapter);


        // Construct the data source
        ArrayList<Course> list = getCourseList();

        // Create the adapter to convert the array to views
        CourseListAdapter adapter = new CourseListAdapter(this, list);

        // Attach the adapter to a ListView
        simpleList = findViewById(R.id.simpleListView);
        simpleList.setAdapter(adapter);

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
