package com.study.rpc;

import com.study.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ClientProxyInvoke implements InvocationHandler {
    private String ipAddress;
    private String port;
    public ClientProxyInvoke(String ipAddress, String port) {
        this.ipAddress=ipAddress;
        this.port=port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("come in..");
        //调用service的方法  在这里可以封装一个Rpcrequest请求
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        RpcNetTransport rpcNetTransport = new RpcNetTransport(ipAddress,port);
        Object send = rpcNetTransport.send(rpcRequest);
        return send;
    }
}
