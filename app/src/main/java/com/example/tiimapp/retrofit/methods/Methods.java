package com.example.tiimapp.retrofit.methods;

import com.example.tiimapp.retrofit.data.ClubDTO;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Methods {

    @GET("/clubs")
    Call<List<ClubDTO>> getAllClubs();

    @POST("/clubs")
    Call<ClubDTO> postClub(@Body ClubDTO clubDTO);

    @DELETE("/clubs/id")
    Call<ResponseBody> deleteChosenClub(@Query("id") String id);


}
