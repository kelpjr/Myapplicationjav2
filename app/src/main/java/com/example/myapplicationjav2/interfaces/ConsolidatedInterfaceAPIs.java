package com.example.myapplicationjav2.interfaces;

import com.example.myapplicationjav2.models.PAR;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ConsolidatedInterfaceAPIs {
    String BASE_URL = "http://11.0.0.3/api/";

    @POST("addDoc")
    Call<PAR> addDoc(
            @Part("addDoc")RequestBody description,
            @Part MultipartBody.Part file
    );

    @POST("processVid")
    Call<PAR> addVideo(
            @Part("description")RequestBody description,
            @Part MultipartBody.Part file
            );

    @GET("findVideo")
    Call<List<PAR>> findVideo(
            @Query("query") String query);

    @GET("selectVideo")
    Call<List<PAR>> selectVideo(
            @Query("file_path") String filePath,
            @Query("time_stamp") String timeStamp);

    @GET("getVideo")
    Call<List<PAR>> getVideo(
            @Query("file_path") String filePath);

    @GET("getData")
    Call<List<PAR>> getData();

    @GET("qa_query")
    Call<List<PAR>> qa_query(
            @Query("query") String query,
            @Query("mode") String mode,
            @Query("k") String k,
            @Query("temperature") String temperature);


//    /api/select_qa_list
//
//    Description:
//    Fetches the info of a specified document search entry
//    Parameters:
//    idx:<text>Search entry index
//    Return:
//      <page content>,
//      <source>,
//      <page number>,
//      <summary of document>,
//      <evaluation of relevance to query>
//
    @GET("select_qa_list")
    Call<List<PAR>> select_qa_list(@Query("idx") String idx);

    @DELETE("clearVidData")
    Call<List<PAR>> clearVidData();

    @DELETE("clearVectorDb")
    Call<List<PAR>> clearVectorDb();



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