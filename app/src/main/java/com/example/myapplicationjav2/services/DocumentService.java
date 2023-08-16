package com.example.myapplicationjav2.services;

import com.example.myapplicationjav2.models.DocResults;
import com.example.myapplicationjav2.models.PAR;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DocumentService {

    @GET("getData")
    Call<List<PAR>> getData();
    @POST("qa_query")
    @FormUrlEncoded
    Call<List<DocResults>> qa_query(
            @Field("query") String query,
            @Field("mode") String mode,
            @Field("k") String k,
            @Field("temperature") String temperature);



    @GET("select_qa_list")
    Call<List<PAR>> select_qa_list(@Query("idx") String idx);

    @DELETE("clearVectorDb")
    Call<List<PAR>> clearVectorDb();
}
