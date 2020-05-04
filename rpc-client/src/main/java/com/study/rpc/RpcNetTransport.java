package com.study.rpc;

import com.study.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RpcNetTransport {
    private String ipAddress;
    private String port;
    public RpcNetTransport(String ipAddress, String port) {
        this.ipAddress=ipAddress;
        this.port=port;
    }


    public Object send(RpcRequest rpcRequest){
        Socket socket =null;
        ObjectOutputStream objectOutputStream=null;
        ObjectInputStream objectInputStream=null;
        Object result=null;
        try {
            socket = new Socket(ipAddress, Integer.parseInt(port));
             objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
             objectOutputStream.writeObject(rpcRequest);
             objectOutputStream.flush();


            objectInputStream = new ObjectInputStream(socket.getInputStream());
             result = objectInputStream.readObject();
            return result;
        }catch (Exception e){
            e.getStackTrace();
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
        return result;
    }
}
