package com.example.myapplicationjav2.ui.add_data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddDataViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AddDataViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Please select file/directory...");
    }

    public void setMText(String text){
        mText.setValue(text);
    }


    public LiveData<String> getText() {
        return mText;
    }
}