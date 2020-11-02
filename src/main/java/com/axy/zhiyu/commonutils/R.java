package com.axy.zhiyu.commonutils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
    private boolean success;

    private int code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    private R() {};

    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultEnum.SUCCESS.getCode());
        r.setMessage(ResultEnum.SUCCESS.getInfo());
        return r;
    }

    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultEnum.ERROR.getCode());
        r.setMessage(ResultEnum.ERROR.getInfo());
        return r;
    }

    public R success(boolean success) {
        this.success = success;
        return this;
    }

    public R code(int code) {
        this.code = code;
        return this;
    }

    public R message(String message) {
        this.message = message;
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> data) {
        this.data = data;
        return this;
    }

}
