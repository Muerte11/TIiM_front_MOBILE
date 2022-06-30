package com.example.tiimapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText etv_emailToResetPassword;
    private Button btn_resetPassword;
    private ProgressBar pb_resetPassword;
    FirebaseAuth firebaseAuth;
    AlertDialog.Builder reset_alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etv_emailToResetPassword = findViewById(R.id.etv_emailToResetPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        reset_alert = new AlertDialog.Builder(this);

        btn_resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reset_alert.setTitle("Zresetować zapomniane hasło?")
                        .setMessage("Podaj adres e-mail, aby dostać link do zresetowania hasła.")
                        .setPositiveButton("Zresetuj", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).setNegativeButton("Anuluj", null)
                        .create().show();
            }
        });
    }
}