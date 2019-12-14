package com.yourecom;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText password2;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_ui);
    }

    private boolean validate(String username, String email, String pasw1, String pasw2) {
        if ((username.length() > 0) && (email.endsWith("@eurecom.fr"))
                && (pasw1.length() > 0) && (pasw1.equals(pasw2))) {
            return true;

        }
        return false;
    }

}
