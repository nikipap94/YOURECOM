package com.yourecom;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResetPassword extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
        final EditText edt = (EditText) findViewById(R.id.editText2);
        Button bt = (Button) findViewById(R.id.sendEmail);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                final String emailAddress = edt.getText().toString();

                auth.sendPasswordResetEmail(emailAddress)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ResetPassword.this,
                                            "Verification email sent to " + emailAddress,
                                            Toast.LENGTH_SHORT).show();
                                    AlertDialog alertDialog = new AlertDialog.Builder(ResetPassword.this).create();
                                    alertDialog.setTitle("Verification email sent.");
                                    alertDialog.setMessage("Please check your mail inbox to verify your email and then login again.");
                                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Logout",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    signOut();
                                                    dialog.dismiss();
                                                }
                                            });
                                    alertDialog.show();
                                }
                            }
                        });
            }
        });
    }
    private void signOut() {
        Intent intent = new Intent(ResetPassword.this, EmailPasswordActivity.class);
        startActivity(intent);
    }
    }

