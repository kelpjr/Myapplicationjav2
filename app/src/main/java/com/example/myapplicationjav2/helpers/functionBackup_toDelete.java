//package com.example.myapplicationjav2.helpers;
//
//import static android.app.PendingIntent.getActivity;
//import static com.example.myapplicationjav2.helpers.HelperMethods.createCopyAndReturnRealPath;
//import static com.example.myapplicationjav2.helpers.HelperMethods.getMimeType;
//
//import android.content.Intent;
//import android.util.Log;
//
//import com.example.myapplicationjav2.services.FileUploadService;
//import com.example.myapplicationjav2.services.ServiceGenerator;
//
//import java.io.File;
//
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.RequestBody;
//import okhttp3.ResponseBody;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class functionBackup_toDelete {
//    private void uploadFileWORKING(Intent data) {
//        File file = new File(createCopyAndReturnRealPath(getActivity(), data.getData()));
//
//        // Create a request body for the text file
//        String filetype = getMimeType(file);
//        RequestBody fileRequestBody = RequestBody.create(MediaType.parse(filetype), file);
//
//
//        // Create a MultipartBody.Part instance representing the file part
//        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), fileRequestBody);
//
//        // Create the Retrofit service interface
//        FileUploadService service =
//                ServiceGenerator.createService(FileUploadService.class);
//
//        // Make the API call to upload the file
//        Call<ResponseBody> call = service.uploadFile(filePart);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    // File upload successful
//                    Log.d("upload stats", "File uploaded successfully");
//                } else {
//                    // File upload failed
//                    Log.e("upload stats", "File upload failed: " + response.message());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                // Handle failure
//                Log.e("upload stats", "File upload failed: " + t.getMessage());
//            }
//        });
//
//    }
//
//}
