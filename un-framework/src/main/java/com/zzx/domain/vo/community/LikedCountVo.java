package com.zzx.domain.vo.community;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-05-17 15:40
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikedCountVo {

    // 动态id
    private Long communityId;
    //被点赞人id
    private String likedUserId;
    // 动态点赞数
    private Integer goodNum;
}
