package com.zzx.domain.vo.community;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-05-14 16:21
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityDetailVo {
    //打卡动态id
    private Long id;
    //健身心得
    private String content;
    //健身地点
    private String address;
    //上传的图片
    private String picture;
    //点赞数
    private Integer goodNum;
    //作者昵称
    private String authorName;
    //作者头像
    private String authorAvatar;
}
