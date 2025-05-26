package org.lwx.aiagent.enums;

public enum ResponseStatusEnum {
    /**
     * 返回状态枚举
     * 0:操作失败 1:操作成功 2:处理中 3:无记录
     * 4:更新失败 5:处理异常 6:无需处理
     */
    FAIL(0, "失败"),
    SUCCESS(1, "成功"),
    DOING(2, "处理中"),
    NO_RECORD(3, "无记录"),
    UPDATE_FAIL(4, "更新失败"),
    EXCEPTION(5, "处理异常"),
    NOT_DEAL_WITH(6, "无需处理");

    private final Integer code;
    private final String desc;

    /**
     * @param code
     * @param description
     */
    private ResponseStatusEnum(Integer code, String description) {
        this.code = code;
        this.desc = description;
    }

    /**
     * @return
     * @desc 返回code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @return
     * @desc 返回desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 通过code获取枚举
     *
     * @param code
     */
    public static ResponseStatusEnum getByCode(String code) {
        for (ResponseStatusEnum yesNo : values()) {
            if ((yesNo.getCode() + "").equals(code)) {
                return yesNo;
            }
        }
        return null;
    }
}
