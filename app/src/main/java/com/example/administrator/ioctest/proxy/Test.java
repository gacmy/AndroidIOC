package com.example.administrator.ioctest.proxy;

/**
 * Created by gacmy on 2018/4/10.
 * description:{
 * }
 */

public class Test {
    public static void main(String[] args){
        Subject sub = new SubjectProxy();
        sub.doSomething();
        ProxyHandler handler = new ProxyHandler();
         Subject sub1 = (Subject) handler.bind(new RealSubject());
         sub1.doSomething();

    }
}
