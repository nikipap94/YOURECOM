package com.yourecom.utils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.yourecom.R;
import com.yourecom.data.model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseListAdapter extends ArrayAdapter<Course> {

    private ArrayList<Course> originalList = new ArrayList<Course>();
    private ArrayList<Course> filteredList = new ArrayList<Course>();
    private ItemFilter mFilter = new ItemFilter();

    public CourseListAdapter(Context context, ArrayList<Course> courses) {
        super(context, 0, courses);
        this.originalList = courses;
        this.filteredList = courses;

    }

    @Override
    public Course getItem(int position){
        return filteredList.get(position);
    }


    @Override
    public int getCount(){
        return filteredList.size();
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

    public Filter getFilter() {
        return mFilter;
    }


    //INNER CLASS
    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Course> list = originalList;

            int count = list.size();
            final ArrayList<Course> nlist = new ArrayList<Course>();

            Course filterableItem ;

            for (int i = 0; i < count; i++) {
                filterableItem = list.get(i);
                if (filterableItem.contains(filterString)) {
                    nlist.add(filterableItem);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (ArrayList<Course>) results.values;
            notifyDataSetChanged();
        }

    }


}
