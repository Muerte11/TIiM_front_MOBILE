package com.example.tiimapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tiimapp.adapter.CustomListAdapter;
import com.example.tiimapp.databinding.FragmentDashboardBinding;
import com.example.tiimapp.retrofit.data.ClubDTO;
import com.example.tiimapp.retrofit.methods.Methods;
import com.example.tiimapp.retrofit.methods.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewTeamsActivity extends AppCompatActivity {

    ArrayList<ClubDTO> listview = new ArrayList();
    ListView listOfClubs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_teams);

        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);

        Call<List<ClubDTO>> call = methods.getAllClubs();

        call.enqueue(new Callback<List<ClubDTO>>() {
            @Override
            public void onResponse(Call<List<ClubDTO>> call, Response<List<ClubDTO>> response) {
                List<ClubDTO> list = response.body();

                for (int i = 0; i < list.size(); i++){
                    ClubDTO clubDTO = new ClubDTO();
                    clubDTO.setClubName(list.get(i).getClubName());
                    clubDTO.setShortname(list.get(i).getShortname());
                    clubDTO.setCoach(list.get(i).getCoach());
                    clubDTO.setStadium(list.get(i).getStadium());
                    clubDTO.setWebPage(list.get(i).getWebPage());
                    clubDTO.setCaptain(list.get(i).getCaptain());
                    listview.add(clubDTO);
                }

                listOfClubs = (ListView) findViewById(R.id.clubList);
                CustomListAdapter adapter = new CustomListAdapter(getApplicationContext(), listview);
                listOfClubs.setAdapter(adapter);
                System.out.println(listOfClubs);
            }
            @Override
            public void onFailure(Call<List<ClubDTO>> call, Throwable t) {
                System.out.println("Failure with message: " + t.getMessage());
            }
        });
    }
}