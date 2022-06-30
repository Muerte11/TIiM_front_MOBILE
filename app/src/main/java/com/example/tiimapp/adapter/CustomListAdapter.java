package com.example.tiimapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiimapp.MainActivity;
import com.example.tiimapp.R;
import com.example.tiimapp.retrofit.data.ClubDTO;
import com.example.tiimapp.retrofit.methods.Methods;
import com.example.tiimapp.retrofit.methods.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomListAdapter extends BaseAdapter {

    Context context;
    ArrayList<ClubDTO> arrayListForClub;
    OnItemClickListener mListener;
    Button delete_btn, edit_btn;

    public CustomListAdapter(Context context, ArrayList<ClubDTO> arrayListForClub) {
        this.context = context;
        this.arrayListForClub = arrayListForClub;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    @Override
    public int getCount() {
        return arrayListForClub.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListForClub.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView ==  null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_club, parent, false);
        }
        Methods methods  = RetrofitClient.getRetrofitInstance().create(Methods.class);
        TextView clubName, shortName, coachName, stadium, webPage, captain;
        clubName = (TextView) convertView.findViewById(R.id.clubName);
        shortName = (TextView) convertView.findViewById(R.id.shortName);
        coachName = (TextView) convertView.findViewById(R.id.coachName);
        stadium = (TextView) convertView.findViewById(R.id.stadium);
        webPage = (TextView) convertView.findViewById(R.id.webPage);
        captain = (TextView) convertView.findViewById(R.id.captain);

        clubName.setText(String.valueOf(arrayListForClub.get(position).getClubName()));
        shortName.setText(String.valueOf(arrayListForClub.get(position).getShortname()));
        coachName.setText(String.valueOf(arrayListForClub.get(position).getCoach()));
        stadium.setText(String.valueOf(arrayListForClub.get(position).getStadium()));
        webPage.setText(String.valueOf(arrayListForClub.get(position).getWebPage()));
        captain.setText(String.valueOf(arrayListForClub.get(position).getCaptain()));


        return convertView;
    }
}
