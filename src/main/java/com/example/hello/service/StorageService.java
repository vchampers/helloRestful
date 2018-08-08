package com.example.hello.service;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@Service
public class StorageService {
    private static final Logger log = LoggerFactory.getLogger(StorageService.class);
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private HttpServletRequest request;

    public String upload(MultipartFile file) throws IOException {
        //上传文件路径
        String path = request.getSession().getServletContext().getRealPath("upload");
        //上传文件名
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("."))
                + System.currentTimeMillis() + fileType;
        File filepath = new File(path, fileName);
        //判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        //将上传文件保存到一个目标文件当中
        file.transferTo(new File(path + File.separator + fileName));
        return fileName;
    }

    public ResponseEntity<byte[]> download(String fileId) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = path + "\\" + fileId;
        File file = new File(fileName);

        // 为防止中文文件名的乱码显示
        String agent = request.getHeader("User-Agent");
        if (agent.contains("MSIE")) {
            // IE浏览器
            fileId = URLEncoder.encode(fileId, "utf-8");
            fileId = fileId.replace("+", "");
        } else if (agent.contains("Firefox")) {
            //火狐浏览器
            BASE64Encoder base64Encoder = new BASE64Encoder();
            fileId = "=?utf-8?B?" + base64Encoder.encode(fileId.getBytes("utf-8")) + "?=";
        } else {
            //其它浏览器
            fileId = URLEncoder.encode(fileId, "UTF-8");
        }
        // 编码后文件名中的空格被替换为“+”号，所以此处将替换编码后文件名中“+”号为UTF-8中空格的“%20”编码
        fileId = fileId.replaceAll("\\+", "%20");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileId);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }
}
