package com.zzx.enums;

import lombok.Getter;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-05-17 15:13
 **/
@Getter
public enum LikedStatusEnum {

    LIKE(1,"点赞"),
    UNLIKE(0,"未点赞/取消点赞");

    private Integer code;
    private String message;

    LikedStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
