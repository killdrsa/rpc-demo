package com.study.rpc;

import com.study.RpcRequest;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.util.Map;

public class ProcessHandlers implements Runnable {
    Socket socket ;
    Map<String,Object> map;

    public ProcessHandlers(Socket socket, Map<String,Object> map) {
        this.socket = socket;
        this.map = map;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream=null;
        ObjectOutputStream objectOutputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            Object result = invoke(rpcRequest);
            System.out.println("result:"+result);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(objectInputStream!=null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectOutputStream!=null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Object invoke(RpcRequest rpcRequest) throws Exception {
        String className = rpcRequest.getClassName();
        System.out.println("className:"+className);
        Object service = map.get(className);
        if(service==null){
            throw new Exception("service is null"+className);
        }
        Object[] parameters = rpcRequest.getParameters();//拿到参数数组
        Class<?>[] types = new Class[parameters.length]; //获得每个参数的类型
        for (int i = 0; i <types.length ; i++) {
            types[i] = parameters[i].getClass();
        }
        Class<?> aClass = Class.forName(rpcRequest.getClassName());
        Method method = aClass.getMethod(rpcRequest.getMethodName(), types);
        Object result = method.invoke(service, parameters);
        System.out.println("result:--"+result);
        return result;
    }
}
