package com.example.myapplicationjav2.ui.find_docs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FindDocsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FindDocsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}