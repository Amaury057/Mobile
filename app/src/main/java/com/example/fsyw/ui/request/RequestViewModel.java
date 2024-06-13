package com.example.fsyw.ui.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class RequestViewModel extends ViewModel{

    private final MutableLiveData<String> mText;

    public RequestViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is logout fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
