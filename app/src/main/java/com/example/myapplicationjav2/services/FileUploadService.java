package com.example.myapplicationjav2.services;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileUploadService {
    @Multipart
    @POST("addDoc")
    Call<ResponseBody> uploadDoc(
            @Part MultipartBody.Part file);

    @Multipart
    @POST("addDoc")
    Call<ResponseBody> uploadMultiDoc(
            @Part List<MultipartBody.Part> files);

    @POST("processVid")
    Call<ResponseBody> uploadVid(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file);

    @POST("processVid")
    Call<ResponseBody> uploadMultiVid(
            @Part List<MultipartBody.Part> file);

    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadTest(
            @Part MultipartBody.Part file);






}