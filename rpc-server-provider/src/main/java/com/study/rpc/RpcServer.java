package com.study.rpc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class RpcServer implements InitializingBean,ApplicationContextAware {
    int port ;
    ExecutorService executorService = Executors.newCachedThreadPool();

    Map<String,Object> map = new HashMap<>();

    public RpcServer(int port) {
        this.port = port;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket accept = serverSocket.accept();
                executorService.execute(new ProcessHandlers(accept,map));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(RpcService.class);
        if(!beansWithAnnotation.isEmpty()) {
            for (Object bean : beansWithAnnotation.values()) {
                RpcService annotation = bean.getClass().getAnnotation(RpcService.class);
                String name = annotation.value().getName();
                //封装一个map
                map.put(name,bean);
            }
        }
    }
}
