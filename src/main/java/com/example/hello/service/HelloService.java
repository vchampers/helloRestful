package com.example.hello.service;

import com.example.hello.entity.HelloEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class HelloService {
    public HelloEntity getHelloMsg(String name){
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();
        return new HelloEntity(name, currentTime);
    }
}
