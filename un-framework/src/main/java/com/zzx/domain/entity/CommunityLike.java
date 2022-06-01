package com.zzx.domain.entity;

import java.util.Date;

import com.zzx.enums.LikedStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 用户点赞表(UserLike)表实体类
 *
 * @author makejava
 * @since 2022-05-17 14:58:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_community_like")
public class CommunityLike {
    @TableId
    private Long id;

    //动态id
    private Long communityId;
    //点赞人id
    private String likedPostId;
    //被点赞人id
    private String likedUserId;
    //点赞状态，0表示未点赞/取消点赞，1表示点赞，默认为0
    private Integer status = LikedStatusEnum.UNLIKE.getCode();
    //创建时间
    private Date createTime = new Date();
    //更新时间
    private Date updateTime = new Date();
    //删除标志
    private Integer delFlag;

    public CommunityLike(Long communityId, String likedUserId, String likedPostId, Integer status) {
        this.communityId = communityId;
        this.likedUserId = likedUserId;
        this.likedPostId = likedPostId;
        this.status = status;
    }
}

