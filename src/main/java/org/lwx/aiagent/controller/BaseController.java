package org.lwx.aiagent.controller;

import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.lwx.aiagent.dto.Response;
import org.lwx.aiagent.enums.ResponseStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Tag(name = "基础控制器")
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 分页参数合理化
     */
    public static final String REASONABLE = "reasonable";

    /**
     * 返回成功
     */
    public Response success() {
        return RestResult.success();
    }

    /**
     * 返回成功消息
     */
    public Response success(String message) {
        return RestResult.success(message);
    }
    public Response success(String message,Object data) {
        return RestResult.success(message,data);
    }
    /**
     * 返回成功消息
     */
    public Response successWithData(Object data) {
        return RestResult.successWithData(data);
    }


    public JSONObject successWithJSON(Object data) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CODE_TAG,ResponseStatusEnum.SUCCESS.getCode());
        jsonObject.put(MSG_TAG,ResponseStatusEnum.SUCCESS.getDesc());
        jsonObject.put(DATA_TAG,data);

        return jsonObject;
    }

    public JSONObject successWithJSON() {
        JSONObject data = new JSONObject();
        data.put(CODE_TAG, ResponseStatusEnum.SUCCESS.getCode());
        data.put(MSG_TAG,ResponseStatusEnum.SUCCESS.getDesc());

        return data;
    }

    public JSONObject errorWithJSON(String msg) {
        JSONObject data = new JSONObject();
        data.put(CODE_TAG,ResponseStatusEnum.FAIL.getCode());
        data.put(MSG_TAG,msg);

        return data;
    }

    /**
     * 返回成功消息
     */
    public Response resultWithRowCount(int row) {
        return row > 0 ? success() : error(ResponseStatusEnum.FAIL.getDesc());
    }


    public Response error(String message) {
        return RestResult.error(message);
    }


    /**
     * 获取登录部门id
     */
    public String getOrgCode() {
//        return SecurityHelper.getOrgCode();
        return null;
    }

    public boolean isSuccess(JSONObject jsonObject){
        if(ResponseStatusEnum.SUCCESS.getCode() == jsonObject.getInteger("code")){
            return true;
        }else{
            return false;
        }
    }


    public JSONObject getData(JSONObject jsonObject){
        if(ResponseStatusEnum.SUCCESS.getCode() == jsonObject.getInteger("code")){
            return jsonObject.getJSONObject("data");
        }
        return null;
    }
}
