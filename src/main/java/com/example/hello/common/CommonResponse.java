package com.example.hello.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonResponse {
    private String code;

    private String msg;

    private Object datas;

    public  CommonResponse(){}

    public CommonResponse(String code, String msg, Object datas) {
        this.setCode(code);
        this.setMsg(msg);
        this.setDatas(datas);
    }

    public CommonResponse(String code, String msg) {
        this.setCode(code);
        this.setMsg(msg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }

//    @Override
//    public String toString() {
//        return "{" +
//                "code: '" + code + '\'' +
//                ", msg: '" + msg + '\'' +
//                ", datas: " + datas  +
//                '}';
//    }
}
