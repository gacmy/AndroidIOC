package com.example.administrator.ioctest.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by gacmy on 2018/4/10.
 * description:{
 * }
 */

public class ProxyHandler implements InvocationHandler {
    private Object tar;
    public Object bind(Object tar){
        this.tar = tar;
        //动态代理接口
        return Proxy.newProxyInstance(tar.getClass().getClassLoader(),
                tar.getClass().getInterfaces(),this);
    }
    //调用接口
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        result = method.invoke(tar,args);
        return result;
    }
}
