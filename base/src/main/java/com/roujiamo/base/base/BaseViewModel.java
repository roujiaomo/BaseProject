package com.roujiamo.base.base;

import androidx.lifecycle.ViewModel;

import com.roujiamo.http.http.LoadStatusLiveData;


public class BaseViewModel extends ViewModel {
    public LoadStatusLiveData loadStatusLiveData = LoadStatusLiveData.getInstance();
}
