package com.study.rpc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.study.rpc")
public class SpringConfig {


    @Bean
    public RpcServer rpcServer(){

        return new RpcServer(8080);
    }
}
