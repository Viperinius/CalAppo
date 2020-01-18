package com.viperinius.calappo.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> _abTitle;

    public MainViewModel() {
        _abTitle = new MutableLiveData<>();
    }

    public LiveData<String> getAbTitle() {
        return _abTitle;
    }
    public void setAbTitle(String title) {
        _abTitle.setValue(title);
    }
}
