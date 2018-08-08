package com.example.hello.controller;

import com.example.hello.common.CommonResponse;
import com.example.hello.common.CommonResponseEnum;
import com.example.hello.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class FileController {
    private final StorageService storageService;

    @Autowired
    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public CommonResponse upload(MultipartFile jarFile) throws IOException {
        try {
            String filename = storageService.upload(jarFile);
            return new CommonResponse("0000", CommonResponseEnum.responseMap.get("0000"), filename);
        } catch (IOException e) {
            return new CommonResponse("1001", CommonResponseEnum.responseMap.get("1001"));
        } catch (Exception e) {
            return new CommonResponse("9999", CommonResponseEnum.responseMap.get("9999"));
        }
    }

    @RequestMapping(value = "/download/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@PathVariable String fileId) {
        ResponseEntity<byte[]> responseEntity = null;
        try {
            responseEntity = storageService.download(fileId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseEntity;
    }
}
