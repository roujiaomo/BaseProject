package com.roujiamo.study.source_code.rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxJavaNote {
    public static void main(String[] args) {
        doSingle();
    }

    public static void doSingle() {
        Single<String> single = Single.just("");
        //订阅 传入Observer对象
        //subscribe() 方法内部调用 subscribeActual(Observer)方法
        //subscribeActual() 使用传入的Observer对象调用onSubscribe() onSuccess()方法
        single.subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public static void doObserver() {
        Observable<String> observable = Observable.just("");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
