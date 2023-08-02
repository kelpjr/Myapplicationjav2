package com.example.myapplicationjav2.ui.find_pedestrians;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FindPedViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FindPedViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}