package com.yourecom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yourecom.data.model.Feedback;

public class AddFeedbackActivity extends AppCompatActivity {
    String courseKey;

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference feedbackRef = firebaseDatabase.getReference(Feedback.DB_NAME);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);

        Intent intent = getIntent();
        this.courseKey = intent.getStringExtra("courseKey");
        String courseTitle = intent.getStringExtra("courseTitle");

        ((TextView) findViewById(R.id.course_name)).setText(courseTitle);

        Button btn = (Button) findViewById(R.id.btnSubmit);

    }

    public void save(View v) {
        TextView feedbackText = ((TextView) findViewById(R.id.feedback_txt));
        Integer rating = (int)((RatingBar) findViewById(R.id.rating_course)).getRating();

        String author = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

        Feedback newFeedback = new Feedback(author, feedbackText.getText().toString().trim(), rating, this.courseKey);

        if(newFeedback.getText().isEmpty()){
            feedbackText.setError("Field required");
            return;
        }
        System.out.println("%%%%%%%%%%%%%%%%%%");
        System.out.println(newFeedback.getCourseId());

        String feedback_key = feedbackRef.push().getKey();
        feedbackRef.child(feedback_key).setValue(newFeedback);

        super.onBackPressed();
    }
}
