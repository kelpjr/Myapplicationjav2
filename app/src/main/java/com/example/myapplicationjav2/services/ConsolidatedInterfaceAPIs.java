package com.example.myapplicationjav2.services;

import com.example.myapplicationjav2.Video;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ConsolidatedInterfaceAPIs {
    String BASE_URL = "http://11.0.0.3/api/";
    @GET("getVideo")
    Call<List<Video>> getVideo();

    @POST("processVid")
    Call<Video> addVideo(
            @Part("description")RequestBody description,
            @Part MultipartBody.Part file
            );


//    public static String domainName;
//    Context context;
//    public static final String POST_ADD_DOC = "/api/addDoc";
//    public static final String POST_ADD_VID = "/api/processVid";
//    public static final String QUERY_FIND_VID = "/api/findVideo";
//    public static final String QUERY_SEL_VID = "/api/selectVideo";
//    public static final String QUERY_GET_VID = "/api/getVideo";
//    public static final String QUERY_GET_DATA = "/api/getData";
//    public static final String QUERY_QA_QUERY = "/api/qa_query";
//    public static final String QUERY_QA_SEL_QA_LIST = "/api/select_qa_list/";
//    public static final String DEL_CLEAR_VID_DATA = "/api/clearVidData";
//    public static final String DEL_CLEAR_VECTOR_DB = "/api/clearVectorDb";
//
//    public ConsolidatedInterfaceAPIs(String domainName, Context context) {
//        this.domainName = domainName;
//        this.context = context;
//    }


}