package com.example.hello;

import com.example.hello.common.CommonResponse;
import com.example.hello.common.CommonResponseEnum;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ConsumRest {
    private static final Logger log = LoggerFactory.getLogger(HelloApplication.class);

    public JSONObject getHelloResponse(String name) {
        RestTemplate restTemplate = new RestTemplate();
        CommonResponse response = restTemplate.getForObject("http://localhost:8080/practice/hello?name={name}", CommonResponse.class, name);
        JSONObject json = JSONObject.fromObject(response);
        return json;
    }

    public JSONObject uploadFile(File file) {
        String url = "http://localhost:8080/practice/upload";
        RestTemplate restTemplate = new RestTemplate();
        FileSystemResource resource = new FileSystemResource(file);
        MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
        param.add("jarFile", resource);
        CommonResponse commonResponse = null;
        try {
            commonResponse = restTemplate.postForObject(url, param, CommonResponse.class);
        } catch (Exception e) {
            commonResponse = new CommonResponse("1001", CommonResponseEnum.responseMap.get("1001"));
        }
        JSONObject json = JSONObject.fromObject(commonResponse);
        return json;
    }

    public File downloadFile(String fileId) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> responseEntity = restTemplate.getForEntity("http://localhost:8080/practice/download/{fileId}", byte[].class, fileId);
        byte[] data = responseEntity.getBody();
        String filepath = "D:\\" + fileId;
        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fos = null;
        fos = new FileOutputStream(file);
        fos.write(data, 0, data.length);
        fos.flush();
        fos.close();
        return file;
    }
}
