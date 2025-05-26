package org.lwx.aiagent.dto;

public class Response<T> {
    /**
     * 接口状态码
     */
    private int code;
    /**
     * 异常描述信息
     */
    private String message;

    /**
     * 具体数据
     */
    private T data;
    public Response() {

    }

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        if(data != null) {
            this.data = data;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
