package com.example.myapplicationjav2.services;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileUploadService {
    @Multipart
    @POST("addDoc")
    Call<ResponseBody> upload(
//            @Header("Content-Type") String contentType,
//            @Part("description") RequestBody description,
            @Part MultipartBody.Part file

    );

    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadTest(
//            @Header("Content-Type") String contentType,
//            @Part("description") RequestBody description,
            @Part MultipartBody.Part file
//            @Part List<MultipartBody.Part> files
            );

    @Multipart
    @POST("addDoc")
    Call<ResponseBody> uploadTestm(
//            @Header("Content-Type") String contentType,
//            @Part("description") RequestBody description,
//            @Part MultipartBody.Part file
            @Part List<MultipartBody.Part> files);
//
//    @Multipart
//    @POST("upladdDocoad")
//    Call<ResponseBody> uploadMultipleFilesDynamic(
//            @Part("description") RequestBody description,
//            @Part List<MultipartBody.Part> files);
}
