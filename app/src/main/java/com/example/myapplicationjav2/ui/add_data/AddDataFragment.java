package com.example.myapplicationjav2.ui.add_data;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplicationjav2.R;
import com.example.myapplicationjav2.databinding.FragmentAddDataBinding;
import com.example.myapplicationjav2.services.FileUploadService;
import com.example.myapplicationjav2.services.ServiceGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDataFragment extends Fragment {

    private FragmentAddDataBinding binding;
    private AddDataViewModel addDataViewModel;

    private Intent data;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentAddDataBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAddDataStatus;
        addDataViewModel = new ViewModelProvider(this).get(AddDataViewModel.class);
        addDataViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        buttonInit(root);
        return root;
    }


    public void buttonInit(View root){
        Button selDir = (Button) root.findViewById(R.id.button_sel_dir);
        selDir.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                //OnCLick Stuff
                openFolderChooser();
            }
        });

        Button selFile = (Button) root.findViewById(R.id.button_sel_file);
        selFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });

        Button upFile = (Button) root.findViewById(R.id.button_upload_files);
        upFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile(data);
            }
        });

    }

    private void uploadFile(Intent data) {
        // create upload service client
        FileUploadService service =
                ServiceGenerator.createService(FileUploadService.class);

        ArrayList<Uri> fileList = new ArrayList<Uri>();
        if(data != null && data.getClipData() != null){
            for (int i = 0; i < data.getClipData().getItemCount(); i++) {
                fileList.add(data.getClipData().getItemAt(i).getUri());
                File file = new File(createCopyAndReturnRealPath(getActivity(), fileList.get(i)));
                RequestBody requestFile =
                        RequestBody.create(
                                MediaType.parse(getActivity().getContentResolver().getType(fileList.get(i))),
                                file
                        );

                // MultipartBody.Part is used to send also the actual file name
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

                // add another part within the multipart request
                String descriptionString = "hello, this is description speaking";
                RequestBody description =
                        RequestBody.create(
                                okhttp3.MultipartBody.FORM, descriptionString);

                // finally, execute the request
                Call<ResponseBody> call = service.upload(description, body);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call,
                                           Response<ResponseBody> response) {
                        Log.v("Upload LOGGING", "success");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("Upload error:", t.getMessage());
                    }
                });
            }
        }else{
                File file = new File(createCopyAndReturnRealPath(getActivity(), data.getData()));
                RequestBody requestFile =
                        RequestBody.create(
                                MediaType.parse(getActivity().getContentResolver().getType(data.getData())),
                                file
                        );

                // MultipartBody.Part is used to send also the actual file name
                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("", file.getName(), requestFile);

                // add another part within the multipart request
                String descriptionString = "";
                RequestBody description =
                        RequestBody.create(
                                okhttp3.MultipartBody.FORM, descriptionString);

                // finally, execute the request
                Call<ResponseBody> call = service.upload(description, body);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call,
                                           Response<ResponseBody> response) {
                        Log.v("Upload LOGGING", "success");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("Upload error:", t.getMessage());
                    }
                });

        }
    }
    @Nullable
    public static String createCopyAndReturnRealPath(
            @NonNull Context context, @NonNull Uri uri) {
        final ContentResolver contentResolver = context.getContentResolver();
        if (contentResolver == null)
            return null;

        // Create file path inside app's data dir
        String filePath = context.getApplicationInfo().dataDir + File.separator
                + System.currentTimeMillis();

        File file = new File(filePath);
        try {
            InputStream inputStream = contentResolver.openInputStream(uri);
            if (inputStream == null)
                return null;

            OutputStream outputStream = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0)
                outputStream.write(buf, 0, len);

            outputStream.close();
            inputStream.close();
        } catch (IOException ignore) {
            return null;
        }

        return file.getAbsolutePath();
    }

    public void openFileChooser(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        selFileActivityLauncher.launch(intent);
    }
    ActivityResultLauncher<Intent> selFileActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (data != null) {
                    data = null;
                }
                if(result.getResultCode() == Activity.RESULT_OK){
                    data = result.getData();
                    if(data.getClipData() != null){
                        int count = data.getClipData().getItemCount();
                        addDataViewModel.setMText(Integer.toString(count) + " files selected!");
                    }else if(data != null){
                        Uri uri = data.getData();

                        addDataViewModel.setMText("1 file selected!");
//                    Toast.makeText(getActivity(),uri.getPath(),Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getActivity(),"Data is null!",Toast.LENGTH_LONG).show();

                    }
                }
            }

    );



    public void openFolderChooser(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
        selDirActivityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> selDirActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    if(data == null){
                        Toast.makeText(getActivity(),"Data is null",Toast.LENGTH_LONG).show();
                        return;
                    }
                    Uri uri = data.getData();
                    DocumentFile df = DocumentFile.fromTreeUri(getActivity().getApplicationContext(), uri);
                    df.createFile("txt", "sample.txt");
                    Toast.makeText(getActivity(),uri.getPath(),Toast.LENGTH_LONG).show();
                }
            }
    );

//    private void uploadVid(Uri fileUri){
//        RetrofitClient client =
//        Call<List<VideoDataModel>> call = RetrofitClient.getInstance()
//                .getAPIs()
//                .getVideo()
//    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}