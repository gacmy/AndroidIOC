package com.example.administrator.ioctest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by gacmy on 2018/4/9.
 * description:{
 * }
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface EventBase {
    String listenerSetter();
    Class listenerType();
    String callbackMethod();
}
