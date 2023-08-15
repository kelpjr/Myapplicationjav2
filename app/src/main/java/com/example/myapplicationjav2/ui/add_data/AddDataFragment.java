package com.example.myapplicationjav2.ui.add_data;

import static com.example.myapplicationjav2.helpers.HelperMethods.createCopyAndReturnRealPath;
import static com.example.myapplicationjav2.helpers.HelperMethods.getMimeType;

import android.app.Activity;
import android.content.ClipData;
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
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplicationjav2.R;
import com.example.myapplicationjav2.databinding.FragmentAddDataBinding;
import com.example.myapplicationjav2.services.FileUploadService;
import com.example.myapplicationjav2.services.ServiceGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
//            public void onClick(View view) {
//                uploadFile(data);
//
//            }
            public void onClick(View view) {
                uploadMultiFile(data);
            }

        });

    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri) {
        File file = new File(createCopyAndReturnRealPath(getActivity(), fileUri));
        String filetype = getMimeType(file);

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(filetype),
                        file
                );

        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }


    private void uploadFile(Intent data) {
        File file = new File(createCopyAndReturnRealPath(getActivity(), data.getData()));

        // Create a request body for the text file
        String filetype = getMimeType(file);
        RequestBody fileRequestBody = RequestBody.create(MediaType.parse(filetype), file);


        // Create a MultipartBody.Part instance representing the file part
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), fileRequestBody);

        // Create the Retrofit service interface
        FileUploadService service =
                ServiceGenerator.createService(FileUploadService.class);

        // Make the API call to upload the file
        Call<ResponseBody> call = service.uploadDoc(filePart);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    // File upload successful
                    Log.d("upload stats", "File uploaded successfully");
                } else {
                    // File upload failed
                    Log.e("upload stats", "File upload failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Handle failure
                Log.e("upload stats", "File upload failed: " + t.getMessage());
            }
        });

    }

    private void uploadMultiFile(Intent data) {
        ClipData clipData = data.getClipData();
        String fileType = "";
        if (clipData != null) {
            int itemCount = clipData.getItemCount();

            // Create a list to store the MultipartBody.Part instances
            List<MultipartBody.Part> parts = new ArrayList<>();

            for (int i = 0; i < itemCount; i++) {
                Uri uri = clipData.getItemAt(i).getUri();

                // Create a file object from the Uri
                File file = new File(createCopyAndReturnRealPath(getActivity(), uri));

                // Create a request body for the file
                fileType = getMimeType(file);
                RequestBody fileRequestBody = RequestBody.create(MediaType.parse(fileType), file);

                // Create a MultipartBody.Part instance representing the file part
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), fileRequestBody);

                // Add the file part to the list of parts
                parts.add(filePart);
            }

            // Create the Retrofit service interface
            FileUploadService service = ServiceGenerator.createService(FileUploadService.class);

            // Make the API call to upload the files
            Call<ResponseBody> call;
            if(fileType.contains("video")){
                call = service.uploadMultiVid(parts);
            }else {
                call = service.uploadMultiDoc(parts);
            }
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        // Files upload successful
                        Log.d("upload stats", "Files uploaded successfully");
                    } else {
                        // Files upload failed
                        Log.e("upload stats", "Files upload failed: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    // Handle failure
                    Log.e("upload stats", "Files upload failed: " + t.getMessage());
                }
            });
        }
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
                        for (int i = 0; i < count; i++) {
                            Log.e("FILE ", data.getClipData().getItemAt(i).getUri().toString());

                        }
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


    // TODO: 14/8/23:
    // upload from selected folder
    // prevent a crash when no files or folder is selected
    // add a loading so that the user is aware of it.
    //potentially make the whole table into a listview where attributed will be shown when tapped on
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}