package com.example.myapplicationjav2.services;

import com.example.myapplicationjav2.models.PAR;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface VideoService {
    @POST("findVideo")
    Call<List<PAR>> findVideo(
            @Query("query") String query);

    @POST("selectVideo")
    Call<List<PAR>> selectVideo(
            @Query("file_path") String filePath,
            @Query("time_stamp") String timeStamp);

    @GET("getVideo")
    Call<List<PAR>> getVideo(
            @Query("file_path") String filePath);

    @DELETE("clearVidData")
    Call<List<PAR>> clearVidData();
}
