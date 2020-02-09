package com.yourecom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yourecom.data.model.Course;
import com.yourecom.data.model.Feedback;

public class AddFeedbackActivity extends AppCompatActivity {
    String courseKey;

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference feedbackRef = firebaseDatabase.getReference(Feedback.DB_NAME);
    private final DatabaseReference courseRef = firebaseDatabase.getReference(Course.DB_NAME);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feedback);

        getSupportActionBar().setTitle("Add Feedback");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        this.courseKey = intent.getStringExtra("courseKey");
        String courseTitle = intent.getStringExtra("courseTitle");

        ((TextView) findViewById(R.id.course_name)).setText(courseTitle);

        Button btn = (Button) findViewById(R.id.btnSubmit);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homeAsUp:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateCourseRating(final Integer ratingValue){
        System.out.println("rating" + ratingValue);
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Course course = dataSnapshot.getValue(Course.class);
                System.out.println("@@@@" + dataSnapshot.getKey());
                System.out.println("@@@@" + course);
                if(ratingValue != 0 ){
                    courseRef.child(courseKey).child("ratingSum").setValue(course.getRatingSum() + ratingValue);
                    courseRef.child(courseKey).child("ratingCount").setValue(course.getRatingCount() + 1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        courseRef.child(courseKey).addListenerForSingleValueEvent(listener);
    }

    public void save(View v) {
        TextView feedbackText = ((TextView) findViewById(R.id.feedback_txt));
        Integer rating = (int)((RatingBar) findViewById(R.id.rating_course)).getRating();

        String author = null;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            author = user.getDisplayName();
        }



        Feedback newFeedback = new Feedback(author, feedbackText.getText().toString().trim(), rating, this.courseKey);

        if(newFeedback.getText().isEmpty()){
            feedbackText.setError("Field required");
            return;
        }

        String feedback_key = feedbackRef.push().getKey();
        feedbackRef.child(feedback_key).setValue(newFeedback);

        //update course rating
        updateCourseRating(newFeedback.getRating());

        super.onBackPressed();
    }
}
