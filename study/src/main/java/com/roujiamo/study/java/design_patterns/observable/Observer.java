package com.roujiamo.study.java.design_patterns.observable;

/**
 * 观察者
 */
public interface Observer {
    /**
     *  更新观察者内部数据
     */
    public void update(String data);
}
