package com.hongyuji.imsystem.domain;

/**
 * @description:
 * @author: jihy
 * @date: 2018-07-15 20:53
 */
public class Message <T extends MessageInterface> {

    private static final int SUCCESS_CODE = 0;
    private boolean success;
    private String errorInfo;
    private Integer code;
    private T data;
    private int type;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T extends MessageInterface> Message<T> buildSuccessMessage(T data){
        Message msg = new Message();
        msg.data = data;
        msg.code = SUCCESS_CODE;
        msg.errorInfo = "";
        msg.success = true;
        msg.type = data.getType();
        return msg;
    }
}
