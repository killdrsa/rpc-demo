package com.study.rpc;

import com.study.IHelloService;
import com.study.User;
@RpcService(IHelloService.class)
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String msg) {
        System.out.println("request in say:"+msg);
        return "say hello "+ msg;
    }

    @Override
    public String saveUser(User user) {
        System.out.println(user);
        return "SUCCESS";
    }
}
