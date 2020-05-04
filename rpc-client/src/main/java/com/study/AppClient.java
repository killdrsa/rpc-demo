package com.study;

import com.study.rpc.RpcProxyClient;

/**
 * Hello world!
 *
 */
public class AppClient
{
    public static void main( String[] args )
    {
        RpcProxyClient rpcProxyClient = new RpcProxyClient();
        IHelloService iHelloService = rpcProxyClient.rpcProxyClient(IHelloService.class, "localhost", "8080");
        String result = iHelloService.sayHello("张三");
        System.out.println(result);
    }
}
