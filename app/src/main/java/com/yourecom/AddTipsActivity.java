package com.yourecom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yourecom.data.model.Tip;

public class AddTipsActivity extends AppCompatActivity {

    String course;
    String courseKey;

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference tipRef = firebaseDatabase.getReference(Tip.DB_NAME);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tips);

        Intent intent = getIntent();
        this.course = intent.getStringExtra("courseTitle");
        this.courseKey = intent.getStringExtra("courseKey");

        ((TextView) findViewById(R.id.course_name)).setText(course);
    }


    public void save(View v) {

        TextView tipText = ((TextView) findViewById(R.id.tip_txt));


        String author = null;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            author = user.getDisplayName();
        }

        Tip newTip = new Tip(author, tipText.getText().toString().trim(), this.courseKey);

        if(newTip.getText().isEmpty()){
            tipText.setError("Field required");
            return;
        }

        String feedback_key = tipRef.push().getKey();
        tipRef.child(feedback_key).setValue(newTip);


        super.onBackPressed();
    }
}
