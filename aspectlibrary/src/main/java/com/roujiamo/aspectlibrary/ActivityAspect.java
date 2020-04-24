/*
 * ActivityAspect      2016-03-04
 * Copyright (c) 2016 hujiang Co.Ltd. All right reserved(http://www.hujiang.com).
 * 
 */

package com.roujiamo.aspectlibrary;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ActivityAspect {

//    @Pointcut("execution(* android.app.Activity.onCreate(..))")
//    public void onCreateCutPoint() {
//
//    }
//
//    @Pointcut("execution(* android.app.Activity.onResume())")
//    public void onResumeCutPoint() {
//
//    }
//
//    @Pointcut("execution(* android.app.Activity.onPause(..))")
//    public void onPauseCutPoint() {
//
//    }
//
//    @Pointcut("execution(* android.app.Activity.onStart(..))")
//    public void onStartCutPoint() {
//
//    }
//
//    @Pointcut("execution(* android.app.Activity.onStop(..))")
//    public void onStopCutPoint() {
//
//    }
//
//    @Pointcut("execution(* android.app.Activity.onDestroy(..))")
//    public void onDestroyCutPoint() {
//
//    }

//    /**
//     *
//     * @param joinPoint
//     * @throws Throwable
//     */
//    @After("within(@com.hujiang.library.aspect.TraceDelay *)")
//    public void onUi(JoinPoint joinPoint) throws Throwable {
//        Log.i("helloAOP", "" + joinPoint.getSignature());
//    }

    @Before("execution(* androidx.appcompat.app.AppCompatActivity.on**(..))")
    public void onResumeMethod(JoinPoint joinPoint) throws Throwable {
        Log.i("ActivityAspect", "aspect:::" + joinPoint.getSignature());
    }

//    @Before("execution(* androidx.appcompat.app.AppCompatActivity.onResume())")
//    public void onResume(JoinPoint joinPoint) throws Throwable {
//        Log.i("helloAOP", "aspect:::" + joinPoint.getSignature());
//    }


//    ///////////
//    @After("execution(* com.hujiang.library.demo.Greeter.**())")
//    public void greeterAdvice(JoinPoint joinPoint) throws Throwable {
//        Log.i("helloAOP", "aspect:::" + joinPoint.getSignature());
//    }
//
//    @After("execution(* android.support.v4.app.Fragment.on**(..))")
//    public void fragmentMethod(JoinPoint joinPoint) throws Throwable {
//        Log.i("helloAOP", "aspect:::" + joinPoint.getSignature());
//    }
//
//    @After("call(* com.hujiang.library.demo.AspectJavaDemo.work())")
//    public void aspectJavaDemoAdvice(JoinPoint joinPoint) throws Throwable {
//        Log.i("helloAOP", "aspect:::" + joinPoint.getSignature());
//    }
//
//    @After("execution(* com.nostra13.universalimageloader.core.ImageLoader.displayImage(..))")
//    public void aspectImageLoader(JoinPoint joinPoint) throws Throwable {
//        Log.i("helloAOP", "aspect:::" + joinPoint.getSignature());
//    }
//
//    @After("execution(* com.hujiang.library.demo.NormalClass.**(..))")
//    public void aspectNormalClass(JoinPoint joinPoint) throws Throwable {
//        Log.i("helloAOP", "aspect:::" + joinPoint.getSignature());
//    }
//
//    @Around("execution( * com.hujiang.library.demo.AOPActivity.onCreate(..))")
//    public void aopActivityAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
//        joinPoint.proceed();
//
//        Log.i("helloAOP", "aspect:::" + "------------>>>>>AOPActivity.onCreate");
//    }
}