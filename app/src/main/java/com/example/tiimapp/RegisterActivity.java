package com.example.tiimapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    TextView inputEmail;
    TextView inputPassword;
    TextView btn_alreadyHaveAccount;
    Button btn_register;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        inputEmail = findViewById(R.id.etv_emailRegister);
        inputPassword = findViewById(R.id.inputPassword);

        btn_alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);
        btn_register = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.btn_login);

        btn_alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }

        });

        btn_register.setOnClickListener(v -> {
            String etv_email = inputEmail.getText().toString().trim();
            String etv_password = inputPassword.getText().toString().trim();


            if(TextUtils.isEmpty(etv_email)){
                inputEmail.setError("Email jest wymagany!");
                return;
            }

            if(TextUtils.isEmpty(etv_password)){
                inputPassword.setError("Hasło jest wymagane!");
                return;
            }

            if(etv_password.length() < 6){
                inputPassword.setError("Hasło musi mieć conajmniej 6 znaków.");
                return;
            }

            fAuth.createUserWithEmailAndPassword(etv_email, etv_password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    FirebaseUser user = fAuth.getCurrentUser();
                    Toast.makeText(RegisterActivity.this, "Użytkownik zarejestrowany", Toast.LENGTH_SHORT).show();
                    DocumentReference df = fStore.collection("Users").document(user.getUid());
                    Map<String, Object> userInfo = new HashMap<>();
                    userInfo.put("E-mail", etv_email);

                    userInfo.put("isUser", "1");

                    df.set(userInfo);

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull @NotNull Exception e) {
                    Toast.makeText(RegisterActivity.this, "Error !", Toast.LENGTH_SHORT).show();
                }
            });

        });
    }
}