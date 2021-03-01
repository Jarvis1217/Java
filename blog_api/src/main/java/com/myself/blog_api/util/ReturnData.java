package com.myself.blog_api.util;

import com.myself.blog_api.util.StatusCode;

public class ReturnData {
    /**
     * 状态码
     */
    private int code;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 返回信息
     */
    private String message;

    public ReturnData() {
    }

    public ReturnData(Object data) {
        this.data = data;
    }

    public ReturnData(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ReturnData(int code,  String message) {
        this.code = code;
        this.message = message;
    }

    public ReturnData(int code, Object data, String message) {

        this.code = code;
        this.data = data;
        this.message = message;
    }
    public ReturnData error(Object data,String message){
        this.code = 500;
        this.data = data;
        this.message = message;
        return this;
    }

    /*set get方法*/

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}