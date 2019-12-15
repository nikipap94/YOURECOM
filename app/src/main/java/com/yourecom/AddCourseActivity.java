package com.yourecom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.yourecom.data.model.Professor;

import java.util.ArrayList;
import java.util.Arrays;

public class AddCourseActivity extends AppCompatActivity {

    private final String DEFAULT_PROFESSOR = "Select a professor";
    private final String OTHER_PROFESSOR= "Other";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        String course = getIntent().getStringExtra("course");

        ((TextView) findViewById(R.id.course_name)).setText(course);

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
                }else{
                    txtProf.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private ArrayList<Professor> getProfessors(){
        ArrayList<Professor> professors=new ArrayList<>();

        professors.add(new Professor(DEFAULT_PROFESSOR));
        professors.add(new Professor("Raphael Troncy"));
        professors.add(new Professor("Melek Owen"));
        professors.add(new Professor("Maurizio Filipone"));

        professors.add(new Professor("Other"));
        return professors;

    }


    public void onClickBtn(View v) {
//        Intent intent = new Intent(this, MainActivity.class);
//        setResult(Activity.RESULT_OK, intent);


        //Todo: Have to check if the professor is correct.
        super.onBackPressed();
    }
}
