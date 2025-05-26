package org.lwx.aiagent.controller;


import org.lwx.aiagent.dto.Response;
import org.lwx.aiagent.enums.ResponseStatusEnum;

/**
 * Description
 * Author
 * Version v1.0
 */
public class RestResult<T> {


    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 初始化一个新创建的 RestResult 对象，使其表示一个空消息。
     */
    private RestResult() {
    }



    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static Response success() {
        return RestResult.success(ResponseStatusEnum.SUCCESS.getDesc());
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static Response success(String msg) {
        return RestResult.success(msg, null);
    }




    public static Response successWithData(Object data) {
        return RestResult.success(ResponseStatusEnum.SUCCESS.getDesc(), data);
    }


    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static Response success(String msg, Object data) {
        return new Response(ResponseStatusEnum.SUCCESS.getCode(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static Response error() {
        return RestResult.error(ResponseStatusEnum.FAIL.getDesc());
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static Response error(String msg) {
        return RestResult.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static Response error(String msg, Object data) {
        return new Response(ResponseStatusEnum.FAIL.getCode(), msg, data);
    }
    public static Response error(int code, String msg) {
        return new Response(code, msg, null);
    }


}
