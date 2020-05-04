package com.study;

import com.study.rpc.HelloServiceImpl;
import com.study.rpc.RpcProxyServer;
import com.study.rpc.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        /**
         * 简单的发布 通过new
         */
//        IHelloService iHelloService = new HelloServiceImpl();
//        RpcProxyServer rpcProxyServer = new RpcProxyServer();
//        rpcProxyServer.publisher(iHelloService,8080);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        ((AnnotationConfigApplicationContext) applicationContext).start();
    }
}
