package com.example.myapplicationjav2.ui.add_data;

import android.app.Activity;
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

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplicationjav2.R;
import com.example.myapplicationjav2.databinding.FragmentAddDataBinding;

public class AddDataFragment extends Fragment {

    private FragmentAddDataBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddDataViewModel addDataViewModel =
                new ViewModelProvider(this).get(AddDataViewModel.class);

        binding = FragmentAddDataBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAddData;

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
        addDataViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    public void openFileChooser(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        selFileActivityLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> selFileActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    if(data == null){
                        Toast.makeText(getActivity(),"Data is null",Toast.LENGTH_LONG).show();
                        return;
                    }
                    Uri uri = data.getData();
                    Toast.makeText(getActivity(),uri.getPath(),Toast.LENGTH_LONG).show();
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




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}