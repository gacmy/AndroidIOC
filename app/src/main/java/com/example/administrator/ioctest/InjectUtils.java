package com.example.administrator.ioctest;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;



import java.lang.reflect.Field;

import java.lang.reflect.Method;


/**
 * Created by gacmy on 2018/4/9.
 * description:{
 * }
 */

public class InjectUtils {

    public static void inject(Activity activity){
        injectLayout(activity);
        injectViews(activity);
        injectEvents(activity);
    }

    //方法注解
    private static void injectEvents(Activity activity){
        //获取方法上面的注解
        Class myClass = activity.getClass();
        Method myMethod[] = myClass.getDeclaredMethods();//先拿到全部方法
        for(Method method : myMethod){//遍历方法
            //Log.e("gacmy","method name:"+method.getName());
            if(!method.isAnnotationPresent(EventInject.class)){
                continue;
            }
            Log.e("gacmy","findEventInject annotation");
            EventInject eventAnnotation = method.getAnnotation(EventInject.class);//获取 EventInject方法上的所有注解
            Log.e("gacmy","eventInject annotation length:"+eventAnnotation.annotationType().getAnnotations().length);
            if(!eventAnnotation.annotationType().isAnnotationPresent(EventBase.class)){
                    return;
            }
            //获取eventbase注解 获取事件三要素
            EventBase eventBase = eventAnnotation.annotationType().getAnnotation(EventBase.class);
            //获取事件的属性
            String listenerSetter = eventBase.listenerSetter();
            Class  listenerType = eventBase.listenerType();//View.OnClickListener
            String callbackMethod = eventBase.callbackMethod();
            try {
                int[] viewIds = eventAnnotation.value();//获取view id数组
                for(int viewId : viewIds){
                    View view =activity.findViewById(viewId);
                    //反射setOnclickListener 这里代理
                    Method setListenerMethod = view.getClass().getMethod(listenerSetter,listenerType);

                    ListenerInvocationHandler invocationHandler = new ListenerInvocationHandler();
                    //利用代理 将onClick方法 利用实际的注解method替代
                    Object newProxyInstance = invocationHandler.bind(activity,listenerType,method);
                    setListenerMethod.invoke(view,newProxyInstance);//调用view.setOnclickListener() OnClickListener使用代理
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private static void InjectViews(Activity activity,int id){

    }


    //字段注解
    private static void injectViews(Activity activity){
        //获取每一个熟悉上的注解
        Class myClass = activity.getClass();
        Field[] myFields = myClass.getDeclaredFields();
        for(Field field : myFields){
            ViewInject myView = field.getAnnotation(ViewInject.class);
            if(myView != null){
                int value = myView.value();
                View view = activity.findViewById(value);
                try {
                    field.setAccessible(true);
                    field.set(activity,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //类注解
    private static void injectLayout(Activity activity){
        //获取ContentView注解
        Class myClass  = activity.getClass();
        ContentView myContentView = (ContentView) myClass.getAnnotation(ContentView.class);
        int myLayoutResId = myContentView.value();
        activity.setContentView(myLayoutResId);
    }

}



























