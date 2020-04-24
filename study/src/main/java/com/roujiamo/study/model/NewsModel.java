package com.roujiamo.study.model;


import com.roujiamo.http.bean.TranslationBean;
import com.roujiamo.http.http.BaseResponse;
import com.roujiamo.http.http.HttpUtils;

import io.reactivex.Observable;

public class NewsModel {
    public Observable<BaseResponse<TranslationBean>> getNews() {
        return  HttpUtils.getInstance().getApiService().getNews();
    }
}
