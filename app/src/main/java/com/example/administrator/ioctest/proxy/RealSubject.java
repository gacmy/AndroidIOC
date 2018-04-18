package com.example.administrator.ioctest.proxy;

/**
 * Created by gacmy on 2018/4/10.
 * description:{
 * }
 */

public class RealSubject implements Subject {
    @Override
    public void doSomething() {
        System.out.println("call doSomething()");
    }

}
