package com.mmall.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * Json类
 *
 * @author xvanning
 * @create 2019-04-25 21:01
 */
@Data
public class JsonData {
    private Object data;
    private String msg;
    private boolean ret;

    public JsonData(boolean ret) {
        this.ret = ret;
    }

    public static JsonData success() {
        return new JsonData(true);
    }

    public static JsonData success(Object object) {
        JsonData jsonData = new JsonData(true);
        jsonData.setData(object);
        return jsonData;
    }

    public static JsonData success(Object object, String msg) {
        JsonData jsonData = new JsonData(true);
        jsonData.setData(object);
        jsonData.setMsg(msg);
        return jsonData;
    }

    public static JsonData fail(String msg) {
        JsonData jsonData = new JsonData(false);
        jsonData.setMsg(msg);
        return jsonData;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("ret", ret);
        map.put("msg", msg);
        map.put("data", data);
        return map;
    }
}