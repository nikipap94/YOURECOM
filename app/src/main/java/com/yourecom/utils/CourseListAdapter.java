package com.yourecom.utils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yourecom.R;
import com.yourecom.data.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseListAdapter extends ArrayAdapter<Course> {

    public CourseListAdapter(Context context, ArrayList<Course> courses) {
        super(context, 0, courses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Course course = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main_list, parent, false);
        }

        // Lookup view for data population
        TextView title = (TextView) convertView.findViewById(R.id.course_title);
//        TextView acronym = (TextView) convertView.findViewById(R.id.course_acr);
        TextView professorName = (TextView) convertView.findViewById(R.id.prof_name);


        // Populate the data into the template view using the data object
        String titleText = course.getAcronym() + " - "+course.getTitle();
        title.setText(titleText);
        professorName.setText(course.getProfessor().getName());

        // Return the completed view to render on screen
        return convertView;
    }
}
