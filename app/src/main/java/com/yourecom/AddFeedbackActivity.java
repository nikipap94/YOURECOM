package com.yourecom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddFeedbackActivity extends AppCompatActivity {
    String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        Intent intent = getIntent();
        this.course = intent.getStringExtra("course");

        ((TextView) findViewById(R.id.course_name)).setText(course);

        Button btn = (Button) findViewById(R.id.btnSubmit);

    }

    public void onClickBtn(View v) {
//        Intent intent = new Intent(this, MainActivity.class);
//        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }
}
