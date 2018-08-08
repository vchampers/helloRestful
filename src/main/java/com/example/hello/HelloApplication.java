package com.example.hello;

import com.example.hello.common.CommonResponse;
import com.example.hello.common.CommonResponseEnum;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class HelloApplication {
    private static final Logger log = LoggerFactory.getLogger(HelloApplication.class);

    static ConsumRest consumRest = new ConsumRest();

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);

        //Hello
        log.info(consumRest.getHelloResponse("小明").toString());

        //文件上传
        JSONObject json = consumRest.uploadFile(new File("D:\\中文.txt"));
        log.info(json.toString());
        String filename = json.getString("datas");

        //文件下载
        try {
            File file = consumRest.downloadFile(filename);
        } catch (Exception e) {
            log.info(new CommonResponse("1002", CommonResponseEnum.responseMap.get("1002")).toString());
        }
    }
}
