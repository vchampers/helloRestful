package com.example.hello.controller;

import com.example.hello.HelloApplication;
import com.example.hello.common.CommonResponse;
import com.example.hello.entity.HelloEntity;
import com.example.hello.service.HelloService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.hello.common.CommonResponseEnum;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
public class HelloController {
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    HelloService service;
    CommonResponseEnum responseEnum;

    @RequestMapping(value = "/hello", method= RequestMethod.GET)
    public CommonResponse getHelloMsg(@RequestParam(value="name", defaultValue="World") String name){
        try {
            HelloEntity helloEntity = service.getHelloMsg(name);
            return new CommonResponse("0000", CommonResponseEnum.responseMap.get("0000"), helloEntity);
        } catch (Exception e) {
            return new CommonResponse("9999", CommonResponseEnum.responseMap.get("9999"));
        }
    }
}
