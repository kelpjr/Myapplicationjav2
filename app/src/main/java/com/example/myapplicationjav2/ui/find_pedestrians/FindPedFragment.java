package com.example.myapplicationjav2.ui.find_pedestrians;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.devlomi.record_view.OnRecordClickListener;
import com.devlomi.record_view.OnRecordListener;
import com.devlomi.record_view.RecordButton;
import com.devlomi.record_view.RecordView;
import com.example.myapplicationjav2.R;
import com.example.myapplicationjav2.databinding.FragmentFindPedestriansBinding;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class FindPedFragment extends Fragment {

    private FragmentFindPedestriansBinding binding;
    private MediaRecorder mediaRecorder;
    private static String audioName = null;
    private static String audioPath = null;
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
        initEditText(root);


        return root;
    }

    public void initEditText(View root){
        RecordButton recordButton = (RecordButton) root.findViewById(R.id.record_button);
        EditText editText = (EditText) root.findViewById(R.id.editText_SQLQuery);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                Toast.makeText(getActivity(), "TEXT CHANGE", Toast.LENGTH_SHORT).show();

                if(editText.getText().toString().matches("")){
                    recordButton.setListenForRecord(true);
                    recordButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(),com.devlomi.record_view.R.drawable.recv_ic_mic_white,null));


                }else{
                    recordButton.setListenForRecord(false);
                    //ListenForRecord must be false ,otherwise onClick will not be called
                    recordButton.setOnRecordClickListener(new OnRecordClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(), "RECORD BUTTON CLICKED", Toast.LENGTH_SHORT).show();
                            Log.d("RecordButton","RECORD BUTTON CLICKED");
                        }
                    });
                    recordButton.setImageDrawable(ResourcesCompat.getDrawable(getResources(),com.devlomi.record_view.R.drawable.recv_ic_send,null));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void initAudioRec(View root){
        RecordView recordView = (RecordView) root.findViewById(R.id.record_view);
        RecordButton recordButton = (RecordButton) root.findViewById(R.id.record_button);
        recordButton.setRecordView(recordView);
        recordView.setCancelBounds(0);//dp

        EditText editText = (EditText) root.findViewById(R.id.editText_SQLQuery);


        recordView.setOnRecordListener(new OnRecordListener() {
            @Override
            public void onStart() {

                editText.setVisibility(View.GONE);
                // below method is used to set the
                // output file location for our recorded audio

                audioName = Environment.getExternalStorageDirectory().getAbsolutePath();
                audioName += "/AudioRecording.3gp";
                Toast.makeText(root.getContext(), audioName, Toast.LENGTH_LONG).show();


                mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mediaRecorder.setOutputFile(audioName);
                    try {
                        mediaRecorder.prepare();
                    } catch (IOException e) {
                        Log.e("TAG", "prepare() failed");
                    }
                    mediaRecorder.start();
                    Toast.makeText(root.getContext(), "Recording Started", Toast.LENGTH_LONG).show();


            }

            @Override
            public void onCancel() {
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    // yourMethod();
                    editText.setVisibility(View.VISIBLE);
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                }, 1500);
            }

            @Override
            public void onFinish(long recordTime, boolean limitReached) {
                editText.setVisibility(View.VISIBLE);
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
                Toast.makeText(root.getContext(), "Recording Stopped", Toast.LENGTH_LONG).show();

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