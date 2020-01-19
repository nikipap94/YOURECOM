package com.yourecom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yourecom.data.model.Course;
import com.yourecom.data.model.Professor;

import java.util.ArrayList;

public class AddCourseActivity extends AppCompatActivity {

    private final String DEFAULT_PROFESSOR = "Select a professor";
    private final String OTHER_PROFESSOR= "Other";

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference courseDB = firebaseDatabase.getReference(Course.DB_NAME);
    private final DatabaseReference professorDB = firebaseDatabase.getReference(Professor.DB_NAME);

    private Professor professorSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_course);

//        String courseKey = getIntent().getStringExtra("courseKey");

//        ((TextView) findViewById(R.id.course_name)).setText(courseKey);

        setSpinner();
    }

    private void setSpinner(){
        Spinner spin = (Spinner) findViewById(R.id.prof_options);
        ArrayAdapter<Professor> adapter = new ArrayAdapter<Professor>(this, android.R.layout.simple_spinner_item, getProfessors());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                EditText txtProf = (EditText) findViewById(R.id.prof_name);
                if(parent.getSelectedItem().toString() == OTHER_PROFESSOR){
                    txtProf.setVisibility(View.VISIBLE);
                    professorSelected = null;
                }else{
                    txtProf.setVisibility(View.INVISIBLE);
                    professorSelected = (Professor) parent.getSelectedItem();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private ArrayList<Professor> getProfessors(){
        final ArrayList<Professor> professors=new ArrayList<>();

        professors.add(new Professor(DEFAULT_PROFESSOR));
//        professors.add(new Professor("Raphael Troncy"));
//        professors.add(new Professor("Melek Owen"));
//        professors.add(new Professor("Maurizio Filipone"));


        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Professor professor = dataSnapshot.getValue(Professor.class);

                for(DataSnapshot customList :  dataSnapshot.getChildren()){
                    Professor item = customList.getValue(Professor.class);
                    professors.add(item);
                }

                //to be able to add another professor
                professors.add(new Professor("Other"));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        professorDB.orderByChild("name").addListenerForSingleValueEvent(listener);


        return professors;

    }


    public void save(View v) {
        //text views
        TextView courseNameText = ((TextView) findViewById(R.id.course_name));
        TextView acrText = ((TextView) findViewById(R.id.course_acr));
        TextView profText = (TextView) ((Spinner) findViewById(R.id.prof_options)).getSelectedView();


        String courseTitle = courseNameText.getText().toString().trim();
        String acr = acrText.getText().toString().trim();

        Professor prof;
        if(professorSelected == null){
            //create professor
            String prof_name = ((TextView) findViewById(R.id.prof_name)).getText().toString().trim();

            if(prof_name.isEmpty()){
                ((TextView) findViewById(R.id.prof_name)).setError("Professor name cannot be empty.");
                return;
            }

            prof = new Professor(prof_name);
            String prof_key = professorDB.push().getKey();
            professorDB.child(prof_key).setValue(prof);
            System.out.println("*****Prof key"+ prof_key);


        }else{
            prof = professorSelected;
            if(prof.toString() == DEFAULT_PROFESSOR){
                profText.setError("Please select a professor.");
                return;
            }

        }

        //validation
        if(courseTitle.isEmpty()){
            courseNameText.setError("Course name cannot be empty.");
            return;
        }

        if(acr.isEmpty()){
            acrText.setError("Acronym cannot be empty.");
            return;
        }


        //adding the courseKey
        Course newCourse = new Course(courseTitle, acr, prof);
        String course_key = courseDB.push().getKey();
        courseDB.child(course_key ).setValue(newCourse);


        super.onBackPressed();
    }
}
