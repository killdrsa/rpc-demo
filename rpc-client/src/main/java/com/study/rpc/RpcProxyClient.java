package com.study.rpc;

import java.lang.reflect.Proxy;

public class RpcProxyClient {
    public <T> T rpcProxyClient(Class<T> interfaceName,String ipAddress,String port){
            return (T) Proxy.newProxyInstance(interfaceName.getClassLoader(),new Class[]{interfaceName},new ClientProxyInvoke(ipAddress,port));
    }
}
