package com.example.myapplicationjav2.ui.add_data;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.example.myapplicationjav2.services.RetrofitClient;

public class AddDataFragment extends Fragment {

    private FragmentAddDataBinding binding;
    private AddDataViewModel addDataViewModel;
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
                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    if(data.getClipData() != null){
                        int count = data.getClipData().getItemCount();
                        addDataViewModel.setMText(Integer.toString(count) + " files selected!");
                    }else if(data != null){
                        Uri uri = data.getData();

                        addDataViewModel.setMText("1 file selected!");
//                    Toast.makeText(getActivity(),uri.getPath(),Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(getActivity(),"Data is null",Toast.LENGTH_LONG).show();
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