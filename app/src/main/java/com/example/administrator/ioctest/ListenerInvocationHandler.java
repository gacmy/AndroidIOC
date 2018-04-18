package com.example.administrator.ioctest;

import android.app.Activity;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * Created by gacmy on 2018/4/10.
 * description:{
 * }
 */

public class ListenerInvocationHandler implements InvocationHandler {
    Activity activity;
    Method mMethod;
    public Object bind(Activity activity,Class listenerType,Method method){
        this.activity = activity;
        mMethod = method;
        return Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        if(mMethod != null){
            mMethod.invoke(activity,args);
        }else{
            result =method.invoke(activity,args);
        }
        return result;
    }
}
