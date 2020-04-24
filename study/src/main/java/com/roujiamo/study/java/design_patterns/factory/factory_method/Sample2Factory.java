package com.roujiamo.study.java.design_patterns.factory.factory_method;


import com.roujiamo.study.java.design_patterns.factory.Sample2;
import com.roujiamo.study.java.design_patterns.factory.SampleInterface;

/**
 */
public class Sample2Factory implements FactoryMethod{

    @Override
    public SampleInterface createSample() {
        return new Sample2();
    }
}
