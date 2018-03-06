package com.hongyuji.imsystem.domain;

public class Response<T> {

    public static final int DEFAULT_FAIL_STATUS = 0;
    public static final int DEFAULT_SUCCESS_STATUS = 1;

    private int code;

    private T data;

    private String msg;

    public static Response toFail(int code, String msg){
        Response resp = new Response();
        resp.code = code;
        resp.msg = msg;
        return resp;
    }

    public static Response toFail(String msg){
        return toFail(DEFAULT_FAIL_STATUS, msg);
    }

    public static<T> Response toSuccess(int code, T data, String msg){
        Response resp = new Response();
        resp.code = code;
        resp.data = data;
        resp.msg = msg;
        return resp;
    }

    public static<T> Response toSuccess (T data, String msg){
        return toSuccess(DEFAULT_SUCCESS_STATUS,data, msg);
    }

    public static<T> Response toSuccess(T data){
        return toSuccess(DEFAULT_SUCCESS_STATUS,data, null);
    }

    public static<T> Response toSuccess(){
        return toSuccess(DEFAULT_SUCCESS_STATUS,null, null);
    }


    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}
