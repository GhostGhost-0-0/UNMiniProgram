package com.zzx.enums;

/**
 * @program: UNMiniProgram
 * @Description 状态码枚举类
 * @Author: 那个小楠瓜
 * @create: 2022-02-23 14:49
 **/
public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    FILE_NULL_ERROR(501,"文件不能为空"),
    CONTENT_NULL_ERROR(502,"内容不能为空"),
    LOGIN_ERROR(505,"登录失败"),
    COMMUNITY_NULL_ERROR(506,"动态不存在，无法更新");

    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
