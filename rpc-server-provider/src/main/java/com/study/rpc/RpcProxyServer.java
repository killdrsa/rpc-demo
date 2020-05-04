package com.study.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class RpcProxyServer {
    ExecutorService executorService = Executors.newCachedThreadPool();
    public void publisher(Object service,int port){
//        ServerSocket serverSocket = null;
//        try {
//            serverSocket = new ServerSocket(port);
//            while (true) {
//                Socket accept = serverSocket.accept();
//                executorService.execute(new ProcessHandlers(accept,service));
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
