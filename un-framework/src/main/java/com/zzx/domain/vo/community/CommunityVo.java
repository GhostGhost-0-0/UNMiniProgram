package com.zzx.domain.vo.community;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: UNMinProgram
 * @Description
 * @Author: 那个小楠瓜
 * @create: 2022-02-28 14:09
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityVo {

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
    //作者id
    private Long createBy;
    //发布时间
    private Date createTime;
    //作者昵称
    private String authorName;
    //作者头像
    private String authorAvatar;

}
