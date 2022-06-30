package com.example.tiimapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tiimapp.retrofit.data.ClubDTO;
import com.example.tiimapp.retrofit.methods.Methods;
import com.example.tiimapp.retrofit.methods.RetrofitClient;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddClubActivity extends AppCompatActivity {

    Button btn_addTeamToDb, btn_backToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        TextInputLayout clubName = findViewById(R.id.clubName);
        TextInputLayout shortName = findViewById(R.id.shortName);
        TextInputLayout coachName = findViewById(R.id.coachName);
        TextInputLayout stadium = findViewById(R.id.stadium);
        TextInputLayout webPage = findViewById(R.id.webPage);
        TextInputLayout captain = findViewById(R.id.captain);


        btn_addTeamToDb = (Button) findViewById(R.id.btn_addTeamToDb);
        btn_backToMenu = (Button) findViewById(R.id.btn_backToMenu);

       btn_addTeamToDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text1 = String.valueOf(clubName.getEditText().getText());
                String text2 = String.valueOf(shortName.getEditText().getText());
                String text3 = String.valueOf(coachName.getEditText().getText());
                String text4 = String.valueOf(stadium.getEditText().getText());
                String text5 = String.valueOf(webPage.getEditText().getText());
                String text6 = String.valueOf(captain.getEditText().getText());

                    ClubDTO clubDTO = new ClubDTO(text1, text2, text3,
                            text4, text5, text6);
                    Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
                    Call<ClubDTO> postClub = methods.postClub(clubDTO);
                    postClub.enqueue(new Callback<ClubDTO>() {
                        @Override
                        public void onResponse(Call<ClubDTO> call, Response<ClubDTO> response) {
                            System.out.println("Request got through: " + response.code());
                            Intent switchActivity = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(switchActivity);
                        }

                        @Override
                        public void onFailure(Call<ClubDTO> call, Throwable t) {
                            System.out.println("Failure with message: " + t.getMessage());
                        }
                    });
                }

        });

        btn_backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddClubActivity.this, MainActivity.class));
            }

        });

    }
}