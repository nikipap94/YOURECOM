package com.yourecom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yourecom.data.model.Course;
import com.yourecom.data.model.Tip;

public class AddTipsActivity extends AppCompatActivity {

    String course;

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    private final DatabaseReference courseDB = firebaseDatabase.getReference(Tip.DB_NAME);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tips);

        Intent intent = getIntent();
        this.course = intent.getStringExtra("courseKey");

        ((TextView) findViewById(R.id.course_name)).setText(course);
    }


    public void onClickBtn(View v) {
//        Intent intent = new Intent(this, MainActivity.class);
//        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }
}
