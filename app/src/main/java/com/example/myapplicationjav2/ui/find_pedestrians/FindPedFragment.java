package com.example.myapplicationjav2.ui.find_pedestrians;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.devlomi.record_view.OnRecordClickListener;
import com.devlomi.record_view.RecordButton;
import com.devlomi.record_view.RecordView;
import com.example.myapplicationjav2.R;
import com.example.myapplicationjav2.databinding.FragmentFindPedestriansBinding;


public class FindPedFragment extends Fragment {

    private FragmentFindPedestriansBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FindPedViewModel findPedViewModel =
                new ViewModelProvider(this).get(FindPedViewModel.class);

        //equivalent to context
        binding = FragmentFindPedestriansBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textFindPedStatus;
        findPedViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        initAudioRec(root);

        return root;
    }

    public void initAudioRec(View root){
        RecordView recordView = (RecordView) root.findViewById(R.id.record_view);
        RecordButton recordButton = (RecordButton) root.findViewById(R.id.record_button);
        recordButton.setRecordView(recordView);
        recordButton.setListenForRecord(false);

        //ListenForRecord must be false ,otherwise onClick will not be called
        recordButton.setOnRecordClickListener(new OnRecordClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "RECORD BUTTON CLICKED", Toast.LENGTH_SHORT).show();
                Log.d("RecordButton","RECORD BUTTON CLICKED");
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}