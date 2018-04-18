package com.example.administrator.ioctest.proxy;

/**
 * Created by gacmy on 2018/4/10.
 * description:{
 * }
 */

public class SubjectProxy implements Subject {
    Subject subjectImp = new RealSubject();
    @Override
    public void doSomething() {
        System.out.println("before");
        subjectImp.doSomething();
        System.out.println("after");
    }
}
