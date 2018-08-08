package com.example.hello.common;

import java.util.HashMap;

public class CommonResponseEnum {
    public final static HashMap<String,String> responseMap;
    static{
        responseMap = new HashMap<String, String>();
        responseMap.put("0000", "操作成功");
        responseMap.put("1001", "数据不合法，传入参数");
        responseMap.put("1002", "数据不存在");
        responseMap.put("9999", "未知错误");
    }

}
