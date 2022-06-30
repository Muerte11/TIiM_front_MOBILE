package com.example.tiimapp;


import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;


public class LoginActivity extends AppCompatActivity {

    private Button btn_login;
    private TextView btn_signUp;
    private EditText etv_email, etv_password;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private TextView tv_forgotPassword;
    private AlertDialog.Builder reset_alert;
    private LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_signUp = findViewById(R.id.textViewSignUp);
        btn_login = findViewById(R.id.btn_login);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        etv_email = findViewById(R.id.etv_email);
        etv_password = findViewById(R.id.etv_emailRegister);
        tv_forgotPassword = findViewById(R.id.forgotPassword);
        reset_alert = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btn_login.setOnClickListener(v -> {

            String email = etv_email.getText().toString();
            String password = etv_password.getText().toString();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Wprowadź maila!", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Wprowadź hasło!", Toast.LENGTH_SHORT).show();
            } else {
                fAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(LoginActivity.this, "Pomyślne logowanie!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {

                        Toast.makeText(LoginActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        tv_forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = inflater.inflate(R.layout.activity_forgot_password, null);
                reset_alert.setTitle("Zresetować zapomniane hasło?")
                        .setMessage("Podaj adres e-mail, aby dostać link do zresetowania hasła.")
                        .setPositiveButton("Zresetuj", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText email = view.findViewById(R.id.etv_emailToResetPassword);
                                if (email.getText().toString().isEmpty()) {
                                    email.setError("Adres e-mail jest wymagany!");
                                    return;
                                }
                                fAuth.sendPasswordResetEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(LoginActivity.this, "Wysłano link do resetowania.", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @NotNull Exception e) {
                                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).setNegativeButton("Anuluj", null)
                        .setView(view)
                        .create().show();
            }
        });
        fAuth = FirebaseAuth.getInstance();
    }
/*
    private void checkUserAccessLevel(String uid) {
        DocumentReference df = fStore.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "OnSuccess: " + documentSnapshot.getData());

                if (documentSnapshot.getString("isAdmin") != null) {
                    startActivity(new Intent(LoginActivity.this, MainAdminActivity.class));
                    finish();
                } else if (documentSnapshot.getString("isUser") != null){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }

            }
        });
    }*/
}
