package com.example.myapplicationjav2.ui.find_docs;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import com.devlomi.record_view.OnRecordListener;
import com.devlomi.record_view.RecordButton;
import com.devlomi.record_view.RecordView;
import com.example.myapplicationjav2.MainActivity;
import com.example.myapplicationjav2.R;
import com.example.myapplicationjav2.databinding.FragmentFindDocsBinding;

public class FindDocsFragment extends Fragment {

    private FragmentFindDocsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FindDocsViewModel findDocsViewModel =
                new ViewModelProvider(this).get(FindDocsViewModel.class);

        binding = FragmentFindDocsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textFindDocs;
        findDocsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        initAudioRec(root);
        initFragmentToolbar(root);


        return root;
    }

    public void initFragmentToolbar(View root){

        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {

            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        });
    }




    public void initAudioRec(View root){
        RecordView recordView = (RecordView) root.findViewById(R.id.record_view_docs);
        RecordButton recordButton = (RecordButton) root.findViewById(R.id.record_button_docs);
        recordButton.setRecordView(recordView);
        recordView.setCancelBounds(0);//dp

        EditText editText = (EditText) root.findViewById(R.id.editText_SQLQuery);

        recordView.setOnRecordListener(new OnRecordListener() {
            @Override
            public void onStart() {
                editText.setVisibility(View.GONE);
            }

            @Override
            public void onCancel() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // yourMethod();
                        editText.setVisibility(View.VISIBLE);
                    }
                }, 1500);
            }

            @Override
            public void onFinish(long recordTime, boolean limitReached) {
                editText.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLessThanSecond() {
                editText.setVisibility(View.VISIBLE);

            }

            @Override
            public void onLock() {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}