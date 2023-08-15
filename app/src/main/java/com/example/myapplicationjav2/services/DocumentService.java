package com.example.myapplicationjav2.services;

import com.example.myapplicationjav2.models.Document;
import com.example.myapplicationjav2.models.PAR;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DocumentService {

    @GET("getData")
    Call<List<PAR>> getData();

    @GET("qa_query")
    Call<List<Document>> qa_query(
            @Query("query") String query,
            @Query("mode") String mode,
            @Query("k") String k,
            @Query("temperature") String temperature);


    @GET("select_qa_list")
    Call<List<PAR>> select_qa_list(@Query("idx") String idx);

    @DELETE("clearVectorDb")
    Call<List<PAR>> clearVectorDb();
}
