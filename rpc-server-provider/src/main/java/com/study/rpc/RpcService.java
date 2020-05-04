package com.study.rpc;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //类或者接口
@Retention(RetentionPolicy.RUNTIME)
@Component //被spring管理
public @interface RpcService {
    Class<?> value();
}
