//package com.example.myapplicationjav2.ui.record_view;
//
//import android.view.View;
//import android.widget.EditText;
//
//import com.devlomi.record_view.OnRecordListener;
//import com.devlomi.record_view.RecordView;
//
//public class RecordViewHelper {
//    public RecordViewHelper(RecordView recordView, EditText editText, View root) {
//        root.recordView.setOnRecordListener(new OnRecordListener() {
//            @Override
//            public void onStart() {
//
//                editText.setVisibility(View.GONE);
//                // below method is used to set the
//                // output file location for our recorded audio
//                mediaRecorder = new MediaRecorder();
//
//                audioPath = Environment.getExternalStorageDirectory().getAbsolutePath();
//                audioName += "/docQuery.3gp";
//
//                mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//                mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//                mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//
//                mediaRecorder.setOutputFile(audioName);
//                try {
//                    mediaRecorder.prepare();
//                    //start the recording
//                    mediaRecorder.start();
//                } catch (IllegalStateException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onCancel() {
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    public void run() {
//                        // yourMethod();
//                        editText.setVisibility(View.VISIBLE);
//                        mediaRecorder.stop();
//                        mediaRecorder.release();
//                        mediaRecorder = null;
//                    }
//                }, 1500);
//            }
//
//            @Override
//            public void onFinish(long recordTime, boolean limitReached) {
//                editText.setVisibility(View.VISIBLE);
//                mediaRecorder.stop();
//                mediaRecorder.release();
//                mediaRecorder = null;
//            }
//
//            @Override
//            public void onLessThanSecond() {
//                editText.setVisibility(View.VISIBLE);
//
//            }
//
//            @Override
//            public void onLock() {
//
//            }
//        });
//
//    }
//}
