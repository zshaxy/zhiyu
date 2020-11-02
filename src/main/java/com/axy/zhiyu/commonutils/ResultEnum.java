package com.axy.zhiyu.commonutils;

public enum ResultEnum {
    SUCCESS(200, "success"), ERROR(500, "error");

    private int code;
    private String info;

    ResultEnum(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
